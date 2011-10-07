package com.sqrshare.android.tabactivities;

import com.sqrshare.android.R;
import com.sqrshare.android.connections.RemoteDBAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CompanyInfoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        RemoteDBAdapter db = new RemoteDBAdapter("http://sqrs.co/iphone");
		try {
			db.nodeGet(41);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			String s = db.nodeGet(41).toString();
			textview.setText(s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        setContentView(textview);
	}

}
