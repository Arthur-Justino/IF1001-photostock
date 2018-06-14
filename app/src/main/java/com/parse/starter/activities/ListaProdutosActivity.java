package com.parse.starter.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {
    ListView listaprod;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        listaprod = (ListView) findViewById(R.id.listViewId);
        ParseQuery<ParseObject> consulta = new ParseQuery<ParseObject>("Produto");
        consulta.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e==null){
                    final ArrayList<String> listaNome = new ArrayList<String>();
                    final ArrayList<Number> listaQuant = new ArrayList<Number>();
                    for (ParseObject item:objects) {
                        listaNome.add(item.get("produto").toString());
                        listaQuant.add((Number) item.get("quantidade"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            android.R.id.text1,listaNome);
                    listaprod.setAdapter(adapter);
                    listaprod.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(ListaProdutosActivity.this, DetalhesProdutoActivity.class);
                            intent.putExtra("nomeProd",listaNome.get(i));
                            intent.putExtra("quantProd",listaQuant.get(i).toString());
                            startActivity(intent);

                        }
                    });
                }
                else{
                    Log.i("erroLista","Sem produtos: "+e.getMessage());
                }

            }
        });
    }
    public void abrirCadastroProduto(View view){
        startActivity(new Intent(ListaProdutosActivity.this,CadastroProdutosActivity.class));
    }
}
