package com.ipa.Panel_Shahrdari;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.ipa.Tools.MethodHelper;

public class Act_incantation extends ActionBarActivity{
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=this;
		setContentView(R.layout.act_incantation);
        MethodHelper.ShowActionBar(context,this,R.string.str_incantation);
		TextView text=(TextView)findViewById(R.id.txtt);
		super.onCreate(savedInstanceState);
	}

}
