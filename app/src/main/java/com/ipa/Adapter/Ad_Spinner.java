package com.ipa.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.PersianReshape;

public class Ad_Spinner extends BaseAdapter{

	Activity act;
	int arrValues[];
	int arrImages[];
	LayoutInflater myInflater;
	
	public Ad_Spinner(Activity act, int arrValues[], int arrImages[])
	{
		this.act = act;
		this.arrImages=arrImages;
		this.arrValues=arrValues;
		myInflater = act.getLayoutInflater();
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arrImages.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) 
	{
		ViewHolder holder;
		if(convertView == null)
		{
		
			convertView = myInflater.inflate(R.layout.act_spinner_row, null);
			holder = new ViewHolder();
			holder.lblText = (TextView) convertView.findViewById(R.id.spinnerText);
			holder.imgList = (ImageView) convertView.findViewById(R.id.profile);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.lblText.setText(PersianReshape.reshape(act.getString(arrValues[position])));
		holder.lblText.setTypeface(MethodHelper.getTypeFace(act));
		holder.imgList.setImageResource(arrImages[position]);
		
		return convertView;
	}

	private class ViewHolder
	{
		TextView lblText;
		ImageView imgList;
	}
	
}
