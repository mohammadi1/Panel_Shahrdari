//  ******* Documented in 1393/12/16 *******
//  in activity baraye namayesh comment ha mibashad *
//  -------Tozih:---------------------------
//  dar in activity darkhast be web ersal shode va sepas comment ha tavasote adapter
// namayesh dade mishavad



package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_Comment;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;

public class Act_comment extends ActionBarActivity {
	Context context;
	Activity act;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context=this;	
		act=this;
		setContentView(R.layout.act_comment);
	    MethodHelper.ShowActionBar(context,this,R.string.str_comment);
		final ListView lstcomment=(ListView)findViewById(R.id.lstcomment);
		MethodHelper.CallWebhelper(context).SendRequest("GETALLCOMMENTS", null, null, true, new RunnableOnCompletion() {
			
			@Override
			public void run(String[] arResponse) {
				String[] comment=new String[arResponse.length-1];
				System.arraycopy(arResponse, 1, comment, 0, arResponse.length-1);
				final Ad_Comment ad=new Ad_Comment((Activity)context, comment);
				act.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						lstcomment.setAdapter(ad);
						
					}
				});
			}
		});

	}

}
