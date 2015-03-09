package com.ipa.Adapter;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ipa.Panel_Shahrdari.R;

public class Ad_SpinnerNews extends BaseAdapter {
    Activity act;
    String[] group;

	public Ad_SpinnerNews(Activity act, String[] group) {
	super();
	this.act = act;
	this.group = group;
}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return group.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int pos, View arg1, ViewGroup arg2) {
		LayoutInflater inflater=act.getLayoutInflater();
		View v=inflater.inflate(R.layout.ad_spinner_news, null);
		TextView txtspinner=(TextView)v.findViewById(R.id.txtspinner);
		txtspinner.setText(group[pos]);
		return v;
	}

}
