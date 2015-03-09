package com.ipa.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ipa.Panel_Shahrdari.Act_EditNotification;
import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.ValueKeeper;

public class Ad_Notification extends BaseAdapter {
	Activity act;
	String[] Ar;
	
	public Ad_Notification(Activity act, String[] ar) {
		super();
		this.act = act;
		Ar = ar;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Ar.length;
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
		View v=inflater.inflate(R.layout.ad_notification, null);
		Typeface face=Typeface.createFromAsset(act.getAssets(), "BYekan.ttf");
		TextView txtnotification1=(TextView)v.findViewById(R.id.txtnotification1);
		txtnotification1.setTypeface(face);
		TextView txttitlnotifi=(TextView)v.findViewById(R.id.txttitlnotifi);
		txttitlnotifi.setTypeface(face);
		Button btnnotification1=(Button)v.findViewById(R.id.btnnotification1);
		btnnotification1.setTypeface(face);
		String[] sp= MethodHelper.Split(Ar[pos], ValueKeeper.Sp2);
		txttitlnotifi.setText(sp[1]);
		txtnotification1.setText(sp[2]);
		final String id=sp[0];
		final String notifi=txtnotification1.getText().toString();
		final String title=txttitlnotifi.getText().toString();
		btnnotification1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(act, Act_EditNotification.class);
				intent.putExtra("notifi", notifi);
				intent.putExtra("title", title);
				intent.putExtra("id", id);
				act.startActivity(intent);
				
			}
		});
		
		return v;
	}

}
