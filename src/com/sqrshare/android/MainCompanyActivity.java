package com.sqrshare.android;


import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import com.sqrshare.android.connections.RemoteDBAdapter;
import com.sqrshare.android.tabactivities.CommentActivity;
import com.sqrshare.android.tabactivities.CompanyInfoActivity;
import com.sqrshare.android.tabactivities.HoursActivity;
import com.sqrshare.android.tabactivities.LocationActivity;
import com.sqrshare.android.tabactivities.PhotosActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TextView;

public class MainCompanyActivity extends TabActivity{
	
	private static JSONObject json = null;
	SharedPreferences favorites = null;
	SharedPreferences history = null;
	String nodeId = "-1";
	boolean favorite = false;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.company_main);
	    favorites = getSharedPreferences("sqrshare_favorites", 0);
	    history = getSharedPreferences("sqrshare_history", 0);
	    if (json != null) {
	    	String jsonString = null;
	    	try {
	    		nodeId = json.getString("vid");
	    		jsonString = favorites.getString(nodeId, null);
	    		if (jsonString != null){
	    			setFavorite();
		    	}
			} catch (JSONException e1) {
				e1.printStackTrace();
			}	
	    }
	    else {
		    System.out.print("URL: ");
		    Bundle extras = getIntent().getExtras();
		    String value = null;
		    if(extras != null)
		    {
		    	value = extras.getString("url");
		    	System.out.println(value);
		    	if (value != null && value.startsWith("http://sqrs.co/r/")){
		    		nodeId = value.substring(17);
		    	}
		    }
		    String jsonString = favorites.getString(nodeId, null);
		    if (jsonString != null){
		    	try {
					json = new JSONObject(jsonString);
				} catch (JSONException e) {
					e.printStackTrace();
				}
		    	
		    	setFavorite();
		    }
		    else if (!nodeId.equals("-1")){
		    //Read more: http://getablogger.blogspot.com/2008/01/android-pass-data-to-activity.html#ixzz1gHchwxVN
			    RemoteDBAdapter db = new RemoteDBAdapter("http://sqrs.co/iphone");
				try {
					json = db.nodeGet(Integer.parseInt(nodeId));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		    else{
		    	//TODO handle nonsqrshare codes
		    }
		    
		    int max_history = Integer.parseInt(getString(R.string.max_history));
		    Editor histEditor = history.edit();
		    int size = history.getAll().size();
		    if (size < max_history){
		    	histEditor.putString("" + System.currentTimeMillis(), json.toString());
		    }
		    else{
		    	Set<String> timestamps = history.getAll().keySet();
		    	String oldest_time = "-1";
		    	for (String t : timestamps){
		    		if (Long.parseLong(oldest_time) < Long.parseLong(t))
		    			oldest_time = t;
		    	}
		    	histEditor.remove(oldest_time);
		    	histEditor.putString("" + System.currentTimeMillis(), json.toString());
		    }
		    histEditor.commit();
	    }
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;
		
	    QRCodeActivity.setId(Integer.parseInt(nodeId));
	    
		TextView title = ((TextView)findViewById(R.id.company_title));
		try {
			title.setText(json.getString("title"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	    
	 // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, CompanyInfoActivity.class);

	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("companyInfo").setIndicator("",
	                      res.getDrawable(R.drawable.ic_tab_company))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    // Do the same for the other tabs
	    intent = new Intent().setClass(this, LocationActivity.class);
	    spec = tabHost.newTabSpec("map").setIndicator("",
	                      res.getDrawable(R.drawable.ic_tab_map))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    intent = new Intent().setClass(this, HoursActivity.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("",
	                      res.getDrawable(R.drawable.ic_tab_hours))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, PhotosActivity.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("",
	                      res.getDrawable(R.drawable.ic_tab_photos))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, CommentActivity.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("",
	                      res.getDrawable(R.drawable.ic_tab_comment))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(0);
	}

	private void setFavorite() {
    	ImageButton favoriteButton = (ImageButton) findViewById(R.id.add_favorites_button);
    	favoriteButton.setImageResource(R.drawable.star);
    	favorite = true;
	}

	public void share(View v){
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		try {
			shareIntent.putExtra(Intent.EXTRA_SUBJECT, "awesome SqrShare deal from " + json.getString("title"));
			shareIntent.putExtra(Intent.EXTRA_TITLE, "awesome SqrShare deal from " + json.getString("title"));
			shareIntent.putExtra(Intent.EXTRA_TEXT, "");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		startActivity(Intent.createChooser(shareIntent, "Share via:"));
	}
	
	public void favoritePressed(View v){
		Editor favEditor = favorites.edit();
		if (favorite){
			favEditor.remove(nodeId);
	    	((ImageButton) v).setImageResource(R.drawable.star_open);
	    	favorite = false;
	    	favEditor.commit();
		}
		else{
			favEditor.putString(nodeId, json.toString());
	    	setFavorite();
	    	favEditor.commit();
		}
	}
	
	public void onStop(){
		json = null;
		super.onStop();
	}
	
	public static JSONObject getJSON(){
		return json;
	}
	public static void setJSON(JSONObject j){
		json = j;
	}

}
