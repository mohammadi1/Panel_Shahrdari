
//  ******* Documented in 1393/12/16 *******
//  in activity baraye safheye asli barname ast *
//  -------Tozih:---------------------------
//   shamel item haye mokhtalefi ast in item ha be adapter dade mishavad

package com.ipa.Panel_Shahrdari;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;

import com.ipa.Adapter.Ad_Home;
import com.ipa.Tools.Cls_HomeItems;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.PersianReshape;

import java.util.ArrayList;


public class Act_Home extends ActionBarActivity {
	Context context;
	ArrayList<Cls_HomeItems> lstpanelitem = new ArrayList<Cls_HomeItems>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context = this;
        super.onCreate(savedInstanceState);
		setContentView(R.layout.act_home);
		MethodHelper.ShowActionBar(context,this,R.string.str_panel1);
		GridView grdvpanel = (GridView) findViewById(R.id.grdvpanel);
		ImageView img_logo=(ImageView)findViewById(R.id.img_logo);
		EditText txt_line=(EditText)findViewById(R.id.txt_line);

//				NotificationCompat.Builder mBuilder =
//			            new NotificationCompat.Builder(this)
//			            .setSmallIcon(R.drawable.ic_notification)
//			            .setContentTitle("سلام")
//			            .setContentText("از انتخاب شما متشکریم");
//				NotificationManager notificationManager = (NotificationManager)
//		                getSystemService(Context.NOTIFICATION_SERVICE);
//						notificationManager.notify(5565, mBuilder.build());

		for (int i = 1; i <= 12; i++) {
			Cls_HomeItems item = new Cls_HomeItems();

			switch (i) {
				case 1 :
					item.text = (PersianReshape.reshape(context, R.string.str_shahrdar));
					item.image = (R.drawable.ic_shahrdar);
					break;

				case 2 :
					item.text = (PersianReshape.reshape(context, R.string.str_shora));
					item.image = (R.drawable.ic_shora);
					break;

				case 3 :
					item.text = (PersianReshape.reshape(context, R.string.str_newspapre));
					item.image = (R.drawable.ic_newspaper);
					break;

				case 4 :
					item.text = (PersianReshape.reshape(context, R.string.str_regards_images));
					item.image = (R.drawable.ic_camera);
					break;

				case 5 :
					item.text = (PersianReshape.reshape(context, R.string.str_regards_competition));
					item.image = (R.drawable.ic_cup);
					break;

				case 6 :
					item.text = (PersianReshape.reshape(context, R.string.str_login));
					item.image = (R.drawable.ic_logn);
					break;

				case 7 :
					item.text = (PersianReshape.reshape(context, R.string.str_galery));
					item.image = (R.drawable.ic_gallery);
					break;

				case 8 :
					item.text = (PersianReshape.reshape(context, R.string.str_news_shahrdary));
					item.image = (R.drawable.ic_akhbarshahrdari);
					break;

				case 9 :
					item.text = (PersianReshape.reshape(context, R.string.str_notification));
					item.image = (R.drawable.ic_notifi);
					break;

				case 10 :
					item.text = (PersianReshape.reshape(context, R.string.str_incantation));
					item.image = (R.drawable.ic_tablighat);
					break;

				case 11 :
					item.text = (PersianReshape.reshape(context, R.string.str_comment));
					item.image = (R.drawable.ic_comment);
					break;
				case 12:
					item.text=(PersianReshape.reshape(context, R.string.str_new_job));
					item.image=(R.drawable.ic_taaed);
					break;
			}
			lstpanelitem.add(item);

		}
		Ad_Home ad = new Ad_Home(this, lstpanelitem);
		grdvpanel.setAdapter(ad);

	}
}
