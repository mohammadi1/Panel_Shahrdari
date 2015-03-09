package com.ipa.Adapter;

import android.app.Activity;
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
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.ValueKeeper;
import com.ipa.Tools.WebServiceHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class Ad_NewJobDeplicate extends BaseAdapter {
	private TextView txtnamemahal2,txttozih2,txtphon2,txtaddress2,txtmodir2;
	private ImageView imgjob1,imgjob2,imgjob3;
	WebServiceHelper helper;
	Activity act;
	String[] Ar;
	String chek,ID,phon;
	ArrayList<String> imagesaddress;
	
	public Ad_NewJobDeplicate(Activity act, String[] ar) {
		super();
		this.act = act;
		Ar = ar;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Ar.length;
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
		helper=new WebServiceHelper(act);
		LayoutInflater inflater=act.getLayoutInflater();
		View v=inflater.inflate(R.layout.ad_new_job_deplicate, null);
		TextView txtnamemahal=(TextView)v.findViewById(R.id.txtnamemahal);
		TextView txtmodir=(TextView)v.findViewById(R.id.txtmodir);
		TextView txtphonnum=(TextView)v.findViewById(R.id.txtphonnum);
		TextView txttozih=(TextView)v.findViewById(R.id.txttozihat);
		TextView txtaddress=(TextView)v.findViewById(R.id.txtaddress);
		TextView txtpoint1=(TextView)v.findViewById(R.id.txtpoint1);
		TextView txtpoint2=(TextView)v.findViewById(R.id.txtpoint2);
		TextView txtpoin3=(TextView)v.findViewById(R.id.txtpoin3);
		TextView txtpoint4=(TextView)v.findViewById(R.id.txtpoint4);
		TextView txtpoint6=(TextView)v.findViewById(R.id.txtpoint6);
		imagesaddress=new ArrayList<String>();
		
		txtnamemahal2=(TextView)v.findViewById(R.id.txtnamemahal2);
		txtphon2=(TextView)v.findViewById(R.id.txtphonnum2);
		txtaddress2=(TextView)v.findViewById(R.id.txtaddress2);
		txttozih2=(TextView)v.findViewById(R.id.txttozihat2);
		txtmodir2=(TextView)v.findViewById(R.id.txtmodir2);
		
		
		imgjob1=(ImageView)v.findViewById(R.id.imgjob1);
		imgjob2=(ImageView)v.findViewById(R.id.imgjob2);
		imgjob3=(ImageView)v.findViewById(R.id.imgjob3);
	
		Button btntaedjob=(Button)v.findViewById(R.id.btntaedjob);
		Button btndeletjob=(Button)v.findViewById(R.id.btndeletjob);
		String[] sp= MethodHelper.Split(Ar[pos], ValueKeeper.Sp2);
		 ID=sp[0];
		txtnamemahal2.setText(sp[2]);
		txtmodir2.setText(sp[3]);
		txtaddress2.setText(sp[4]);
		phon=sp[5];
		txtphon2.setText(phon);
		txttozih2.setText(sp[6]);
		String[] imgsplit=MethodHelper.Split(sp[9], ValueKeeper.Sp3);
		for (int i = 0; i < imgsplit.length; i++) {
			
			String uri="http://"+imgsplit[i].substring(18,33)+imgsplit[i].substring(43);
			imagesaddress.add(uri);
		}
		
		ImageLoader imageloader=ImageLoader.getInstance();
		DisplayImageOptions options=new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.ic_imgsend)
		.showImageOnFail(R.drawable.img_background).cacheInMemory(true)
		.cacheOnDisc(true).build();
		imageloader.displayImage(imagesaddress.get(0), imgjob1, options);
		imageloader.displayImage(imagesaddress.get(1), imgjob2, options);
		imageloader.displayImage(imagesaddress.get(2), imgjob3, options);

	
		btntaedjob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				chek="1";
				String[] B=MethodHelper.Split(Ar[pos], ValueKeeper.Sp2);
				String P=B[5];
				helper.SendRequest("VALIDJOB", P+"|"+chek, null, true, new RunnableOnCompletion() {
					
					@Override
					public void run(String[] arResponse) {
						String[] Ar=new String[arResponse.length-1];
						System.arraycopy(arResponse, 1, Ar, 0,arResponse.length-1);
						String[] sp=MethodHelper.Split(Ar[0], ValueKeeper.Sp2);
						if (Ar[0].equals("ok")) {
							act.runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(act, "تایید شد", Toast.LENGTH_SHORT).show();
									
								}
							});
						}
						
					}
				});
				
			}
		});
		
		btndeletjob.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				chek="0";
				helper.SendRequest("VALIDJOB", phon+"|"+chek, null, true, new RunnableOnCompletion() {
					
					@Override
					public void run(String[] arResponse) {
						String[] Ar=new String[arResponse.length-1];
						System.arraycopy(arResponse, 1, Ar, 0,arResponse.length-1);
						String[] sp=MethodHelper.Split(Ar[0], ValueKeeper.Sp2);
						if (Ar[0].equals("ok")) {
							act.runOnUiThread(new Runnable() {
								
								@Override
								public void run() {
									Toast.makeText(act, "حذف شد", Toast.LENGTH_SHORT).show();
									
								}
							});
						}
					}
				});
				
			}
		});
		
		return v;
	}

	

	

}
