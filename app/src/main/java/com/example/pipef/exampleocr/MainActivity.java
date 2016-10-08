package com.example.pipef.exampleocr;

import android.content.Intent;
import android.graphics.BitmapRegionDecoder;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import com.googlecode.tesseract.android.TessBaseAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    Bitmap image; //our image
    Bitmap imagePC;
    Bitmap imageHP;
    Bitmap imageDust;
    Bitmap imageName;
    private TessBaseAPI mTess; //Tess API reference
    String datapath = ""; //path to folder containing language data file

    ImageView imageView;
    Button button;

    private static final int PICK_IMGAGE = 100;
    Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageView);
        button = (Button)findViewById(R.id.IMGButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
                    public void onClick(View v){
                openGallery();
            }
        });

        datapath = getFilesDir()+ "/tesseract/";

        //make sure training data has been copied
        checkFile(new File(datapath + "tessdata/"));

        //init Tesseract API
        String language = "eng";

        mTess = new TessBaseAPI();
        mTess.init(datapath, language);
    }

    private void copyFiles() {
        try {
            //location we want the file to be at
            String filepath = datapath + "/tessdata/eng.traineddata";

            //get access to AssetManager
            AssetManager assetManager = getAssets();

            //open byte streams for reading/writing
            InputStream instream = assetManager.open("tessdata/eng.traineddata");
            OutputStream outstream = new FileOutputStream(filepath);

            //copy the file to the location specified by filepath
            byte[] buffer = new byte[1024];
            int read;
            while ((read = instream.read(buffer)) != -1) {
                outstream.write(buffer, 0, read);
            }
            outstream.flush();
            outstream.close();
            instream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFile(File dir) {
        //directory does not exist, but we can successfully create it
        if (!dir.exists()&& dir.mkdirs()){
            copyFiles();
        }
        //The directory exists, but there is no data file in it
        if(dir.exists()) {
            String datafilepath = datapath+ "/tessdata/eng.traineddata";
            File datafile = new File(datafilepath);
            if (!datafile.exists()) {
                copyFiles();
            }
        }
    }

    public void processImage(){
        TextView OCRTextView = (TextView) findViewById(R.id.OCRTextView);
        TextView OCRTextView2 = (TextView) findViewById(R.id.OCRTextView2);
        TextView OCRTextView3 = (TextView) findViewById(R.id.OCRTextView3);
        TextView OCRTextView4 = (TextView) findViewById(R.id.OCRTextView4);

        if(image != null) {
            String OCRresult = null;
            mTess.setImage(imagePC);
            OCRresult = mTess.getUTF8Text();
            DataGetter data = new DataGetter();
            OCRTextView.setText("PC: "+data.getCp(OCRresult).toString());

            OCRresult = null;
            mTess.setImage(imageHP);
            OCRresult = mTess.getUTF8Text();
            data = new DataGetter();
            OCRTextView2.setText("PS: "+data.getHp(OCRresult).toString());

            OCRresult = null;
            mTess.setImage(imageName);
            OCRresult = mTess.getUTF8Text();
            data = new DataGetter();
            OCRTextView3.setText("Nombre: "+data.getName(OCRresult));

            OCRresult = null;
            mTess.setImage(imageDust);
            OCRresult = mTess.getUTF8Text();
            data = new DataGetter();
            OCRTextView4.setText("Polvos: "+data.getDust(OCRresult));
        }else{
            OCRTextView.setText("Selecciona una imagen primero");
        }
    }

    private void openGallery(){
        Intent galllery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galllery, PICK_IMGAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            imageUri = data.getData();
            try {
                image = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                imagePC = Bitmap.createBitmap(image,500,170,480,120);
                imageHP = Bitmap.createBitmap(image,500,1350,480,80);
                imageDust = Bitmap.createBitmap(image,790,2030,160,80);
                imageName = Bitmap.createBitmap(image,350,1140,800,160);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(image);
            processImage();
        }
    }
}
