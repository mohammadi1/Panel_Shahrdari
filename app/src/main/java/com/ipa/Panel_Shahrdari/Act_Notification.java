
//  ******* Documented in 1393/12/16 *******
//  in activity baraye namayeshe list notification ast *
//  -------Tozih:---------------------------



package com.ipa.Panel_Shahrdari;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.WebServiceHelper;

public class Act_Notification extends ActionBarActivity{
	Context context;
	 WebServiceHelper whelper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=this;
		whelper =new  WebServiceHelper(context);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_notification);
	    MethodHelper.ShowActionBar(context,this,R.string.str_notification);

		Typeface face=Typeface.createFromAsset(getAssets(), "BYekan.ttf");
		Button btnaddcomment=(Button)findViewById(R.id.btnaddcomment);
		btnaddcomment.setTypeface(face);
		Button btneditcomment=(Button)findViewById(R.id.btneditcomment);
		btneditcomment.setTypeface(face);
		btnaddcomment.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(context, Act_SendNotification.class));
				
			}
		});
		
		btneditcomment.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(context, Act_ListNotification.class));
				
			}
		});
		
		
	}

}
