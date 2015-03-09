
//  ******* Documented in 1393/12/16 *******
//  in activity baraye namayesh list notification ha mibashad *

package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.ipa.Adapter.Ad_Notification;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;

public class Act_ListNotification extends Activity {
	Context context;
    String[] Ar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=this;
		setContentView(R.layout.act_list_notif);
		final ListView lstnotification=(ListView)findViewById(R.id.lstnotification);
		super.onCreate(savedInstanceState);

		MethodHelper.CallWebhelper(context).SendRequest("GETNOTIFICATION", null, null, true, new RunnableOnCompletion() {

            @Override
            public void run(String[] arResponse) {
                Ar = new String[arResponse.length - 1];
                System.arraycopy(arResponse, 1, Ar, 0, arResponse.length - 1);
                ((Activity) context).runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        ((Activity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lstnotification.setAdapter(new Ad_Notification((Activity) context, Ar));
                            }
                        });
                    }
                });
            }
        });
	}
}