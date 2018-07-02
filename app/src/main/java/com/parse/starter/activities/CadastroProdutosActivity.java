package com.parse.starter.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.parse.starter.R;
import com.parse.starter.Services.ServiceIntent;

public class CadastroProdutosActivity extends AppCompatActivity {
    EditText nomeProduto;
    EditText quantProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);
    }
    public void cadastrarProduto(View view){
        nomeProduto = (EditText) findViewById(R.id.nomeProduto);
        quantProd = (EditText) findViewById(R.id.quantidadeProduto);
        Intent intentService = new Intent(this,ServiceIntent.class);
        intentService.putExtra("nomeProd",nomeProduto.getText().toString());
        intentService.putExtra("quantProd",quantProd.getText().toString());
        startService(intentService);

    }
}
