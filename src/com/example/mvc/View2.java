package com.example.mvc;

import java.util.Observable;
import java.util.Observer;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.*;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.graphics.*;

public class View2 extends LinearLayout implements Observer {
	private Model model;
    public Context c;
    ImageView imageView;

	public View2(Context context, Model m) {
		super(context);
        c = context;

	    Log.d("MVC", "View2 constructor");

		// get the xml description of the view and "inflate" it
		// into the display (kind of like rendering it)
		View.inflate(context, R.layout.view2, this);

		// save the model reference
		model = m;
		// add this view to model's list of observers
		model.addObserver(this);

	}

	// the model call this to update the view
	public void update(Observable observable, Object data) {
//	    int n = model.getCounterValue();
        Log.d("MVC", "update View2");
//	    StringBuilder s = new StringBuilder(n);
//	    for (int i = 0; i < n; i++) {
//	    	s.append("x");
//	    }
//        Log.d("MVC", "TEst1");

        LinearLayout l1 = (LinearLayout) findViewById(R.id.Cons);
        l1.removeAllViews();

        for(int i = 0; i < model.photos.size(); ++i){
            if(model.photos.get(i).rate.getRating() >= model.filterRate) {
                l1.addView(model.photos.get(i));
            }
        }

	}
}
