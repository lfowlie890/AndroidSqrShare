package com.sqrshare.android.tabactivities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CompanyInfoActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Artists tab");
        setContentView(textview);
	}

}