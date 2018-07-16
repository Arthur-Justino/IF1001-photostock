package com.parse.starter.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.starter.R;
import com.parse.starter.Receivers.AlarmReceiver;

import java.util.Calendar;

public class LoginActivity extends AppCompatActivity {
    private Context context;
    public EditText textUsu;
    public EditText textSen;
    private  Button logar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textUsu = (EditText) findViewById(R.id.loginUsuario);
        textSen = (EditText) findViewById(R.id.loginSenha);
        logar = (Button) findViewById(R.id.loginButtonId);

        verificaUsuarioLogado();

        logar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario = textUsu.getText().toString();
                String senha = textSen.getText().toString();
                checkLogin(usuario, senha);
            }
        });
    }

    private void verificaUsuarioLogado(){
        if( ParseUser.getCurrentUser() != null){
            startActivity(new Intent(LoginActivity.this, ListaProdutosActivity.class));

        }
    }



    private void startBackgroundTask(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        Intent intent = new Intent(getApplicationContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
    }
    public void abrirCadastroUsuario(View view){
        startActivity(new Intent(LoginActivity.this,CadastroActivity.class));
    }

    private void checkLogin(String usuario, String senha){
        ParseUser.logInInBackground(usuario, senha, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e==null){
                    Toast.makeText(LoginActivity.this, "Login realizado com sucesso!" ,Toast.LENGTH_LONG).show();
                    startBackgroundTask();
                    startActivity(new Intent(LoginActivity.this,ListaProdutosActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "Erro ao fazer login! " + e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
