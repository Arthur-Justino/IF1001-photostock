package com.parse.starter.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.starter.R;

public class CadastroActivity extends AppCompatActivity {
    private EditText textoUsuario;
    private  EditText textoSenha;
    private Button botaoCadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        textoUsuario = (EditText) findViewById(R.id.text_usuario);
        textoSenha =(EditText) findViewById(R.id.text_senha);
        botaoCadastrar = (Button) findViewById(R.id.button_register);

        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarUsuario();
            }
        });

    }

    private void cadastrarUsuario(){
        ParseUser usuario = new ParseUser();
        usuario.setUsername(textoUsuario.getText().toString());
        usuario.setPassword(textoSenha.getText().toString());

        usuario.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    Toast.makeText(CadastroActivity.this, "Cadastro realizado com sucesso!", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(CadastroActivity.this, "Erro ao cadastrar usuário! "+e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void abrirLogin(View view){
        ParseUser.logOut();
        Intent intent = new Intent(CadastroActivity.this,LoginActivity.class);
        startActivity(intent);

    }
}
