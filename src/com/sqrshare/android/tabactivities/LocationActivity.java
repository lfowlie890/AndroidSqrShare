package com.sqrshare.android.tabactivities;

import java.io.IOException;
import java.util.List;

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
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.ScrollView;
import android.widget.TextView;


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
			//int latitude = (int) (Double.parseDouble(o.getString("latitude")) * 1000000);
			//int longitude = (int) (Double.parseDouble(o.getString("longitude")) * 1000000);
			String address = o.getString("street");
			address += "\n" + o.getString("city");
			address += "\n" + o.getString("province_name");
			address += "\n" + o.getString("country_name");
			String phone = o.getString("phone");
			Geocoder gc = new Geocoder(this);
			Address adr = null;
			
			JSONObject web = json.getJSONObject("field_company_website");
			und = web.getJSONArray("und");
			o = und.getJSONObject(0);
			String url = o.getString("url");
			try {
				adr = gc.getFromLocationName(address, 1).get(0);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (adr != null){
				
				int latitude = (int) (adr.getLatitude() * 1000000);
				int longitude = (int) (adr.getLongitude() * 1000000);
		        mapController.animateTo(new GeoPoint(latitude, longitude));
		        mapController.setZoom(18);
			}
			TextView locText = (TextView) findViewById(R.id.location_text);
			TextView urlText = (TextView) findViewById(R.id.url_text);
			locText.setText(address + "\n" + phone);
			urlText.setText(url);
			Linkify.addLinks(locText, Linkify.PHONE_NUMBERS);
			Linkify.addLinks(urlText, Linkify.WEB_URLS);
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
