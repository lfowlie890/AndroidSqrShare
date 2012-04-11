package com.sqrshare.android.tabactivities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sqrshare.android.MainCompanyActivity;
import com.sqrshare.android.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.View;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class PhotosActivity extends Activity {

	private Context context;
	String[] imageUrls;
	Bitmap[] images;
	ImageView selected;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos_tab);
		context = this;
		selected = (ImageView) findViewById(R.id.selected_image);
		JSONObject json = MainCompanyActivity.getJSON();
		if (json != null){
			try {
				JSONObject body = json.getJSONObject("field_images");
				JSONArray und = body.getJSONArray("und");
				int size = und.length();
				imageUrls = new String[size];
				images = new Bitmap[size];
				for (int i = 0; i < size; i++){
					JSONObject o = und.getJSONObject(i);
					imageUrls[i] = "http://sqrs.co/sites/default/files/styles/iphone_image/public/" + o.getString("filename");
					images[i] = downloadFile(imageUrls[i]);
				}
				selected.setImageBitmap(images[0]);
				Gallery gallery = (Gallery) findViewById(R.id.gallery);
				 
		        gallery.setAdapter(new ImageAdapter(this, images));        
		        gallery.setOnItemClickListener(new OnItemClickListener() 
		        {
		            public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
		            {               
		                selected.setImageBitmap(images[position]);
		            }
		        });
				
			} catch (JSONException e2) {
				e2.printStackTrace();
			}
		}
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
