
//  ******* Documented in 1393/12/16 *******
//  in activity baraye namayeshe tasavire ersali ast*
//  -------Tozih:---------------------------
//   dar hengam ersak darkhast be web adade 1 marbot be tasavir mosabeghe va
//  adade 0 baraye tasavire ersali ast


package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_Gallery;
import com.ipa.Tools.Cls_DetailImage;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.ValueKeeper;

import java.util.ArrayList;

public class Act_ImagesReceived extends ActionBarActivity {
	Context context;
	ArrayList<Cls_DetailImage> lst_img=new ArrayList<Cls_DetailImage>();
	Cls_DetailImage model1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context = this;
		setContentView(R.layout.act_image_received);
		MethodHelper.ShowActionBar(context,this,R.string.str_regards_images);

		final ListView lstimage=(ListView)findViewById(R.id.lstimage);
		MethodHelper.CallWebhelper(context).SendRequest("GETPICS", "0", null, true, new RunnableOnCompletion() {
			
			@Override
			public void run(String[] arResponse) {
			String[] img=new String[arResponse.length-1];
			System.arraycopy(arResponse, 1, img, 0, arResponse.length-1);
			for (int i = 0; i < img.length; i++) {
			String[] sp=MethodHelper.Split(img[i], ValueKeeper.Sp2);
			String address=sp[4];
			String id=(sp[0]);
			model1=new Cls_DetailImage(address, id);
                lst_img.add(model1);
			}
			((Activity)context).runOnUiThread(new Runnable() {
				@Override
				public void run() {
					lstimage.setAdapter(new Ad_Gallery((Activity)context, lst_img));
				}
			});
			}
		});

	}

}
