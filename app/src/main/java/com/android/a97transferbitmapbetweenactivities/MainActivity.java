package com.android.a97transferbitmapbetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.a97transferbitmapbetweenactivities.Helper.BitmapHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btnNext,btnLoad;
    ImageView firstImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad = findViewById(R.id.btnLoad);
        btnNext = findViewById(R.id.btnNext);
        firstImage = findViewById(R.id.firstImg);

        if (Build.VERSION.SDK_INT>9){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll()
                    .build();
            StrictMode.setThreadPolicy(policy);
        }

        btnLoad.setOnClickListener(view -> {
            Bitmap bitmap = loadBitmapFromUrl("https://i.pinimg.com/736x/d7/a1/f6/d7a1f69dddea5ef44b4698dfe6f0b6db.jpg");
            firstImage.setImageBitmap(bitmap);

            //todo 1 send to activity
            BitmapHelper.getInstance().setBitmap(bitmap);
        });

        //todo 2 send to activity (todo 3 Second Activity)
        btnNext.setOnClickListener(view -> {
            if (BitmapHelper.getInstance().getBitmap() == null){
                Toast.makeText(this, "Bitmap cant't null", Toast.LENGTH_SHORT).show();
            }else{
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
    }

    private Bitmap loadBitmapFromUrl(String sourceLink) {
        try {
            URL url = new URL(sourceLink);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }
}