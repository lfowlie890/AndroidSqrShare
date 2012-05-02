package com.sqrshare.android.tabactivities;

import org.json.JSONException;
import org.json.JSONObject;

import com.sqrshare.android.LoginActivity;
import com.sqrshare.android.MainCompanyActivity;
import com.sqrshare.android.R;
import com.sqrshare.android.connections.RemoteDBAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

public class CommentActivity extends Activity {
	private SharedPreferences userInfo;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		userInfo = getSharedPreferences("user_info", 0);
		if (!userInfo.getBoolean("logged_in", false)){
			startActivity(new Intent(this, LoginActivity.class));
		}
		setContentView(R.layout.comment);
		
	}
	
	public void postComment(View v){
		InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
		RemoteDBAdapter db = new RemoteDBAdapter("http://sqrs.co/iphone");
		TextView comment_text = (TextView) findViewById(R.id.comment_text);
		String comment = comment_text.getText().toString();
		JSONObject json = MainCompanyActivity.getJSON();
	    if (json != null) {
	    	try {
				String nodeID = json.getString("nid");
				int response = db.comment(comment, nodeID);
				if (response == 200){
					Toast.makeText(this, "Thanks for your feedback!", Toast.LENGTH_SHORT).show();
					comment_text.setText("");
				}
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
	}
	
}
