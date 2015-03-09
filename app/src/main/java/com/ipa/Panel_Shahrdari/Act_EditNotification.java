//  ******* Documented in 1393/12/16 *******
//  in activity baraye edit kardan notification he mibashad*

package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.PersianReshape;
import com.ipa.Tools.RunnableOnCompletion;
public class Act_EditNotification extends Activity{
	Context context;
	String txt_notification,txt_title,title,text,id;
		@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_edit_notification);

		final EditText txt_title_noti=(EditText)findViewById(R.id.edtTITLENOTIF);
		final EditText txt_noti=(EditText)findViewById(R.id.edtTEXTNOTIF);
		Button btnsendnotif=(Button)findViewById(R.id.btnsendnotif);
		btnsendnotif.setText(PersianReshape.reshape(context, R.string.str_send));
		btnsendnotif.setTypeface(MethodHelper.getTypeFace(context));
		Bundle bundle=getIntent().getExtras();
		if (bundle!=null) {
			title=bundle.getString("title");
			text=bundle.getString("notifi");
			id=bundle.getString("id");
		}
            txt_title_noti.setText(title);
            txt_noti.setText(text);

		    btnsendnotif.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
		    txt_title=txt_title_noti.getText().toString();
		    txt_notification=txt_noti.getText().toString();
			MethodHelper.CallWebhelper(context).SendRequest("EDITNOTIFICATION", id + "|" + txt_title + "|" + txt_notification, null, false, new RunnableOnCompletion() {
                @Override
                public void run(String[] arResponse) {

                }
            });
				
			}
		});
		
		
	}

}
