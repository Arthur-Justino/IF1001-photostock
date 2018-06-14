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
        ParseObject prod = new ParseObject("Produto");
        prod.put("produto",nomeProduto.getText().toString());
        prod.put("quantidade", Integer.parseInt(quantProd.getText().toString()));
        prod.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e== null){
                    Toast.makeText(getApplicationContext(),"Dados salvos com sucesso!",Toast.LENGTH_SHORT);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Erro ao salvar os dados",Toast.LENGTH_SHORT);
                }

            }
        });

    }
}
