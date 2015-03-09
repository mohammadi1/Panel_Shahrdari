
//  ******* Documented in 1393/12/16 *******
//  in Activity baraye namayeshe contact haye shahrdar mibashad *
//  -------Tozih:---------------------------
//  dar in act az method GETCONTACTSHAHRDAR baraye gereftan
// contact haye shahrdar estefade shode ast

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

public class Act_Shahrdar extends ActionBarActivity {
	Context context;
	WebServiceHelper whelper;
	Activity act;
    String[] Ar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context = this;
		act = this;
		whelper = new WebServiceHelper(context);
		setContentView(R.layout.act_shahrdar);
		MethodHelper.ShowActionBar(context,this,R.string.str_shahrdar);
		SharedPrefrencesHelper.setStringPref(context, "RegisterID", "1");
		final ListView lstv_comment = (ListView) findViewById(R.id.lst_comment);
		whelper.SendRequest("GETCONTACTSHAHRDAR", null, null, true, new RunnableOnCompletion() {

			@Override
			public void run(String[] arResponse) {
			  Ar=new String[arResponse.length-1];
				java.lang.System.arraycopy(arResponse, 1, Ar, 0, arResponse.length - 1);
				act.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						lstv_comment.setAdapter(new Ad_Shahrdar((Activity)context, Ar));
					}
				});
			}
		});

	}

}
