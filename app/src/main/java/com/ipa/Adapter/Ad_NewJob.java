package com.ipa.Adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ipa.Panel_Shahrdari.R;

import java.util.ArrayList;

public class Ad_NewJob extends BaseAdapter {
	Activity act;
	ArrayList<Bitmap> listimages;

	public Ad_NewJob(Activity act, ArrayList<Bitmap> listimages) {
		super();
		this.act = act;
		this.listimages = listimages;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listimages.size();
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
	public View getView(final int pos, View arg1, ViewGroup arg2) {
		LayoutInflater inflater=act.getLayoutInflater();
		View v=inflater.inflate(R.layout.ad_new_job, null);
		ImageView imgasli=(ImageView)v.findViewById(R.id.imgasli);
		ImageView imgdelet=(ImageView)v.findViewById(R.id.imgdelet);
		imgasli.setImageBitmap(listimages.get(pos));
		imgdelet.setImageResource(R.drawable.ic_delet);
		v.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				listimages.remove(listimages.get(pos));
				notifyDataSetChanged();
				
//				Intent intent=new Intent(act, New_Job_Activity.class);
//				intent.putParcelableArrayListExtra("list", listimages);
//				act.startActivity(intent);
			}
		});
		return v;
	}

}
