package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_Shahrdar;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.SharedPrefrencesHelper;
import com.ipa.Tools.WebServiceHelper;

public class Act_Shora extends ActionBarActivity {
	Context context;
	WebServiceHelper whelper;
	Activity act;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context = this;
		act=this;
		whelper = new WebServiceHelper(context);
		setContentView(R.layout.act_shora);
	MethodHelper.ShowActionBar(context,this,R.string.str_shora);
		
	
		SharedPrefrencesHelper.setStringPref(context, "RegisterID", "2");
		
		final ListView lstcommentshora=(ListView)findViewById(R.id.lstcommentshora);
		whelper.SendRequest( "GetCONTACTMEMBERS", null,  null, true, new RunnableOnCompletion() {
			
			@Override
			public void run(String[] arResponse) {
			String[] Ar=new String[arResponse.length-1];
			System.arraycopy(arResponse, 1, Ar, 0, arResponse.length-1);
			final Ad_Shahrdar ad=new Ad_Shahrdar((Activity)context, Ar);
			act.runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					lstcommentshora.setAdapter(ad);	
					
				}
			});
			
			}
		});
		super.onCreate(savedInstanceState);
	}

}
