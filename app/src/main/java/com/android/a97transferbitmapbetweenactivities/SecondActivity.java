package com.android.a97transferbitmapbetweenactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.a97transferbitmapbetweenactivities.Helper.BitmapHelper;

public class SecondActivity extends AppCompatActivity {

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageView = (ImageView)findViewById(R.id.secondImg);
        imageView.setImageBitmap(BitmapHelper.getInstance().getBitmap());

        Bitmap bitmap =  BitmapHelper.getInstance().getBitmap();
        Toast.makeText(this, ""+bitmap, Toast.LENGTH_SHORT).show();
    }
}