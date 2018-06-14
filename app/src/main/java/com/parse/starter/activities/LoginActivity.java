package com.parse.starter.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.parse.starter.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void abrirCadastroUsuario(View view){
        startActivity(new Intent(LoginActivity.this,CadastroActivity.class));
    }
    public void goToLista(View view){
        startActivity(new Intent(LoginActivity.this,ListaProdutosActivity.class));
    }
}
