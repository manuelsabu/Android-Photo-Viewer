package com.example.mvc;

import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Observer;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.*;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RatingBar.*;

public class View1 extends LinearLayout implements Observer {


	private Model model;

	public View1(Context context, Model m) {
		super(context);
		
	    Log.d("MVC", "View1 constructor");
		
		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.view1, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

        RatingBar filter = (RatingBar) findViewById(R.id.Filter);

        filter.setOnRatingBarChangeListener(new OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                if(fromUser){
                    model.setRating(ratingBar.getRating());
                }
            }
        });

	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
	    Log.d("MVC", "update View1");

		// update button text with click count
	    // (convert to string, or else Android uses int as resource id!)
		//button.setText(String.valueOf(model.getCounterValue()));
	}
}
