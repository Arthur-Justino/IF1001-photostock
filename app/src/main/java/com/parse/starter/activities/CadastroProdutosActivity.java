package com.parse.starter.activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;


import com.parse.starter.R;
import com.parse.starter.Services.ServiceIntent;

import java.io.File;
import java.util.Calendar;




public class CadastroProdutosActivity extends AppCompatActivity {
    EditText nomeProduto;
    EditText quantProd;
    EditText valiProd;
    Button registro;
    private String mCurrentPhotoPath;
    private ImageView imgPhoto;
    ImageView btnUploadImage;
    private File cameraImageFile;
    Uri selectedImage;
    Button validadeB;
    private DatePickerDialog.OnDateSetListener mDateSetListener;



    @Override
    public void onActivityResult (int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            if (requestCode == 1 && null != data) {

                selectedImage = data.getData();

                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage,filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                mCurrentPhotoPath = cursor.getString(columnIndex);
                DataManager.getInstance().setImageUrl(mCurrentPhotoPath);
                cursor.close();

            } else if (requestCode == 2) {

                mCurrentPhotoPath = cameraImageFile.getAbsolutePath();
            }

            File image = new File(mCurrentPhotoPath);
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);
            imgPhoto = (ImageView)findViewById(R.id.photoId);
            imgPhoto.setImageBitmap(bitmap);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_produtos);
        btnUploadImage = (ImageView) findViewById(R.id.btnUpload);
        btnUploadImage.setOnClickListener(chooseImageListener);
        validadeB = (Button) findViewById(R.id.calendarButton);
        valiProd = (EditText) findViewById(R.id.validProd);

        validadeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        CadastroProdutosActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date = dayOfMonth+"/"+(monthOfYear+1)+"/"+year;
                valiProd.setText(date);
            }
        };



    }

    View.OnClickListener chooseImageListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            dialogChooseFrom();
        }
    };


    private void dialogChooseFrom(){
        final CharSequence[] items ={"Galeria"};
        AlertDialog.Builder chooseDialog = new AlertDialog.Builder(this);
        chooseDialog.setTitle("Escolha").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int choice) {
                if(items[choice].equals("Galeria")){
                    Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(galleryIntent, 1);

                }

            }
        });
        chooseDialog.show();
    }


    public void cadastrarProduto(View view){
        nomeProduto = (EditText) findViewById(R.id.nomeProduto);
        quantProd = (EditText) findViewById(R.id.quantidadeProduto);
        valiProd = (EditText) findViewById(R.id.validProd);
        registro = (Button) findViewById(R.id.button_register);

        Intent intentService = new Intent(this,ServiceIntent.class);
        intentService.putExtra("nomeProd",nomeProduto.getText().toString());
        intentService.putExtra("quantProd",quantProd.getText().toString());
        intentService.putExtra("validade",valiProd.getText().toString());
        intentService.putExtra("uri",selectedImage);
        intentService.putExtra("path",mCurrentPhotoPath);
        registro.setEnabled(false);
        startService(intentService);


    }

}
