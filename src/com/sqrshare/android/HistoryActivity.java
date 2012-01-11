package com.sqrshare.android;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class HistoryActivity extends ListActivity{
	 
	private String[] ids;
	private SharedPreferences history;
	private Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		
		history = getSharedPreferences("sqrshare_history", 0);
		Map<String, ?> historyMap = history.getAll();
		int size = historyMap.size();
		String[] historyNames = new String[size];
		ids = new String[size];
		int i = 0;
		for (Object key : historyMap.keySet()){
			try {
				ids[i] = (String) key;
				historyNames[i++] = new JSONObject((String) historyMap.get(key)).getString("title");
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		setListAdapter(new ArrayAdapter<String>(this, R.layout.popup_list_item, historyNames));
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		      // When clicked, show a toast with the TextView text
		      String jsonString = history.getString(ids[position], null);
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
