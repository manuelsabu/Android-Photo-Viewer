package com.example.mvc;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.os.StrictMode;

import java.net.URL;

public class FotagMobile extends Activity {
	Model model;
    public ImageButton Loadbutton;
    public ImageButton SearchButton;
    public ImageButton ClearButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy p = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(p);

		Log.d("MVC", "onCreate");
		// load the base UI (has places for the views)
		setContentView(R.layout.mainactivity);

		// Setup model
		model = new Model();
	}


    public void loadHelp(Context context, Bitmap t1b, int m){
        final Context c = context;
        final int tp = m;
        //final Bitmap bm = t1b;
        Log.d("MVC", "Model: Image Reloaded to ");
        ImgPanel t1 = new ImgPanel(context,t1b,model);
        t1.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(c, PhotoExp.class);
                i.putExtra("id", tp);
                startActivity(i);
            }
        });

        t1.rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                if (fromUser) {
                    model.loadImage();
                }
            }
        });

        model.photos.add(t1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("MVC", "return result");

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.d("MVC", "request code");

            if(resultCode == RESULT_OK){
                Log.d("MVC", "RESULT OK");

                final String search = data.getStringExtra("edittext");
                Log.d("MVC", "term: "+ search);
                try {
                    final URL url = new URL(search);
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    ImgPanel t1 = new ImgPanel(this,bmp, model);
                    final Context c = this;
                    t1.img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(c, PhotoExp.class);
                            i.putExtra("url", search);
                            startActivity(i);
                        }
                    });

                    t1.rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                        @Override
                        public void onRatingChanged(RatingBar ratingBar, float rating,
                                                    boolean fromUser) {
                            if(fromUser){
                                model.loadImage();
                            }
                        }
                    });
                    model.photos.add(t1);
                    model.loadImage();

                }catch(Exception e) {}
            }
        }
    }

    public void loadImg(){
        Bitmap t1b = BitmapFactory.decodeResource(getResources(), R.drawable.bird);
        loadHelp(this, t1b, R.drawable.bird);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.ferrari_360);
        loadHelp(this, t1b, R.drawable.ferrari_360);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.maserati_logo);
        loadHelp(this, t1b,  R.drawable.maserati_logo);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.mclaren_f1_1);
        loadHelp(this, t1b,  R.drawable.mclaren_f1_1);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.mclaren_f1_2);
        loadHelp(this, t1b,  R.drawable.mclaren_f1_2);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.mclaren_f1_3);
        loadHelp(this, t1b,  R.drawable.mclaren_f1_3);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.sample_1);
        loadHelp(this, t1b,  R.drawable.sample_1);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.sample_2);
        loadHelp(this, t1b,  R.drawable.sample_2);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.sample_3);
        loadHelp(this, t1b,  R.drawable.sample_3);
        t1b = BitmapFactory.decodeResource(getResources(), R.drawable.sample_4);
        loadHelp(this, t1b,  R.drawable.sample_4);
        model.loadImage();
    }

    public void searchStuff(){
        Intent i = new Intent(this, Search.class);
        startActivityForResult(i, 1);
    }
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		Log.d("MVC", "onPostCreate");
		// can only get widgets by id in onPostCreate for activity xml res

		// create the views and add them to the main activity
		View1 view1 = new View1(this, model);
		ViewGroup v1 = (ViewGroup) findViewById(R.id.mainactivity_1);
		v1.addView(view1);

		View2 view2 = new View2(this, model);
		ViewGroup v2 = (ViewGroup) findViewById(R.id.mainactivity_2);
		v2.addView(view2);

		// initialize views
		model.setChanged();
		model.notifyObservers();

        Loadbutton = (ImageButton) findViewById(R.id.LoadBut);
        Loadbutton.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                loadImg();
            }
        });

        SearchButton = (ImageButton) findViewById(R.id.SearchBut);
        SearchButton.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
               searchStuff();
            }
        });

        ClearButton = (ImageButton) findViewById(R.id.ClearBut);
        ClearButton.setOnClickListener(new ImageButton.OnClickListener() {
            public void onClick(View v) {
                model.delete();
            }
        });

	}

	// save and restore state (need to do this to support orientation change)

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.d("MVC", "save state");
		outState.putInt("Counter", model.getCounterValue());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		Log.d("MVC", "restore state");
		super.onRestoreInstanceState(savedInstanceState);
		model.setCounterValue(savedInstanceState.getInt("Counter"));
	}

}
