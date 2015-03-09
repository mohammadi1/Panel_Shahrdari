package com.ipa.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.ipa.Panel_Shahrdari.R;
import com.ipa.Tools.Cls_DetailImage;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.WebServiceHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
public class Ad_Gallery extends BaseAdapter {
	Activity act;
	String[] img;
	String imagepath = "";
	WebServiceHelper helper;
	String id;
	String an;
	String im;
	ArrayList<Cls_DetailImage> listimg;

	public Ad_Gallery(Activity act, ArrayList<Cls_DetailImage> listimg) {
		super();
		this.act = act;
		this.listimg = listimg;
	}

	public Ad_Gallery(Activity act, String[] img) {
		super();
		this.act = act;
		this.img = img;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listimg.size();
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
		LayoutInflater inflater=act.getLayoutInflater();
		View v=inflater.inflate(R.layout.ad_galery, null);
		ImageView imggalery=(ImageView)v.findViewById(R.id.imggalery);
		Button btntaed=(Button)v.findViewById(R.id.btnTAED);
		Button btndelet=(Button)v.findViewById(R.id.btnDELET);
		helper=new WebServiceHelper(act);
		
//		String[] sp=ValueKeeper.Split(img[pos], ValueKeeper.Sp2);
//		String imagepath = "";
//		imagepath = "http://"+sp[4].substring(18,33)+sp[4].substring(43);
//		 id=sp[0];
//		 im=imagepath;
	
		imagepath=listimg.get(pos).imgpach.substring(18,33)+listimg.get(pos).imgpach.substring(43);
		ImageLoader imageLoader=ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.ic_launcher)
		.showImageOnFail(R.drawable.key)
		.cacheInMemory(true)
		.cacheOnDisc(true)
		.build();
		imageLoader.displayImage(imagepath, imggalery, options);
		btntaed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				an="1";
				id=listimg.get(pos).id;
				im=listimg.get(pos).imgpach;
				helper.SendRequest("VALIDATIONIMAGE", id+"|"+an+"|"+im, null, true, new RunnableOnCompletion() {
					
					@Override
					public void run(String[] arResponse) {
						act.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
							Toast.makeText(act, "این تصویر تایید شد", Toast.LENGTH_SHORT).show();
							}
						});
						
					}
				});
				
			}
		});
		
		btndelet.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				an="0";
				id=listimg.get(pos).id;
				im=listimg.get(pos).imgpach;
				helper.SendRequest("VALIDATIONIMAGE",  id+"|"+an+"|"+im, null, true, new RunnableOnCompletion() {
					
					@Override
					public void run(String[] arResponse) {
						act.runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(act, "این تصویر حذف شد", Toast.LENGTH_SHORT).show();
								
							}
						});
						
					}
				});
				
				
			}
		});
		
		
		
		return v;
	}

}
