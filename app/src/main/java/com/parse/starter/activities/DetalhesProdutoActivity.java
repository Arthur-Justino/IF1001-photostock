package com.parse.starter.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.starter.R;
import com.squareup.picasso.Picasso;


public class DetalhesProdutoActivity extends AppCompatActivity {
    private TextView nomeProd;
    private TextView quantProd;
    private ImageView imageProd;
    private  TextView vali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_produto);
        nomeProd = (TextView) findViewById(R.id.nomeProId);
        quantProd = (TextView) findViewById(R.id.nomeQuantId);
        imageProd = (ImageView) findViewById(R.id.photoProd);
        vali = (TextView) findViewById(R.id.validadeId);

        Bundle extra = getIntent().getExtras();

        nomeProd.setText(extra.getString("nomeProd"));
        quantProd.setText(extra.getString ("quantProd"));
        vali.setText(extra.getString("validade"));
        Picasso.get().load(String.valueOf(extra.get("imagem"))).into(imageProd);


    }
}
