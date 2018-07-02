package com.parse.starter.Services;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;





public class ServiceIntent extends IntentService {
    public ServiceIntent() {
        super("SalvaProdutos");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        final String nome;
        final String quant;

        synchronized (this) {
            int count = 0;

                try {
                    wait(8000);
                    ParseObject prod = new ParseObject("Produto");
                    nome = (String) intent.getExtras().get("nomeProd");
                    quant = (String) intent.getExtras().get("quantProd");
                    prod.put("produto",nome);
                    prod.put("quantidade", Integer.parseInt(quant));
                    prod.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e== null){
                                Toast.makeText(getApplicationContext(),nome+" ---> estoque",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Erro ao salvar os dados",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    count++;

                } catch (InterruptedException e) {
                    e.printStackTrace();

                }


        }
    }
}
