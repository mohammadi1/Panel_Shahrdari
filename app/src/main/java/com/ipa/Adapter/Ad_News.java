package com.ipa.Adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.Cls_NewspaperModel;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.WebServiceHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class Ad_News extends BaseAdapter {
	Activity act;
	ArrayList<Cls_NewspaperModel> listnews;
	String imagePath = "http://";
	String an;
	WebServiceHelper whelper;

	public Ad_News(Activity act, ArrayList<Cls_NewspaperModel> listnews) {
		super();
		this.act = act;
		this.listnews = listnews;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listnews.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(final int pos, View arg1, ViewGroup arg2) {
		LayoutInflater inflater = act.getLayoutInflater();
		View v = inflater.inflate(R.layout.ad_newspaper, null);
		Typeface face=Typeface.createFromAsset(act.getAssets(), "BYekan.ttf");
		TextView txtnameuser = (TextView) v.findViewById(R.id.txtnameuser);
		txtnameuser.setTypeface(face);
		TextView txtnews = (TextView) v.findViewById(R.id.txtnews);
		txtnews.setTypeface(face);
		ImageView imgnews = (ImageView) v.findViewById(R.id.imgnews);
		Button btndelet = (Button) v.findViewById(R.id.btndelet);
		btndelet.setTypeface(face);
		Button btntaed = (Button) v.findViewById(R.id.btntaed);
		btntaed.setTypeface(face);
		txtnameuser.setText(listnews.get(pos).userName);
		txtnews.setText(listnews.get(pos).text);

		imagePath += listnews.get(pos).imagepath.substring(18, 33) + listnews.get(pos).imagepath.substring(43);

		ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.drawable.camera1).showImageOnFail(R.drawable.animated_loading_blue).cacheInMemory(true)
				.cacheOnDisc(true).build();
		imageLoader.displayImage("http://hadiservices.ir/baghbadoran/Images/UsersPic/23.jpg", imgnews, options);
		whelper = new WebServiceHelper(act);
		btndelet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				an = "0";
				int id = listnews.get(pos).Groupid;
				whelper.SendRequest("CONFIRMPOSTS", id + "|" + an, null, true, new RunnableOnCompletion() {

					@Override
					public void run(String[] arResponse) {
						act.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(act, "این خبر حذف شد", Toast.LENGTH_SHORT).show();
							}
						});
					}

				});
			}
		});
		btntaed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				an = "1";
				int id = listnews.get(pos).newsID;
				whelper.SendRequest("CONFIRMPOSTS", id + "|" + an, null, true, new RunnableOnCompletion() {

					@Override
					public void run(String[] arResponse) {
						act.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(act, "این خبر تایید شد", Toast.LENGTH_SHORT).show();
							}
						});
					}

				});
			}
		});

		return v;
	}
}
