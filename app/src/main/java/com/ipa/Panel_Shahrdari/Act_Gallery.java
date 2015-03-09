package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_Gallery;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;

public class Act_Gallery extends ActionBarActivity{
	Context context;
    Activity act;
    String[] list_img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context=this;
        act=this;
		setContentView(R.layout.act_galery);
		MethodHelper.ShowActionBar(context,this,R.string.str_galery);
		final ListView lstimaggalery=(ListView)findViewById(R.id.lstimaggalery);
		MethodHelper.CallWebhelper(context).SendRequest("GETPICS", null, null, true, new RunnableOnCompletion() {

			
			@Override
			public void run(String[] arResponse) {
			list_img=new String[arResponse.length-1];
				System.arraycopy(arResponse, 1, list_img, 0, arResponse.length-1);
                act.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						lstimaggalery.setAdapter(new Ad_Gallery((Activity)context, list_img));
					}
				});
				
		
			}
		});
		

	}

}
