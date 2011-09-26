package com.sqrshare.android;


import com.sqrshare.android.tabactivities.CommentActivity;
import com.sqrshare.android.tabactivities.CompanyInfoActivity;
import com.sqrshare.android.tabactivities.HoursActivity;
import com.sqrshare.android.tabactivities.LocationActivity;
import com.sqrshare.android.tabactivities.PhotosActivity;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;

public class MainCompanyActivity extends TabActivity{
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.company_main);
	
	    Resources res = getResources(); // Resource object to get Drawables
	    TabHost tabHost = getTabHost();  // The activity TabHost
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;
	    
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
	    spec = tabHost.newTabSpec("songs").setIndicator("Songs",
	                      res.getDrawable(R.drawable.ic_tab_hours))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, PhotosActivity.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("Songs",
	                      res.getDrawable(R.drawable.ic_tab_photos))
	                  .setContent(intent);
	    tabHost.addTab(spec);
	    
	    intent = new Intent().setClass(this, CommentActivity.class);
	    spec = tabHost.newTabSpec("songs").setIndicator("Songs",
	                      res.getDrawable(R.drawable.ic_tab_comment))
	                  .setContent(intent);
	    tabHost.addTab(spec);

	    tabHost.setCurrentTab(2);
	}
	
	public void share(View v){
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SqrShare Code");
		shareIntent.putExtra(Intent.EXTRA_TEXT, "");
		startActivity(Intent.createChooser(shareIntent, "title"));
	}

}
