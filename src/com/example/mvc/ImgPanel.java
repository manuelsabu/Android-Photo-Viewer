package com.example.mvc;

/**
 * Created by Manuel on 2016-04-03.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.*;
import android.graphics.drawable.*;
import android.view.View;
import android.view.Gravity;
import android.graphics.*;


public class ImgPanel extends LinearLayout {
    RatingBar rate;
    ImageView img;

    public ImgPanel(Context context, Bitmap i, final Model model) {
        super(context);
        this.setOrientation(VERTICAL);
        //Drawable drawable = new BitmapDrawable(context.getResources(), i);
        img = new ImageView(context);
        img.setImageBitmap(i);
        //img.setLayoutParams(new LayoutParams());
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //img.setBackgroundColor(Color.argb(255, 255, 175, 64));
        rate = new RatingBar(context);
        rate.setNumStars(5);
        rate.setStepSize(1);
        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                if (fromUser) {
                    model.setRating(ratingBar.getRating());
                }
            }
        });

        LayoutParams lp = new LayoutParams( LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT );
        lp.gravity = Gravity.CENTER_HORIZONTAL;

        rate.setLayoutParams(lp);

        //img.setBackground(drawable);
        this.addView(img);
        this.addView(rate);
        //img.setImageBitmap(i);
    }

}
