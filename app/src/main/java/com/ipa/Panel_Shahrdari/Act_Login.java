
//  ******* Documented in 1393/12/16 *******
//  in activity baraye login be barname ast *
//  -------Tozih:---------------------------
//  baad az login , user va pass dar ValuKeeper zakhire mishavad

package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipa.Tools.SharedPrefrencesHelper;
import com.ipa.Tools.ValueKeeper;

public class Act_Login extends Activity {
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context=this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	setContentView(R.layout.act_login);

	final EditText edtpassword=(EditText)findViewById(R.id.edtpassword);
	final EditText edtusername=(EditText)findViewById(R.id.edtusername);
	TextView txtline=(TextView)findViewById(R.id.txtline);
	TextView txtline2=(TextView)findViewById(R.id.txtline2);
	ImageView imgpassword=(ImageView)findViewById(R.id.imgpassword);
	ImageView imgusername=(ImageView)findViewById(R.id.imgusername);
	Button btnwelcom=(Button)findViewById(R.id.btnwelcom);
	
	btnwelcom.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
		String username=edtusername.getText().toString();
		String pass=edtpassword.getText().toString();
		SharedPrefrencesHelper.setStringPref(context, "UserName", username);
		SharedPrefrencesHelper.setStringPref(context, "Password", pass);
		ValueKeeper.UserName=username;
		ValueKeeper.Password=pass;
		Intent intent1=new Intent(context, Act_Home.class);
		startActivity(intent1);
		finish();	
		}
	});
	}

}
