//  ******* Documented in 1393/12/16 *******
//  in activity baraye pasokh shahrdar be soalat ersali ast *
//  -------Tozih:---------------------------
//  dar ebtedaye activity id marbot be inke soalate shahrdar nemayesh dade shavad ya shora
//  az SharedPreferencess gerefteh mishavad


package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.SharedPrefrencesHelper;

public class Act_answer extends Activity {
	Context context;
	String answer,question,idquestion;
	public static int id=1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context=this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_answer);
		idquestion= SharedPrefrencesHelper.getStringPref(context, "RegisterID");
		TextView txtanswer=(TextView)findViewById(R.id.txtanswer);
        final EditText edtanswer=(EditText)findViewById(R.id.edtanswer);
        Button btnanswer=(Button)findViewById(R.id.btnanswer);

		txtanswer.setTypeface(MethodHelper.getTypeFace(context));
		edtanswer.setTypeface(MethodHelper.getTypeFace(context));
		btnanswer.setTypeface(MethodHelper.getTypeFace(context));
		Bundle bundle=getIntent().getExtras();
		if (bundle!=null){
			 question=bundle.getString("ques");
		}
				btnanswer.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						answer=edtanswer.getText().toString();
						MethodHelper.CallWebhelper(context).SendRequest("REGISTERQUESTIONS", question+"|"+answer+"|"+idquestion, null, true, new RunnableOnCompletion() {
							
							@Override
							public void run(String[] arResponse) {
								((Activity)context).runOnUiThread(new Runnable() {
									
									@Override
									public void run() {
                                        MethodHelper.ShowToast(context,R.string.welcom);
										((Activity)context).finish();
									}
								});
							}
						});
					}
				});
	}

}

