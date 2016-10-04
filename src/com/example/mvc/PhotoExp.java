package com.example.mvc;

/**
 * Created by Manuel on 2016-04-04.
 */

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import java.net.URL;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

public class PhotoExp extends Activity{

    int temp;

    boolean isFit = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photoexp);
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if(getIntent().hasExtra("id")) {
            temp = getIntent().getIntExtra("id",0);
            Bitmap t1b = BitmapFactory.decodeResource(getResources(), temp);
            imageView.setImageBitmap(t1b);
        }

        if(getIntent().hasExtra("url")) {
            try {
                URL url = new URL(getIntent().getStringExtra("url"));
                Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                imageView.setImageBitmap(bmp);

            }catch (Exception e){}
        }

        //imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        //imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent();
                    intent.putExtra("edittextvalue", "value_here");
                    setResult(RESULT_OK, intent);
                    finish();
            }
        });

    }
//    Model model;
//    ImageView imageView;
//    boolean isImageFitToScreen;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        Log.d("MVC", "PhotoEXP");
//        // Setup model
//        model = new Model();
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.photoexp);
//
//        imageView = (ImageView) findViewById(R.id.imageView);
//
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isImageFitToScreen) {
//                    isImageFitToScreen = false;
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//                    imageView.setAdjustViewBounds(true);
//                } else {
//                    isImageFitToScreen = true;
//                    imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                }
//            }
//        });
//    }
//
//
//    @Override
//    protected void onPostCreate(Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//
//        Log.d("MVC", "onPostCreate");
//        // can only get widgets by id in onPostCreate for activity xml res
//
//        Intent intent = new Intent();
//        intent.putExtra("edittextvalue","value_here");
//        setResult(RESULT_OK, intent);
//        finish();
//
//        // initialize views
////        model.setChanged();
////        model.notifyObservers();
//
//    }
//
//    // save and restore state (need to do this to support orientation change)
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        Log.d("MVC", "save state");
//        outState.putInt("Counter", model.getCounterValue());
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        Log.d("MVC", "restore state");
//        super.onRestoreInstanceState(savedInstanceState);
//        model.setCounterValue(savedInstanceState.getInt("Counter"));
//    }
}
