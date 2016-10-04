package com.example.mvc;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import java.lang.reflect.Field;
import android.content.*;

public class Model extends Observable {
	private int counter;
    public float filterRate;
    public ArrayList<ImgPanel> photos = new ArrayList<ImgPanel>();
    public boolean isFit = false;
	Model() {
		counter = 0;
	}

	// Data methods

    public void setRating(float val){
        filterRate = val;
        setChanged();
        notifyObservers();
    }
	public int getCounterValue() {
		return counter;
	}

	public void delete(){
		photos.clear();
		setChanged();
		notifyObservers();
	}
	public void setCounterValue(int i) {
		counter = i;
		Log.d("MVC", "Model: set counter to " + counter);
		setChanged();
		notifyObservers();
	}

	public void loadImage() {
		setChanged();
		notifyObservers();
	}

	// Observer methods
	@Override
	public void addObserver(Observer observer) {
		Log.d("MVC", "Model: Observer added");
		super.addObserver(observer);
	}

	@Override
	public synchronized void deleteObservers() {
		super.deleteObservers();
	}

	@Override
	public void notifyObservers() {
		Log.d("MVC", "Model: Observers notified");
		super.notifyObservers();
	}

	@Override
	protected void setChanged() {
		super.setChanged();
	}

	@Override
	protected void clearChanged() {
		super.clearChanged();
	}
}