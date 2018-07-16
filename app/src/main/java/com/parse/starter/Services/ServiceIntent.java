package com.parse.starter.Services;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ServiceIntent extends IntentService {
    public ServiceIntent() {
        super("SalvaProdutos");
    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    private byte[] readInFile(String path, Uri seleima) throws IOException {


        ParcelFileDescriptor parcelFileDescriptor = getContentResolver().openFileDescriptor(seleima,"r");
        FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);
        options.inSampleSize = calculateInSampleSize(options, 300, 300);
        options.inJustDecodeBounds = false;
        bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor, null, options);

        parcelFileDescriptor.close();


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,50,stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        final String nome;
        final String quant;
        final String path;
        final Uri seleima;

        synchronized (this) {

                try {
                    wait(2000);

                    nome = (String) intent.getExtras().get("nomeProd");
                    quant = (String) intent.getExtras().get("quantProd");
                    path = (String) intent.getExtras().get("path");
                    seleima = (Uri) intent.getExtras().get("uri");


                    byte[] image = null;
                    try{
                        image = readInFile(path, seleima);
                    }catch(Exception e){
                        e.printStackTrace();
                    }




                    DateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    Date date = null;
                    try {
                        date = formatter.parse(intent.getExtras().get("validade").toString());

                    } catch (java.text.ParseException e) {
                        e.printStackTrace();
                    }

                    ParseObject prod = new ParseObject("Produto");
                    ParseFile file = new ParseFile("imagem.png",image);
                    file.saveInBackground();
                    prod.put("produto",nome);
                    prod.put("quantidade", Integer.parseInt(quant));
                    prod.put("validade",date);
                    prod.put("foto",file);
                    prod.put("username", ParseUser.getCurrentUser().getUsername().toString());
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


                } catch (InterruptedException e) {
                    e.printStackTrace();

                }


        }
    }
}
