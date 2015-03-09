package com.ipa.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ipa.Panel_Shahrdari.Act_CompetitionImages;
import com.ipa.Panel_Shahrdari.Act_Gallery;
import com.ipa.Panel_Shahrdari.Act_ImagesReceived;
import com.ipa.Panel_Shahrdari.Act_LoginManage;
import com.ipa.Panel_Shahrdari.Act_NewJob;
import com.ipa.Panel_Shahrdari.Act_NewsShahrdary;
import com.ipa.Panel_Shahrdari.Act_Newspaper;
import com.ipa.Panel_Shahrdari.Act_Notification;
import com.ipa.Panel_Shahrdari.Act_Shahrdar;
import com.ipa.Panel_Shahrdari.Act_Shora;
import com.ipa.Panel_Shahrdari.Act_comment;
import com.ipa.Panel_Shahrdari.Act_incantation;
import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.Cls_HomeItems;

import java.util.ArrayList;

public class Ad_Home extends BaseAdapter {
	Activity act;
	ArrayList<Cls_HomeItems> lstpanelitem ;

	public Ad_Home(Activity act, ArrayList<Cls_HomeItems> lstpanelitem) {
		super();
		this.act = act;
		this.lstpanelitem = lstpanelitem;
	}

	@Override
	public int getCount() {
		return lstpanelitem.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(final int arg0, View arg1, ViewGroup arg2) {
	LayoutInflater inflater=act.getLayoutInflater();
	final View v=inflater.inflate(R.layout.ad_home, null);
	ImageView imgitempanel=(ImageView)v.findViewById(R.id.imgitempanel);
	TextView txtitempanel=(TextView)v.findViewById(R.id.txtitempanel);
	Typeface face=Typeface.createFromAsset(act.getAssets(), "BYekan.ttf");
	txtitempanel.setText(lstpanelitem.get(arg0).text);
	txtitempanel.setTypeface(face);
	imgitempanel.setImageResource(lstpanelitem.get(arg0).image);
	v.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View pos) {
				switch (arg0) {
					case 0 :
						Intent shahrdar=new Intent(act,Act_Shahrdar.class);
						act.startActivity(shahrdar);
						break;
					case 1 :
						Intent shora=new Intent(act,Act_Shora.class);
						act.startActivity(shora);
						break;
					case 2 :
						Intent newsp=new Intent(act,Act_Newspaper.class);
						act.startActivity(newsp);						break;

					case 3 :
						Intent images=new Intent(act,Act_ImagesReceived.class);
						act.startActivity(images);
						break;
					case 4:
						Intent competition=new Intent(act,Act_CompetitionImages.class);
						act.startActivity(competition);
						break;
					case 5:
						Intent login=new Intent(act,Act_LoginManage.class);
						act.startActivity(login);
						break;
					case 6:
						Intent galery=new Intent(act,Act_Gallery.class);
						act.startActivity(galery);
						break;
					case 7 :
						Intent newsshahrdary=new Intent(act,Act_NewsShahrdary.class);
						act.startActivity(newsshahrdary);
						break;
					case 8:
						Intent notification=new Intent(act,Act_Notification.class);
						act.startActivity(notification);
						break;
					case 9 :
						Intent incantation=new Intent(act,Act_incantation.class);
						act.startActivity(incantation);
						break;
					case 10 :
						Intent comment=new Intent(act,Act_comment.class);
						act.startActivity(comment);
						break;
					case 11:
						Intent job=new Intent(act, Act_NewJob.class);
						act.startActivity(job);
						break;
			}
		}
	});
		return v;
	}
}
