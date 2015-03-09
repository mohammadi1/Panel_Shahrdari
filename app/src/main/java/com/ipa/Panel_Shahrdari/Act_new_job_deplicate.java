package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_NewJobDeplicate;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.WebServiceHelper;

public class Act_new_job_deplicate extends ActionBarActivity{
	
	WebServiceHelper helper;
	Context context;
    Activity act;
    String[] Ar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context=this;
        act=this;
		setContentView(R.layout.act_new_job);
		helper=new WebServiceHelper(context);
		final ListView lstnewjob=(ListView)findViewById(R.id.lstnewjob);
		helper.SendRequest("GETNEWJOB", null, null, true, new RunnableOnCompletion() {
			
			@Override
			public void run(String[] arResponse) {
			Ar=new String[arResponse.length-1];
				System.arraycopy(arResponse, 1, Ar, 0, arResponse.length-1);
                act.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						lstnewjob.setAdapter(new Ad_NewJobDeplicate(act,Ar));
					}
				});
			}
		});
}

}
