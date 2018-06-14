package com.parse.starter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.parse.starter.R;

public class DetalhesProdutoActivity extends AppCompatActivity {
    private TextView nomeProd;
    private TextView quantProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);
        nomeProd = (TextView) findViewById(R.id.nomeProId);
        quantProd = (TextView) findViewById(R.id.nomeQuantId);
        Bundle extra = getIntent().getExtras();

        nomeProd.setText(extra.getString("nomeProd"));
        quantProd.setText(extra.getString ("quantProd"));
    }
}
