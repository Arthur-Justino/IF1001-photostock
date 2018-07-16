package com.parse.starter.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class RepeatingActivity extends AppCompatActivity {

    final ArrayList<String> listaNome = new ArrayList<String>();
    final ArrayList<ParseObject> items = new ArrayList<>();
    ListView lista;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeating);
        lista = (ListView) findViewById(R.id.listValidade);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RepeatingActivity.this, DetalhesProdutoActivity.class);

                ParseObject parseObject = items.get(i);

                intent.putExtra("nomeProd", parseObject.get("produto").toString());
                intent.putExtra("quantProd", parseObject.get("quantidade").toString());
                intent.putExtra("validade", parseObject.get("validade").toString());
                intent.putExtra("imagem", parseObject.getParseFile("foto").getUrl());

                startActivity(intent);

            }
        });

        ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Produto");
        Date date = new Date();
        filtro.whereEqualTo("username", ParseUser.getCurrentUser().getUsername().toString());
        filtro.whereLessThan("validade", date);
        filtro.addAscendingOrder("validade");
        filtro.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                for (ParseObject parseObject : objects) {
                    listaNome.add(parseObject.get("produto").toString());
                    items.add(parseObject);

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        getApplicationContext(),
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1, listaNome);
                lista.setAdapter(adapter);
            }


        });


    }

}
