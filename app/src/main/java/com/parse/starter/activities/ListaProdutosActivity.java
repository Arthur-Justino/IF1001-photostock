package com.parse.starter.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
                    ArrayList<String> lista = new ArrayList<String>();
                    for (ParseObject item:objects) {
                        lista.add(item.get("produto").toString());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            android.R.id.text1,lista);
                    listaprod.setAdapter(adapter);
                }
                else{
                    Log.i("erroLista","Sem produtos: "+e.getMessage());
                }

            }
        });
    }
}
