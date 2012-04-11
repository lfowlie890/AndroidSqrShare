package com.sqrshare.android.tabactivities;

import org.json.JSONArray;
import org.json.JSONException;
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
        if (json != null) {
			try {
				JSONObject from_time = json.getJSONObject("field_repeat_hours_from");
				JSONArray und = from_time.getJSONArray("und");
				JSONObject o = und.getJSONObject(0);
				String from = o.getString("value");
				JSONObject to_time = json.getJSONObject("field_repeat_hours_to");
				und = to_time.getJSONArray("und");
				o = und.getJSONObject(0);
				String to = o.getString("value");
				JSONObject repeats = json.getJSONObject("field_repeat_days");
				und = repeats.getJSONArray("und");
				for (int i = 0; i < und.length(); i++){
					o = und.getJSONObject(i);
					String day = o.getString("value");
					if (day.equals(days[0]))
						hours[0] = from + " - " + to;
					else if (day.equals(days[1]))
						hours[1] = from + " - " + to;
					else if (day.equals(days[2]))
						hours[2] = from + " - " + to;
					else if (day.equals(days[3]))
						hours[3] = from + " - " + to;
					else if (day.equals(days[4]))
						hours[4] = from + " - " + to;
					else if (day.equals(days[5]))
						hours[5] = from + " - " + to;
					else if (day.equals(days[6]))
						hours[6] = from + " - " + to;
				}
			}
			catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
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
			TextView daysView = (TextView) rowView.findViewById(R.id.day);
			daysView.setText(days[position]);
			TextView hoursView = (TextView) rowView.findViewById(R.id.hours);
			hoursView.setText(hours[position]);
			return rowView;
		}
		
	}

}
