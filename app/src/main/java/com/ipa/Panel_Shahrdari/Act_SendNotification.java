
//  ******* Documented in 1393/12/16 *******
//  in Activity baraye ersal notificatioon mibashad*
//  -------Tozih:---------------------------
//   Notification tavasote method SENDNOTIFICATION ersal mishvad

package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;

public class Act_SendNotification extends Activity{
	Context context;
	String title;
	String text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_send_notif);
		Typeface face=Typeface.createFromAsset(getAssets(), "BYekan.ttf");
		final EditText edttext=(EditText)findViewById(R.id.edtText);
		edttext.setTypeface(face);
		final EditText edttitle=(EditText)findViewById(R.id.edttitlenotif);
		edttitle.setTypeface(face);
		Button btnsendnotif=(Button)findViewById(R.id.btnsendnotif);
		btnsendnotif.setTypeface(face);
		btnsendnotif.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				title =edttitle.getText().toString();
				text=edttext.getText().toString();
				MethodHelper.CallWebhelper(context).SendRequest("SENDNOTIFICATION", title + "|" + text, null, true, new RunnableOnCompletion() {

                    @Override
                    public void run(String[] arResponse) {
                        ((Activity) context).runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                Toast.makeText(context, "اطلاعیه  ثبت شد ", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
			}
		});
		
		
		
		
		
	}
}
