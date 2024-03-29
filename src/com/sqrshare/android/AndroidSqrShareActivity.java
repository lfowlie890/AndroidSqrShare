package com.sqrshare.android;

import org.json.JSONObject;

import com.google.zxing.client.android.CaptureActivity;
import com.sqrshare.android.connections.RemoteDBAdapter;
import com.sqrshare.android.user.UserLogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class AndroidSqrShareActivity extends Activity {
    /** Called when the activity is first created. */
	private RelativeLayout tapToScan;
	private GestureDetector detector;
	Context context;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        context = this;
        
        new UserLogin().execute(this);
        
        DoubleTapListener l = new DoubleTapListener();
        detector = new GestureDetector(this, l);
        tapToScan = (RelativeLayout) findViewById(R.id.tap_to_scan);
        tapToScan.setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				detector.onTouchEvent(event);
				return true;
			}
        	
        }
        		);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    
    public void showHistory(View v){
    	startActivity(new Intent(context, HistoryActivity.class));
    }
    
    public void showFavorites(View v){
    	startActivity(new Intent(context, FavoritesActivity.class));
    }
    
    private class DoubleTapListener extends GestureDetector.SimpleOnGestureListener{
    	
    	@Override
    	public boolean onDoubleTapEvent(MotionEvent e){
    		MainCompanyActivity.setJSON(null);
    		startActivity(new Intent(context, CaptureActivity.class));
			return true;
    		
    	}
		
    	
    }
    
}