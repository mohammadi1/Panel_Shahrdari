package com.ipa.Adapter;


import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.ValueKeeper;
import com.ipa.Tools.WebServiceHelper;

public class Ad_Comment extends BaseAdapter {
	Activity act;
	String[] comment;
	String an;
	WebServiceHelper whelper;
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return comment.length;
	}

	public Ad_Comment(Activity act, String[] comment) {
		super();
		this.act = act;
		this.comment = comment;
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
		View v=inflater.inflate(R.layout.ad_comment, null);
		Typeface face=Typeface.createFromAsset(act.getAssets(), "BYekan.ttf");
		TextView txtcomment=(TextView)v.findViewById(R.id.txtcomment);
		txtcomment.setTypeface(face);
		Button btntaed=(Button)v.findViewById(R.id.btntaedc);
		btntaed.setTypeface(face);
		Button btndelet=(Button)v.findViewById(R.id.btndeletc);
		btndelet.setTypeface(face);
		whelper=new WebServiceHelper(act);
		
		final String[] sp= MethodHelper.Split(comment[pos], ValueKeeper.Sp2);
		txtcomment.setText(sp[2]);
		
		
		btndelet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				an = "0";
				int id=Integer.parseInt(sp[0]);
				whelper.SendRequest("CONFIRMPOSTS", id + "|" + an, null, true, new RunnableOnCompletion() {

					@Override
					public void run(String[] arResponse) {
						act.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(act, "این کامنت حذف شد", Toast.LENGTH_SHORT).show();
							}
						});
					}

				});
			}
		});
		btntaed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				an = "1";
				int id=Integer.parseInt(sp[0]);
				whelper.SendRequest("CONFIRMPOSTS", id + "|" + an, null, true, new RunnableOnCompletion() {

					@Override
					public void run(String[] arResponse) {
						act.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(act, "این کامنت تایید شد", Toast.LENGTH_SHORT).show();
							}
						});
					}

				});
			}
		});

				return v;
	}

}
