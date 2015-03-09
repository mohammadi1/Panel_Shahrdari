

//  ******* Documented in 1393/12/16 *******
//  in activity baraye garar dadane akhbar shahrdari ast *
//  -------Methods:-------------------------
// getPath(Uri,Activity)

package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ipa.Adapter.Ad_SpinnerNews;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.SharedPrefrencesHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Act_NewsShahrdary extends ActionBarActivity {
	Context context;
	String title,time ,date,text;
	private static final int REQUEST_CAMERA = 1;
	private static final int SELECT_FILE = 2;
	Bitmap bm;
	ImageView imgnews;
	int groupID;
	String[] group;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		context=this;
		setContentView(R.layout.act_news_shahrdary);
    	MethodHelper.ShowActionBar(context,this,R.string.str_news_shahrdary);
		TextView txttitle=(TextView)findViewById(R.id.txttitle);
		TextView txtnews=(TextView)findViewById(R.id.txtnews);
		TextView txtdate=(TextView)findViewById(R.id.txtdate);
		TextView txttime=(TextView)findViewById(R.id.txttime);
		TextView txtgroup=(TextView)findViewById(R.id.txtgroup);
		final EditText edttitle=(EditText)findViewById(R.id.edttitle);
		final EditText edttext=(EditText)findViewById(R.id.edttext);
		final EditText edtdate=(EditText)findViewById(R.id.edtdate);
		final EditText edttime=(EditText)findViewById(R.id.edttime);
		Spinner sp=(Spinner)findViewById(R.id.spngroup);
		Button btntaed=(Button)findViewById(R.id.btntaednews);
		btntaed.setTypeface(MethodHelper.getTypeFace(context));
		Button btnback=(Button)findViewById(R.id.btnback);

		btnback.setTypeface(MethodHelper.getTypeFace(context));
		imgnews=(ImageView)findViewById(R.id.imgnews);
		group=new String[]{"اجتماعی","فرهنگی","عمرانی"};
		Ad_SpinnerNews ad=new Ad_SpinnerNews((Activity)context, group);
		sp.setAdapter(ad);
		SharedPrefrencesHelper.setIntPref(context, "bb", 10);
		
		imgnews.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				final CharSequence[] items = { "Take Photo", "Choose from Library",
				"Cancel" };

				AlertDialog.Builder builder = new AlertDialog.Builder(Act_NewsShahrdary.this);
				builder.setTitle("Add Photo!");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int item) {
						if (items[item].equals("Take Photo")) {
							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							File f = new File(Environment
								.getExternalStorageDirectory(),"temp.jpg");
							intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
							startActivityForResult(intent, REQUEST_CAMERA);
						} else if (items[item].equals("Choose from Library")) {
							Intent intent = new Intent(
									Intent.ACTION_PICK,
									MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							intent.setType("image/*");
							startActivityForResult(
									Intent.createChooser(intent, "Select File"),
									SELECT_FILE);
						} else if (items[item].equals("Cancel")) {
							dialog.dismiss();
						}
			}
		});
		builder.show();
			}
			});
		
		sp.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
				groupID = position +1;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
		
		btntaed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			title=edttitle.getText().toString();
			text=edttext.getText().toString();
			date=edtdate.getText().toString();
			time=edttime.getText().toString();
			edttext.setText("");
			edttitle.setText("");
			edtdate.setText("");
			edttime.setText("");
		
			
			byte[] imgByByte = null;
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			if(bm != null)
			{
				bm.compress(Bitmap.CompressFormat.JPEG, 100, output);
				imgByByte = output.toByteArray();
				
				MethodHelper.CallWebhelper(context).SendRequest("SENDCITYNEWS", title+"|"+text+"|"+date+"|"+time+"|"+groupID, imgByByte, true, new RunnableOnCompletion() {
					
					@Override
					public void run(String[] arResponse) {
						((Activity)context).runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(context, "ثبت خبر با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
							}
						});
						
					}
				});
					
			}
			else
			{
                MethodHelper.CallWebhelper(context).SendRequest("SENDCITYNEWS", title+"|"+text+"|"+date+"|"+time+"|"+groupID, null, true, new RunnableOnCompletion() {
					
					@Override
					public void run(String[] arResponse) {
						((Activity)context).runOnUiThread(new Runnable() {
							
							@Override
							public void run() {
								Toast.makeText(context, "ثبت خبر با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
							}
						});
						
					}
				});
			}
			
			}
		});
		btnback.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			startActivity(new Intent(context,Act_Home.class));
				
			}
		});
	

	}
/*
 pas az gereftane ax ya entekhabe an az gallery ,
 ax tavasote OnActivityResult be Activity asli bargardandeh mishavad
 va sepas agar ba dorbin gerefte shode bashad dar file zakhire mishavad
 va agar az gallery entekhab shavad tavasote class getpatch() adress uri an ersal mishavad
 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
 
                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);
 
                    // bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
                    imgnews.setImageBitmap(bm);
 
                    String path = Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");
                    try {
                        fOut = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
                String tempPath = getPath(selectedImageUri, Act_NewsShahrdary.this);
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                imgnews.setImageBitmap(bm);
            }
        }
    }
	/*
	in calass baraye tebdil kardane Adress uri be string
	estefade mishavad
	parameter: URI,Activity
	 */
	public String getPath(Uri uri, Activity activity) {
		String[] projection = { MediaColumns.DATA };
		Cursor cursor = activity
				.managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}
