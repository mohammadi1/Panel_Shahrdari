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

import com.ipa.Panel_Shahrdari.Act_answer;
import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.ValueKeeper;
import com.ipa.Tools.WebServiceHelper;

public class Ad_Shahrdar extends BaseAdapter {
	Activity act;
	String[] Ar;
	
	WebServiceHelper helper;

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Ar.length;
	}

	public Ad_Shahrdar(Activity act, String[] ar) {
		super();
		this.act = act;
		Ar = ar;
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
	public View getView(int position, View arg1, ViewGroup arg2) {
		LayoutInflater inflater = act.getLayoutInflater();
		View v = inflater.inflate(R.layout.ad_question_shahrdar, null);
		Typeface face = Typeface.createFromAsset(act.getAssets(), "BROYA_0.TTF");
		TextView txtshahrdar = (TextView) v.findViewById(R.id.txtshahrdar);
		TextView txtTITLE = (TextView) v.findViewById(R.id.txtTITLE);
		
		Button btnanswer = (Button) v.findViewById(R.id.btnanswer);
		btnanswer.setTypeface(face);
		String[] sp = MethodHelper.Split(Ar[position], ValueKeeper.Sp2);
		txtTITLE.setText(sp[5]);
	    txtshahrdar.setText(sp[3]);
	    final String question=txtshahrdar.getText().toString();
		txtshahrdar.setTypeface(face);
		btnanswer.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent=new Intent(act,Act_answer.class);
				intent.putExtra("ques", question);
				act.startActivity(intent);
			}
		});
		return v;
	}

}
