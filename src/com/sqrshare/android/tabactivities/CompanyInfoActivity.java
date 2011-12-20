package com.sqrshare.android.tabactivities;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sqrshare.android.MainCompanyActivity;
import com.sqrshare.android.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CompanyInfoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.description_tab); 
		
		JSONObject json = MainCompanyActivity.getJSON();
		String mainbody = null;
		try {
			JSONObject body = json.getJSONObject("body");
			JSONArray und = body.getJSONArray("und");
			JSONObject o = und.getJSONObject(0);
			mainbody = o.getString("value");
		} catch (JSONException e2) {
			e2.printStackTrace();
		}

        TextView textview = (TextView) findViewById(R.id.description_text);
        textview.setText(mainbody);
        
	}

}
    