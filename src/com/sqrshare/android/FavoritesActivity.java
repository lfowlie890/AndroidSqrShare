package com.sqrshare.android;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.zxing.client.android.CaptureActivity;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class FavoritesActivity extends ListActivity{
	
	private SharedPreferences favorites;
	private Context context;
	String[] ids;
	 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		
		favorites = getSharedPreferences("sqrshare_favorites", 0);
		Map<String, ?> favoritesMap = favorites.getAll();
		int size = favoritesMap.size();
		String[] favoritesNames = new String[size];
		ids = new String[size];
		int i = 0;
		for (Object key : favoritesMap.keySet()){
			try {
				ids[i] = (String) key;
				favoritesNames[i++] = new JSONObject((String) favoritesMap.get(key)).getString("title");
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		setListAdapter(new ArrayAdapter<String>(this, R.layout.popup_list_item, favoritesNames));
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		      // When clicked, show a toast with the TextView text
		      String jsonString = favorites.getString(ids[position], null);
		      if (jsonString != null){
			    	try {
						MainCompanyActivity.setJSON(new JSONObject(jsonString));
						startActivity(new Intent(context, MainCompanyActivity.class));
					} catch (JSONException e) {
						e.printStackTrace();
					}
		      }
			}
				
		});
	}
}
