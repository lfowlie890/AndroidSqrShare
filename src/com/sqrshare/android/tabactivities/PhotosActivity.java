package com.sqrshare.android.tabactivities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sqrshare.android.MainCompanyActivity;
import com.sqrshare.android.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

public class PhotosActivity extends Activity {

	private Context context;
	GridView gridview;
	String[] imageUrls = null;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photos_tab);
		context = this;
		gridview = (GridView) findViewById(R.id.photo_grid);
		JSONObject json = MainCompanyActivity.getJSON();
		try {
			JSONObject body = json.getJSONObject("field_images");
			JSONArray und = body.getJSONArray("und");
			int size = und.length();
			imageUrls = new String[size];
			for (int i = 0; i < size; i++){
				JSONObject o = und.getJSONObject(i);
				imageUrls[i] = "http://sqrs.co/sites/default/files/styles/iphone_image/public/" + o.getString("filename");
			}
			
		} catch (JSONException e2) {
			e2.printStackTrace();
		}
		if (imageUrls != null){
			gridview.setAdapter(new ImageAdapter(context, imageUrls));
		}
//	    gridview.setOnItemClickListener(new OnItemClickListener() {
//	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
//	            Toast.makeText(HelloGridView.this, "" + position, Toast.LENGTH_SHORT).show();
//	        }
//	    });
	}

}
