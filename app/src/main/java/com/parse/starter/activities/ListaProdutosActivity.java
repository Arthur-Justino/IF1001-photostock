package com.parse.starter.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.ProcessedData;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.starter.R;

import java.util.ArrayList;
import java.util.List;

public class ListaProdutosActivity extends AppCompatActivity {
    ListView listaprod;
    private ProgressDialog progD;
    final ArrayList<String> listaNome = new ArrayList<String>();
    final ArrayList<Number> listaQuant = new ArrayList<Number>();
    final int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_produtos);
        listaprod = (ListView) findViewById(R.id.listViewId);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Parâmetros do ProgressDialog
        progD = new ProgressDialog(ListaProdutosActivity.this);
        progD.setCancelable(false);
        progD.setTitle("Carregando...");
        progD.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progD.setMax(100);
        progD.setProgress(0);
        progD.show();
        listaNome.clear();
        CarregaLista cl = new CarregaLista();
        cl.execute(10);
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

    public void abrirCadastroProduto(View view){
        startActivity(new Intent(ListaProdutosActivity.this,CadastroProdutosActivity.class));
    }

    public class CarregaLista extends AsyncTask<Integer, String, String>{
        @Override
        protected String doInBackground(Integer... integers) {

            int progress = 0;
            int total = integers[0];


            ParseQuery<ParseObject> consulta = new ParseQuery<ParseObject>("Produto");
            consulta.findInBackground(new FindCallback<ParseObject>() {
                @Override
                public void done(List<ParseObject> objects, ParseException e) {

                    if (e == null) {
                            for (ParseObject item : objects) {
                                    listaNome.add(item.get("produto").toString());
                                    listaQuant.add((Number) item.get("quantidade"));
                            }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                                getApplicationContext(),
                                android.R.layout.simple_list_item_1,
                                android.R.id.text1, listaNome);
                        listaprod.setAdapter(adapter);


                    } else {
                        Toast.makeText(getApplicationContext(),"Lista vazia",Toast.LENGTH_SHORT).show();
                    }

                }

        });
            while(progress<=total){
                try{
                    Thread.sleep(integers[0]*30);
                }catch (InterruptedException er){

                }
                this.publishProgress(String.valueOf(progress),String.valueOf(total));
                progress++;
            }
            return "DONE";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Float progress = Float.valueOf(values[0]);
            Float total = Float.valueOf(values[1]);
            progD.setProgress((int)((progress/total)*100));


            if(values[0].equals(values[1])) {
                progD.cancel();
            }
        }

    }
}
