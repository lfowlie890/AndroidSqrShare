package com.sqrshare.android.tabactivities;

import java.io.IOException;

import com.sqrshare.android.R;
import com.sqrshare.android.connections.RemoteDBAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class CommentActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

        TextView textview = new TextView(this);
        textview.setText("This is the Artists tab");
        setContentView(R.layout.comment);	
	}
	
	public void logIn(View v){
		RemoteDBAdapter db = new RemoteDBAdapter("http://sqrs.co/iphone");
		try {
			String s = db.login("luke", "19lmf89");
			System.out.println(s);
			db.logout();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
//		DefaultHttpClient httpclient = new DefaultHttpClient();
//        try {
//            httpclient.getCredentialsProvider().setCredentials(
//                    new AuthScope(null, -1),
//                    new UsernamePasswordCredentials("lfowlie890@gmail.com", "19lmf89"));
//
//            HttpGet httpget = new HttpGet("http://sqrs.co/iphone/user/login");
//
//            System.out.println("executing request" + httpget.getRequestLine());
//            HttpResponse response = httpclient.execute(httpget);
//            HttpEntity entity = response.getEntity();
//
//            System.out.println("----------------------------------------");
//            System.out.println(response.getStatusLine());
//            if (entity != null) {
//                System.out.println("Response content length: " + entity.getContentLength());
//            }
//            //EntityUtils.consume(entity);
//        } catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//            // When HttpClient instance is no longer needed,
//            // shut down the connection manager to ensure
//            // immediate deallocation of all system resources
//            httpclient.getConnectionManager().shutdown();
//        }
	}
}
