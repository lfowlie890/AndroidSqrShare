package com.sqrshare.android.tabactivities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.sqrshare.android.MainCompanyActivity;
import com.sqrshare.android.R;
import android.app.Activity;
import android.os.Bundle;


public class LocationActivity extends MapActivity{
	
	private MapView mapView;
	private MapController mapController;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
        setContentView(R.layout.map_tab);
		JSONObject json = MainCompanyActivity.getJSON();
        mapView = (MapView) findViewById(R.id.mapview);
        mapController = mapView.getController();
		try {
			JSONObject loc = json.getJSONObject("field_company_location");
			JSONArray und = loc.getJSONArray("und");
			JSONObject o = und.getJSONObject(0);
			int latitude = (int) (o.getDouble("latitude") * 1000000);
			int longitude = (int) (o.getDouble("longitude") * 1000000);
	        mapController.animateTo(new GeoPoint(latitude, longitude));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
