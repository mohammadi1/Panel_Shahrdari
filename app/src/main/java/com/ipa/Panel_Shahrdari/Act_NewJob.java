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
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.ipa.Adapter.Ad_NewJob;
import com.ipa.Tools.DataBaseHelper;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.PersianReshape;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.Search_Jobs;
import com.ipa.Tools.ValueKeeper;
import com.ipa.Tools.WebServiceHelper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

public class Act_NewJob extends ActionBarActivity{
	private Activity act;
	private Context context;
	GridView gridv;
	Bitmap bm;
	private static final int REQUEST_CAMERA = 1;
	private static final int SELECT_FILE = 2;
	String name, address, phon, modir, tozih;
	private TextView txtnamemahal, txtaddress, txtphon1, txtmodir, txttozihat, txtgroup, txtsubgroup;
	private EditText edtnamemahal, edtaddress, edtphon1, edtmodir, edttozihat;
	private Spinner spngroup, spnsubgroup;
	int label1[] = {R.string.image_category_item0, R.string.job_item1, R.string.job_item2};
	int imagelabel[] = {R.drawable.ic_shahrdariunits, R.drawable.ic_services, R.drawable.ic_sale};
	WebServiceHelper helper;
	ArrayList<Search_Jobs> jobList=new ArrayList<Search_Jobs>();
	
	SpinnerAdapter adapter;
	Cursor select;
	String label, path, jobID;
	String jobLabels[];
	String arrImages[];
	String jobLabel;
	long x;
	int idsubgrup = 22;
	private String IDSECOUND, IDSECOUND2;
	Boolean flag = false;
	int i = 0;
	int jobImage;
	Button btnadd;
	ArrayList<Bitmap> listimages = new ArrayList<Bitmap>();

	Search_Jobs searchJobs;
	int arrImagesForosh[] = {R.drawable.ic_hyper_market, R.drawable.ic_mobleman, R.drawable.ic_lavazem, R.drawable.ic_daro, R.drawable.ic_talar, R.drawable.ic_resturan, R.drawable.ic_food,
			R.drawable.ic_map_chicken, R.drawable.ic_map_phone, R.drawable.ic_map_bread, R.drawable.ic_map_man_shoe, R.drawable.ic_map_jewlery, R.drawable.ic_map_botic, R.drawable.ic_food,
			R.drawable.ic_map_phone, R.drawable.ic_map_bookstore, R.drawable.i_map_tire, R.drawable.ic_map_bag, R.drawable.ic_other};

	int arrImagesKhadamati[] = {R.drawable.ic_khadamat_khodro, R.drawable.ic_maskan, R.drawable.ic_khadamat_amozeshi, R.drawable.ic_map_barber, R.drawable.ic_black_camera, R.drawable.ic_map_pc,
			R.drawable.ic_other};

	int arrImage[] = {R.drawable.ic_khadamat_khodro, R.drawable.ic_hyper_market, R.drawable.ic_mobleman, R.drawable.ic_lavazem, R.drawable.ic_daro, R.drawable.ic_talar, R.drawable.ic_resturan,
			R.drawable.ic_food, R.drawable.ic_maskan, R.drawable.ic_khadamat_amozeshi, R.drawable.ic_map_chicken, R.drawable.ic_map_phone, R.drawable.ic_map_barber, R.drawable.ic_map_bread,
			R.drawable.ic_map_man_shoe, R.drawable.ic_black_camera, R.drawable.ic_map_jewlery, R.drawable.ic_map_botic, R.drawable.ic_food, R.drawable.ic_map_phone, R.drawable.ic_map_bookstore,
			R.drawable.ic_map_pc, R.drawable.i_map_tire, R.drawable.ic_map_bag};
	private DataBaseHelper db = new DataBaseHelper(ValueKeeper.DataBasePath, ValueKeeper.DataBaseName);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		act = this;
		context = this;
        helper = new WebServiceHelper(act);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_job_activity);
		txtnamemahal = (TextView) findViewById(R.id.txtnamemahal);
		txtnamemahal.setText(PersianReshape.reshape(getString(R.string.sub_jobs_name)));
		txtnamemahal.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		txtaddress = (TextView) findViewById(R.id.txtaddress);
		txtaddress.setText(PersianReshape.reshape(getString(R.string.sub_jobs_address)));
		txtaddress.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		txtphon1 = (TextView) findViewById(R.id.txtphon1);
		txtphon1.setText(PersianReshape.reshape(getString(R.string.sub_jobs_phone)));
		txtphon1.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		txtmodir = (TextView) findViewById(R.id.txtmodir);
		txtmodir.setText(PersianReshape.reshape(getString(R.string.sub_jobs_managenment)));
		txtmodir.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		txtgroup = (TextView) findViewById(R.id.txtgroup);
		txtgroup.setText(PersianReshape.reshape(getString(R.string.str_group_job)));
		txtgroup.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		txtsubgroup = (TextView) findViewById(R.id.txtsubgroup);
		txtsubgroup.setText(PersianReshape.reshape(getString(R.string.str_grup_2)));
		txtsubgroup.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		txttozihat = (TextView) findViewById(R.id.txttozihat);
		txttozihat.setText(PersianReshape.reshape(getString(R.string.sub_job_desc)));
		txttozihat.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		TextView txtpoint = (TextView) findViewById(R.id.txtpoint);

		TextView txtpoint1 = (TextView) findViewById(R.id.txtpoint1);
		TextView txtpoint2 = (TextView) findViewById(R.id.txtpoint2);
		TextView txtpoint3 = (TextView) findViewById(R.id.txtpoint3);
		TextView txtpoint4 = (TextView) findViewById(R.id.txtpoint4);
		Button btnersal = (Button) findViewById(R.id.btnersal);
		btnersal.setText(PersianReshape.reshape(getString(R.string.str_send)));
		btnersal.setTypeface(MethodHelper.getTypeFace(act, "BYekan.ttf"));

		gridv = (GridView) findViewById(R.id.gridv);

		edtnamemahal = (EditText) findViewById(R.id.edtnamemahal);
		edtaddress = (EditText) findViewById(R.id.edtaddress);
		edtphon1 = (EditText) findViewById(R.id.edtphon1);
		edtmodir = (EditText) findViewById(R.id.edtmodir);
		edttozihat = (EditText) findViewById(R.id.edttozihat);
		btnadd = (Button) findViewById(R.id.btnadd);
		btnadd.setText(PersianReshape.reshape(getString(R.string.str_addimg)));
		btnadd.setTypeface(MethodHelper.getTypeFace(context, "BYekan.ttf"));
		spngroup = (Spinner) findViewById(R.id.spngroup);
		spnsubgroup = (Spinner) findViewById(R.id.spnsubgroup);
//		adapter=new  SpinnerAdapter(act, label1, imagelabel);
		spngroup.setAdapter(adapter);

//		getSupportActionBar().setDisplayShowHomeEnabled(false);
//		getSupportActionBar().setDisplayShowTitleEnabled(false);
//		getSupportActionBar().setDisplayShowCustomEnabled(true);
//		LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//		View v = inflater.inflate(R.layout.actionbar_newspapper_items, null);
//		TextView lbl_ActionBar_NewsPapper_Items = (TextView) v.findViewById(R.id.lbl_ActionBar_NewsPapper_Items);
//		lbl_ActionBar_NewsPapper_Items.setText(PersianReshape.reshape(getString(R.string.str_sabt)));
//		lbl_ActionBar_NewsPapper_Items.setTypeface(MethodHelper.getTypeFace(act, "mjDinarTowMedium.ttf"));
//		getSupportActionBar().setCustomView(v);


//		DataBaseHelper dbh=new DataBaseHelper();
//		dbh.copyDatabaseFromAssets(context,Environment.getExternalStorageDirectory()+"/data/data/","BaghbahadoranDB");
//		spngroup.setOnItemSelectedListener(new OnItemSelectedListener() {
//
//			@Override
//			public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3) {
//
//				x = spngroup.getItemIdAtPosition(position);
////				j = (int) x;
//				switch (position) {
//					case 0 :
//						jobList.removeAll(jobList);
//						select = db.ReadfromDB("tblJobs", null);
//						if (select.getCount()==0) {
//							Toast.makeText(context, "fffff", Toast.LENGTH_LONG).show();
//						}
//						// jobLabels = new String[select.getCount()];
//						for (int i = 0; i < select.getCount(); i++) {
//							select.moveToPosition(i);
//							jobID = select.getString(select.getColumnIndex("JobID"));
//							jobLabel = select.getString(select.getColumnIndex("Value_fa"));
//							jobImage = arrImage[i];
//							searchJobs = new Search_Jobs(jobID, jobLabel, jobImage);
//							jobList.add(searchJobs);
//							// jobLabels[i] =
//							// select.getString(select.getColumnIndex("Value_fa"));
//						}
//						break;
//
//					case 1 :
//						jobList.removeAll(jobList);
//						select = db.ReadfromDB("tblJobs", "Type =1");
//						// jobLabels = new String[select.getCount()];
//						int count = select.getCount() + 1;
//						for (int i = 0; i < count; i++) {
//							select.moveToPosition(i);
//
//							if (i == 6) {
//								jobLabel = act.getString(R.string.khadamati_other);
//							} else {
//								jobID = select.getString(select.getColumnIndex("JobID"));
//								jobLabel = select.getString(select.getColumnIndex("Value_fa"));
//							}
//							jobImage = arrImagesKhadamati[i];
//							searchJobs = new Search_Jobs(jobID, jobLabel, jobImage);
//							jobList.add(searchJobs);
//							// jobLabels[i] =
//							// select.getString(select.getColumnIndex("Value_fa"));
//						}
//						break;
//
//					case 2 :
//						jobList.removeAll(jobList);
//						select = db.ReadfromDB("tblJobs", "Type =2");
//						// jobLabels = new String[select.getCount()];
//						for (int i = 0; i < select.getCount() + 1; i++) {
//							select.moveToPosition(i);
//
//							if (i == 18) {
//								jobLabel = act.getString(R.string.forosh_other);
//							} else {
//								jobID = select.getString(select.getColumnIndex("JobID"));
//								jobLabel = select.getString(select.getColumnIndex("Value_fa"));
//							}
//							jobImage = arrImagesForosh[i];
//							searchJobs = new Search_Jobs(jobID, jobLabel, jobImage);
//							jobList.add(searchJobs);
//							// jobLabels[i] =
//							// select.getString(select.getColumnIndex("Value_fa"));
//						}
//						break;
//
//				}
//				spnsubgroup.setAdapter(new Search_Jobs_Adapter(act, jobList));
//
//			}
//
//			@Override
//			public void onNothingSelected(AdapterView<?> arg0) {
//
//
//			}
//
//		});

		spnsubgroup.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				idsubgrup = Integer.parseInt(jobList.get(arg2).id);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		btnadd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
				AlertDialog.Builder builder = new AlertDialog.Builder(Act_NewJob.this);

				builder.setTitle("Add Photo!");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int item) {

						if (listimages.size() == 2) {
							btnadd.setEnabled(false);
						}					
						if (items[item].equals("Take Photo")) {
							Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
							File f = new File(Environment.getExternalStorageDirectory(), "temp.jpg");
							intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
							startActivityForResult(intent, REQUEST_CAMERA);
						} else if (items[item].equals("Choose from Library")) {
							Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
							intent.setType("image/*");
							startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
						} else if (items[item].equals("Cancel")) {
							dialog.dismiss();
						}

						{

						}
					}
				});
				builder.show();
			}
		});

		btnersal.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				name = edtnamemahal.getText().toString();
				phon = edtphon1.getText().toString();
				modir = edtmodir.getText().toString();
				tozih = edttozihat.getText().toString();
				address = edtaddress.getText().toString();

				if (listimages != null &&name!=null && modir!=null &&address!=null && phon!=null && tozih!=null) {					
					helper.SendRequest("ADDJOB", idsubgrup + "|" + name + "|" + modir + "|" + address + "|" + phon + "|" + tozih, null, true, new RunnableOnCompletion() {

						@Override
						public void run(String[] arResponse) {
							String Ar[] = new String[arResponse.length - 1];
							System.arraycopy(arResponse, 1, Ar, 0, arResponse.length - 1);
							String sp[] = MethodHelper.Split(Ar[0], ValueKeeper.Sp2);
							if (Ar[0] != null) {
								IDSECOUND = Ar[0];
								act.runOnUiThread(new Runnable() {

									@Override
									public void run() {
										Toast.makeText(context, "ثبت شغل شما با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
										for (int i = 0; i < listimages.size(); i++) {
											byte[] imgByByte = null;
											ByteArrayOutputStream output = new ByteArrayOutputStream();
											listimages.get(i).compress(Bitmap.CompressFormat.JPEG, 100, output);
											imgByByte = output.toByteArray();
											helper.SendRequest("ADDPICTURE", IDSECOUND, imgByByte, true, new RunnableOnCompletion() {

												@Override
												public void run(String[] arResponse) {
													String Ar[] = new String[arResponse.length - 1];
													System.arraycopy(arResponse, 1, Ar, 0, arResponse.length - 1);
													String sp[] = MethodHelper.Split(Ar[0], ValueKeeper.Sp2);
													if (Ar[0].equals("OK")) {
														act.runOnUiThread(new Runnable() {
															@Override
															public void run() {
																Toast.makeText(context, "ثبت عکس شما با موفقیت انجام شد", Toast.LENGTH_SHORT).show();
																finish();
															}
														});
													}

												}
											});
										}

									}
								});
							}
						}
					});

				}
				else{
					Toast.makeText(context,PersianReshape.reshape(getString(R.string.str_newjob)), Toast.LENGTH_SHORT).show();
				}
			}
		});
		

		if (flag) {
			Ad_NewJob adapt = new Ad_NewJob(act, listimages);
			gridv.setAdapter(adapt);
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (resultCode == RESULT_OK) {
			if (requestCode == REQUEST_CAMERA) {
				File f = new File(Environment.getExternalStorageDirectory().toString());
				for (File temp : f.listFiles()) {
					if (temp.getName().equals("temp.jpg")) {
						f = temp;
						break;
					}
				}
				try {
					BitmapFactory.Options btmapOptions = new BitmapFactory.Options();

					bm = BitmapFactory.decodeFile(f.getAbsolutePath(), btmapOptions);
					listimages.add(bm);
					flag = true;
					if (flag = true) {
                        Ad_NewJob adapt = new Ad_NewJob(act, listimages);
						gridv.setAdapter(adapt);
					}			
					String path = Environment.getExternalStorageDirectory() + File.separator + "Phoenix" + File.separator + "default";
					f.delete();
					OutputStream fOut = null;
					File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
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
				String tempPath = getPath(selectedImageUri, Act_NewJob.this);
				BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
				bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
				// listimages.clear();
				listimages.add(bm);

				flag = true;		
			}
		}

		if (flag) {

            Ad_NewJob adapt = new Ad_NewJob(act, listimages);
			gridv.setAdapter(adapt);
		}
	}

	public static String getPath(Uri uri, Activity activity) {
		String[] projection = {MediaColumns.DATA};
		Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
		int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

}
