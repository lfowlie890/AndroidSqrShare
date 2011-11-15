package com.sqrshare.android.tabactivities;

import org.json.JSONObject;

import com.sqrshare.android.MainCompanyActivity;
import com.sqrshare.android.R;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class HoursActivity extends ListActivity {

	private String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	private String[] hours = {"", "", "", "", "", "", ""};
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		JSONObject json = MainCompanyActivity.getJSON();
        HoursListAdapter adapter= new HoursListAdapter(this);
        setListAdapter(adapter);

	}
	
	private class HoursListAdapter extends BaseAdapter{
        private Context context;
		public HoursListAdapter(Context context) {
            this.context = context;
        }
		@Override
		public int getCount() {
			return days.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View view, ViewGroup group) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.hours, null);
			TextView textView = (TextView) rowView.findViewById(R.id.day);
			textView.setText(days[position]);
			return rowView;
		}
		
	}

}
