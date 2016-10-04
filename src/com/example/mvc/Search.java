package com.example.mvc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import java.net.URL;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by Manuel on 2016-04-04.
 */
public class Search extends Activity{

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        final EditText search = (EditText) findViewById(R.id.hypertext);
        Button go = (Button) findViewById(R.id.go);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("edittext", search.getText().toString());
                //intent.putExtra("edittext",  "http://www.wired.com/wp-content/uploads/2015/09/google-logo.jpg");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
