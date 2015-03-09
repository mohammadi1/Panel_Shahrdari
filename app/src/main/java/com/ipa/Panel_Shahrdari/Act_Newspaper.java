
//  ******* Documented in 1393/12/16 *******
//  in activity baraye namayeshe rozname ast*
//  -------Tozih:---------------------------
//   az class Cls_NewspaperModel baraye item haye roozname estefade shode ast

package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_News;
import com.ipa.Tools.Cls_NewspaperModel;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.ValueKeeper;

import java.util.ArrayList;

public class Act_Newspaper extends ActionBarActivity {
	Context context;
	ArrayList<Cls_NewspaperModel> listnews=new ArrayList<Cls_NewspaperModel>();
	Cls_NewspaperModel model;
@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
	context = this;
	setContentView(R.layout.act_newspaper);
    MethodHelper.ShowActionBar(context,this,R.string.str_newspapre);
	final ListView lstvnews = (ListView) findViewById(R.id.lstvnews);
	MethodHelper.CallWebhelper(context).SendRequest("GETNEWPOST", null, null, true, new RunnableOnCompletion() {
		
		@Override
		public void run(String[] arResponse) {
			String[] news=new String[arResponse.length-1];
			System.arraycopy(arResponse, 1, news, 0, arResponse.length-1);
			for (int i = 0; i < news.length; i++) 
			{			
				String[] sp = MethodHelper.Split(news[i], ValueKeeper.Sp2);
				int userID = Integer.parseInt(sp[2]);
				String userName = sp[3];
				String txtnews = sp[4];
				int newsID = Integer.parseInt(sp[0]);
				int groupID = Integer.parseInt(sp[1]);
                int subGroupID = Integer.parseInt(sp[7]);
				String imageAdd = sp[5];
				model = new Cls_NewspaperModel(newsID,userID, userName, txtnews, groupID, subGroupID, imageAdd);
				listnews.add(model);			
			}
			((Activity)context).runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					lstvnews.setAdapter(new Ad_News((Activity)context, listnews));
				}
			});
		}
	});

}
}
