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
import com.ipa.Tools.Cls_LoginModel;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.WebServiceHelper;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

public class Ad_LoginManage extends BaseAdapter {
	Activity act;
	ArrayList<Cls_LoginModel> listlogin = new ArrayList<Cls_LoginModel>();
	WebServiceHelper whelper;
	String[] ar;
	String an;
	Button btndelet1;
	LayoutInflater inflater;

	public Ad_LoginManage(Activity act, ArrayList<Cls_LoginModel> Listlogin) {
		super();
		this.act = act;
		this.listlogin = Listlogin;
		inflater = act.getLayoutInflater();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listlogin.size();
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
	public View getView(final int pos, View convertView, ViewGroup arg2) {
		// ViewHolder holder;
		// if (convertView == null) {
		// convertView = inflater.inflate(R.layout.adapter_loginmanag, null);
		// holder = new ViewHolder();
		// holder.taeduser = (TextView)
		// convertView.findViewById(R.id.textView1);
		// holder.lblList = (TextView)
		// convertView.findViewById(R.id.txtusername);
		// holder.lblList.setText(listlogin.get(pos).username);
		// holder.imgList = (ImageView) convertView.findViewById(R.id.imguser);
		// holder.btnOk = (Button) convertView.findViewById(R.id.btntaed1);
		// holder.btnCancel = (Button) convertView.findViewById(R.id.btndelet1);

		// if (listlogin.get(pos).valid.equals("True")) {
		//
		// holder.taeduser.setText("این کاربر قبلا حذف شده است");
		// } else {
		// holder.taeduser.setText("این کاربر قبلا تایید شده است");
		// }
		// else{
		// holder.taeduser.setText("وضعیت این کاربر هنوز مشخص نشده است");
		// }
		// convertView.setTag(holder);
		// } else {
		// holder = (ViewHolder) convertView.getTag();
		// }
		LayoutInflater inflater = act.getLayoutInflater();
		View v = inflater.inflate(R.layout.ad_loginmanag, null);
		TextView txtusername = (TextView) v.findViewById(R.id.txtusername);
		Button btntaed1 = (Button) v.findViewById(R.id.btntaed1);
		btndelet1 = (Button) v.findViewById(R.id.btndelet1);
		ImageView imguser = (ImageView) v.findViewById(R.id.imguser);
		txtusername.setText(listlogin.get(pos).username);

		String imagePath = "";
		imagePath += "http://" + listlogin.get(pos).imagpath.substring(18, 33) + listlogin.get(pos).imagpath.substring(43);

		ImageLoader imageLoader = ImageLoader.getInstance();
		DisplayImageOptions options = new DisplayImageOptions.Builder()
		.showImageForEmptyUri(R.drawable.camera1)
		.showImageOnFail(R.drawable.animated_loading_blue)
		.cacheInMemory(true)
				.cacheOnDisc(true).build();
		imageLoader.displayImage(imagePath, imguser, options);
		whelper = new WebServiceHelper(act);
		btntaed1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				an = "1";
				int id = listlogin.get(pos).id;
				whelper.SendRequest("VALIDATIONREGISTER", id + "|" + an, null, true, new RunnableOnCompletion() {

					@Override
					public void run(String[] arResponse) {
						act.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(act, "کاربر تایید شد", Toast.LENGTH_SHORT).show();
							}
						});
						
					}
				});
			}
		});

		btndelet1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				an = "0";
				int id = listlogin.get(pos).id;
				whelper.SendRequest("VALIDATIONREGISTER", id + "|" + an, null, true, new RunnableOnCompletion() {

					@Override
					public void run(String[] arResponse) {
						// TODO Auto-generated method stub
						act.runOnUiThread(new Runnable() {

							@Override
							public void run() {
								// TODO Auto-generated method stub
								Toast.makeText(act, "کاربر حذف شد", Toast.LENGTH_SHORT).show();
							}
						});

					}
				});

			}
		});

		return v;
	}

	// class ViewHolder {
	// ImageView imgList;
	// TextView lblList;
	// TextView taeduser;
	// Button btnOk, btnCancel;
	// }

}
