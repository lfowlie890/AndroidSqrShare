package com.sqrshare.android.tabactivities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.sqrshare.android.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	
    private Context context;
    private Bitmap[] images = null;
    private int itemBackground;

    public ImageAdapter(Context c, Bitmap[] images) {
        context = c;
        this.images = images;
        TypedArray a = c.obtainStyledAttributes(R.styleable.Gallery);
        itemBackground = a.getResourceId(
            R.styleable.Gallery_android_galleryItemBackground, 0);
        a.recycle();  
        
    }

    public int getCount() {
        return images.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
//        ImageView imageView;
//    	if (convertView == null) {  // if it's not recycled, initialize some attributes
//        	imageViews[position] = new ImageView(context);
//        	imageView = imageViews[position];
//        	imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//            imageView.setPadding(1, 1, 1, 1);
//        } else {
//            imageView = (ImageView) convertView;
//        }
    	ImageView imageView = new ImageView(context);
		Bitmap bm = images[position];
		imageView.setImageBitmap(bm);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
        imageView.setBackgroundResource(itemBackground);
        return imageView;
    }
}
