package com.example.pipef.exampleocr;

import android.app.Activity;
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

public class MainActivity extends Activity {
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
                Integer alto = image.getHeight();
                Integer ancho = image.getWidth();
                //calcular todos los valores del rectagunlo
                Integer ubicacionImagenPrcntA = (int)(ancho * (33.33334f /100.0f));
                Integer ubicacionImagenPrcntb = (int)(alto * (5.859375f /100.0f));
                Integer ubicacionImagenPrcntc = (int)(ancho * (33.33334f /100.0f));
                Integer ubicacionImagenPrcntd = (int)(alto * (5.468750f /100.0f));
                imagePC = Bitmap.createBitmap(image,ubicacionImagenPrcntA,ubicacionImagenPrcntb,ubicacionImagenPrcntc,ubicacionImagenPrcntd);
                //calcular todos los valores del rectagunlo
                ubicacionImagenPrcntA = (int)(ancho * (34.72223f /100.0f));
                ubicacionImagenPrcntb = (int)(alto * (52.734375f /100.0f));
                ubicacionImagenPrcntc = (int)(ancho * (33.33334f /100.0f));
                ubicacionImagenPrcntd = (int)(alto * (3.906250f /100.0f));
                imageHP = Bitmap.createBitmap(image,ubicacionImagenPrcntA,ubicacionImagenPrcntb,ubicacionImagenPrcntc,ubicacionImagenPrcntd);
                //calcular todos los valores del rectagunlo
                ubicacionImagenPrcntA = (int)(ancho * (54.861112f /100.0f));
                ubicacionImagenPrcntb = (int)(alto * (79.296875f /100.0f));
                ubicacionImagenPrcntc = (int)(ancho * (11.11112f /100.0f));
                ubicacionImagenPrcntd = (int)(alto * (3.1250f /100.0f));
                imageDust = Bitmap.createBitmap(image,ubicacionImagenPrcntA,ubicacionImagenPrcntb,ubicacionImagenPrcntc,ubicacionImagenPrcntd);
                //calcular todos los valores del rectagunlo
                ubicacionImagenPrcntA = (int)(ancho * (24.305556f /100.0f));
                ubicacionImagenPrcntb = (int)(alto * (44.531250f /100.0f));
                ubicacionImagenPrcntc = (int)(ancho * (55.555556f /100.0f));
                ubicacionImagenPrcntd = (int)(alto * (6.250f /100.0f));
                imageName = Bitmap.createBitmap(image,ubicacionImagenPrcntA,ubicacionImagenPrcntb,ubicacionImagenPrcntc,ubicacionImagenPrcntd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imageView.setImageBitmap(image);
            processImage();
        }
    }


}
