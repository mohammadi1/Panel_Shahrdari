
package com.ipa.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.Cls_CustomJobs;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.PersianReshape;
import com.ipa.Tools.Search_Jobs;

import java.util.ArrayList;

public class Ad_SearchJobs extends BaseAdapter{

	Activity act;
	String arrImages[];
	String arrLabels[];
	ArrayList<Cls_CustomJobs> list;
	ArrayList<Search_Jobs> jobList;
	LayoutInflater myInflater;
	
//	public Search_Jobs_Adapter(Activity act,ArrayList<Custom_jobs> list)
//	{
//		this.act = act;
//		this.list = list;
//		myInflater = act.getLayoutInflater();
//	}
	
	public Ad_SearchJobs(Activity act, String arrLabels[], String arrImages[])
	{
		this.act = act;
		this.arrImages = arrImages;
		this.arrLabels = arrLabels;
		myInflater = act.getLayoutInflater();
	}
	
	public Ad_SearchJobs(Activity act, ArrayList<Search_Jobs> list)
	{
		this.act = act;
		this.jobList = list;
		myInflater = act.getLayoutInflater();
	}
	
	@Override
	public int getCount() {
//		return arrLabels.length;
		return jobList.size();
	}

	@Override
	public Object getItem(int position) {
		return position;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null)
		{
			convertView = myInflater.inflate(R.layout.ad_search_job, null);
			holder = new ViewHolder();
			holder.imgList = (ImageView) convertView.findViewById(R.id.img_Search_Job_Adapter);
			holder.lblList = (TextView) convertView.findViewById(R.id.lbl_Search_Job_Adapter);
			convertView.setTag(holder);
		}
		else
		{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.imgList.setImageResource(jobList.get(position).image);
		holder.lblList.setText(PersianReshape.reshape(jobList.get(position).label));
		holder.lblList.setTypeface(MethodHelper.getTypeFace(act));
		
		return convertView;
	}
	
	private class ViewHolder
	{
		ImageView imgList;
		TextView lblList;
	}
	
}
