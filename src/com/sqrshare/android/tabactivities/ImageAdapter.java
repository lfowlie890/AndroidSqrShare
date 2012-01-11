package com.sqrshare.android.tabactivities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter{
	
    private Context context;
    private String[] imageUrls = null;
    private ImageView[] imageViews = null;

    public ImageAdapter(Context c, String[] images) {
        context = c;
        imageUrls = images;
        if (imageUrls != null)
        	imageViews = new ImageView[imageUrls.length];
    }

    public int getCount() {
        return imageUrls.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
    	if (convertView == null) {  // if it's not recycled, initialize some attributes
        	imageViews[position] = new ImageView(context);
        	imageView = imageViews[position];
        	imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(1, 1, 1, 1);
        } else {
            imageView = (ImageView) convertView;
        }
		Bitmap bm = downloadFile(imageUrls[position]);
		imageViews[position].setImageBitmap(bm);
        return imageView;
    }
    
    private Bitmap downloadFile(String fileUrl){
    	Bitmap bmImg = null;
    	URL myFileUrl = null; 
    	try {
    		myFileUrl= new URL(fileUrl);
    	} 
    	catch (MalformedURLException e) {
    		e.printStackTrace();
    	}
    	try {
	    	HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
	    	conn.setDoInput(true);
	    	conn.connect();
	    	InputStream is = conn.getInputStream();
	    	bmImg = BitmapFactory.decodeStream(is);
    	}
    	catch (IOException e) {
    		e.printStackTrace();
    	}
    	return bmImg;
    }
}
