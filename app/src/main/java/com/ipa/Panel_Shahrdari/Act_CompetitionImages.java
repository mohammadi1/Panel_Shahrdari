//  ******* Documented in 1393/12/16 *******
//  in activity baraye nemayesh ax haye ersali mosabege ast*
//  -------Tozih:---------------------------
// baraye nemayeshe ActionBar az method ShoeActionBar estefade mishavad

package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_Gallery;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;

public class Act_CompetitionImages extends ActionBarActivity{
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.act_competition_images);
		MethodHelper.ShowActionBar(context,this,R.string.str_regards_competition);
		final ListView lstimage=(ListView)findViewById(R.id.lstimage);
		MethodHelper.CallWebhelper(context).SendRequest("GETPICS", "1", null, true, new RunnableOnCompletion() {
			
			@Override
			public void run(String[] arResponse) {
			 final String[] img=new String[arResponse.length-1];
			System.arraycopy(arResponse, 1, img, 0, arResponse.length-1);
			((Activity)context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					lstimage.setAdapter(new Ad_Gallery((Activity)context, img));
				}
			});
			}
		});

	}

}
