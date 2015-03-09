
//  ******* Documented in 1393/12/16 *******
//  in avalin Activity ast ke namayesh dadeh mishvad *
//  -------Tozih:---------------------------
// dar in Act barresi mishavad ke aya login anjam shode ast?

package com.ipa.Panel_Shahrdari;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.SharedPrefrencesHelper;
import com.ipa.Tools.ValueKeeper;

public class Act_Splash extends ActionBarActivity {
	Context context;
	RelativeLayout rel;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        context = this;
      setContentView(R.layout.act_splash);
        MethodHelper.ShowActionBar(context,this,R.string.str_panel1);
		rel=(RelativeLayout)findViewById(R.id.rel);
		final ImageView img=(ImageView)findViewById(R.id.imageView1);
		Runnable run = new Runnable() {

			@Override
			public void run() {
					img.setVisibility(View.GONE);
					
				String User = SharedPrefrencesHelper.getStringPref(context, "UserName", "");
				if (User.equals("")) {
					startActivity(new Intent(context, Act_Login.class));
					finish();
				} else {
					ValueKeeper.UserName = User;
					ValueKeeper.Password = SharedPrefrencesHelper.getStringPref(context, "Password");
					startActivity( new Intent(context, Act_Home.class));
					finish();
				}
			}
		};
		Handler handler = new Handler();
		handler.postDelayed(run, 2000);
	}
}
