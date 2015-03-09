package com.ipa.Tools;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ipa.Panel_Shahrdari.R;

import java.util.Timer;
import java.util.TimerTask;


public class MessageBox {

	private static AlertDialog prgDlg;

	public static void ShowTimedOut(final Context context, final String Caption, final String Text, final int TimeOut) {
		if (context == null)
			return;
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				final AlertDialog dialog = new AlertDialog.Builder(context).create();
				dialog.setMessage(Text);
				dialog.setTitle(Caption);
				dialog.setCanceledOnTouchOutside(false);
				final Timer t = new Timer();
				t.schedule(new TimerTask() {
					public void run() {
						dialog.dismiss();
						t.cancel();
					}
				}, TimeOut);

			}
		});

	}

	public static void ShowToast(final Context context, final String Text, final int duration) {
		if (context == null)
			return;
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				Toast.makeText(context, Text, duration).show();

			}
		});

	}

	public static void Show(Context context, String Text, Runnable Button1Func, MessageBoxIcon icon) {
		if (context == null)
			return;
		Show(context, "", Text, MessageBoxButtons.OK, Button1Func, null, null, icon);
	}

	public static void Show(Context context, String Text, Runnable Button1Func) {
		if (context == null)
			return;
		Show(context, "", Text, MessageBoxButtons.OK, Button1Func, null, null, MessageBoxIcon.OK);
	}

	public static void Show(Context context, String Caption, String Text, Runnable Button1Func) {
		if (context == null)
			return;
		Show(context, Caption, Text, MessageBoxButtons.OK, Button1Func, null, null, MessageBoxIcon.OK);
	}

	public static void Show(Context context, String Caption, String Text, Runnable Button1Func, MessageBoxIcon icon) {
		if (context == null)
			return;
		Show(context, Caption, Text, MessageBoxButtons.OK, Button1Func, null, null, icon);
	}

	public static void Show(Context context, String Caption, String Text, MessageBoxButtons buttons, Runnable Button1Func, Runnable Button2Func, Runnable Button3Func) {
		if (context == null)
			return;
		Show(context, Caption, Text, buttons, Button1Func, Button2Func, Button3Func, MessageBoxIcon.OK);
	}

	static String TAG = "HADI";

	public static void Show(final Context context, final String Caption, final String Text, final MessageBoxButtons buttons, final Runnable Button1Func, final Runnable Button2Func,
			final Runnable Button3Func, final MessageBoxIcon icon) {

		if (context == null)
			return;
		HideLoading(context, true);

		Activity act = (Activity) context;
		act.runOnUiThread(new Runnable() {
			@SuppressWarnings("deprecation")
			public void run() {
				final AlertDialog dialog = new AlertDialog.Builder(context).create();
				dialog.setMessage(PersianReshape.reshape(Text));
				dialog.setTitle(PersianReshape.reshape(Caption));
				dialog.setCanceledOnTouchOutside(false);
				MediaHelper.BeepTypes beepType = MediaHelper.BeepTypes.Information;
				Log.d(TAG, "1");
				switch (buttons) {
				case OK:
					dialog.setButton(PersianReshape.reshape(context.getString(R.string.ok)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button1Func != null)
								Button1Func.run();
							else
								dialog.dismiss();
						}
					});
					break;
				case OKCancel:
					dialog.setButton(PersianReshape.reshape(context.getString(R.string.ok)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button1Func != null)
								Button1Func.run();
							else
								dialog.dismiss();
						}
					});
					dialog.setButton2(PersianReshape.reshape(context.getString(R.string.cancel)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button2Func != null)
								Button2Func.run();
							else
								dialog.dismiss();
						}
					});
					break;
				case YesNo:
					dialog.setButton(PersianReshape.reshape(context.getString(R.string.yes)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button1Func != null)
								Button1Func.run();
							else
								dialog.dismiss();
						}
					});
					dialog.setButton2(PersianReshape.reshape(context.getString(R.string.no)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button2Func != null)
								Button2Func.run();
							else
								dialog.dismiss();
						}
					});
					break;
				case YesNoCancel:
					dialog.setButton(PersianReshape.reshape(context.getString(R.string.yes)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button1Func != null)
								Button1Func.run();
							else
								dialog.dismiss();
						}
					});
					dialog.setButton2(PersianReshape.reshape(context.getString(R.string.no)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button2Func != null)
								Button2Func.run();
							else
								dialog.dismiss();
						}
					});
					dialog.setButton3(PersianReshape.reshape(context.getString(R.string.cancel)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button3Func != null)
								Button3Func.run();
							else
								dialog.dismiss();
						}
					});
					break;
				case RetryCancel:
					dialog.setButton(PersianReshape.reshape(context.getString(R.string.retry)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button1Func != null)
								Button1Func.run();
							else
								dialog.dismiss();
						}
					});
					dialog.setButton2(PersianReshape.reshape(context.getString(R.string.cancel)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button2Func != null)
								Button2Func.run();
							else
								dialog.dismiss();
						}
					});
					break;
				case AbortRetryIgnore:
					dialog.setButton(PersianReshape.reshape(context.getString(R.string.cancel)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button1Func != null)
								Button1Func.run();
							else
								dialog.dismiss();
						}
					});
					dialog.setButton2(PersianReshape.reshape(context.getString(R.string.retry)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button2Func != null)
								Button2Func.run();
							else
								dialog.dismiss();
						}
					});
					dialog.setButton3(PersianReshape.reshape(context.getString(R.string.ignore)), new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							if (Button3Func != null)
								Button3Func.run();
							else
								dialog.dismiss();
						}
					});
					break;

				default:
					break;
				}

				switch (icon) {
				case Error:
					dialog.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
					beepType = MediaHelper.BeepTypes.Error;
					break;
				case OK:
					dialog.setIcon(android.R.drawable.ic_menu_info_details);
					beepType = MediaHelper.BeepTypes.Information;
					break;
				case Question:
					dialog.setIcon(android.R.drawable.ic_menu_help);
					beepType = MediaHelper.BeepTypes.Question;
					break;
				case Stop:
					dialog.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
					beepType = MediaHelper.BeepTypes.Error;
					break;
				case Warning:
					dialog.setIcon(android.R.drawable.ic_menu_report_image);
					beepType = MediaHelper.BeepTypes.Warning;
					break;
				default:
					dialog.setIcon(android.R.drawable.ic_menu_info_details);
					break;
				}

				dialog.show();
				if (ValueKeeper.PlayAudioAlerts)
					MediaHelper.PlayBeep(context, beepType);
				// return dialog;
			}
		});

	}

	public static enum MessageBoxButtons {
		AbortRetryIgnore, OK, OKCancel, RetryCancel, YesNo, YesNoCancel
	}

	public static enum MessageBoxIcon {
		Error, OK, Question, Warning, Stop
	}

	public static enum MessageBoxDefaultButton {
		Button1, Button2, Button3
	}

	public static void ShowLoading(final Context context, final String Title, final String Msg, final boolean Indeterminate) {
		((Activity) context).runOnUiThread(new Runnable() {

			@Override
			public void run() {
				try {
					if (prgDlg.isShowing()) {
						return;
					}
				} catch (Exception e) {
				}

				String msgTitle = Title;
				String msgText = Msg;
				LayoutInflater inflater = ((Activity) context).getLayoutInflater();
				View v = inflater.inflate(R.layout.loading_dialog, null);
				TextView lblLoadingTitle = (TextView) v.findViewById(R.id.lblLoadingTitle);
				TextView lblLoadingText = (TextView) v.findViewById(R.id.lblLoadingText);
				lblLoadingTitle.setTypeface(MethodHelper.getTypeFace(context));
				lblLoadingText.setTypeface(MethodHelper.getTypeFace(context));

				if (msgTitle == null)
					msgTitle = context.getString(R.string.PleaseWait);
				if (msgTitle.equals(""))
					msgTitle = context.getString(R.string.PleaseWait);

				if (msgText == null)
					lblLoadingText.setVisibility(View.INVISIBLE);

				if (msgText == null)
					msgText = context.getString(R.string.ConnectingToServer);
				if (msgText.equals(""))
					msgText = context.getString(R.string.ConnectingToServer);

				lblLoadingTitle.setText(PersianReshape.reshape(msgTitle));
				lblLoadingText.setText(PersianReshape.reshape(msgText));

				ImageView imgLoading = (ImageView) v.findViewById(R.id.imgLoading);
				GifAnimationDrawable drawable;
				try {
					drawable = new GifAnimationDrawable(context.getResources().openRawResource(R.raw.animated_loading_blue), true);

					imgLoading.setImageDrawable(drawable);

					drawable.setVisible(true, true);
				} catch (Exception e) {
				}

				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setView(v);
				prgDlg = builder.show();
				prgDlg.setCanceledOnTouchOutside(false);

			}
		});
	}

	public static void HideLoading(Context context) {
		try {
			((Activity) context).runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (prgDlg != null && !ValueKeeper.DontHideLoading) {
						prgDlg.dismiss();

					}

				}
			});

		} catch (Exception ex) {
		}
	}

	public static void HideLoading(Context context, final boolean isForce) {
		try {
			((Activity) context).runOnUiThread(new Runnable() {

				@Override
				public void run() {
					if (prgDlg != null && (!ValueKeeper.DontHideLoading || isForce)) {
						prgDlg.dismiss();
					}

				}
			});

		} catch (Exception ex) {
		}
	}

	public static void Show(Context context, int Text_id, Runnable Button1Func, MessageBoxIcon icon) {
		if (context == null)
			return;
		Show(context, "", context.getString(Text_id), MessageBoxButtons.OK, Button1Func, null, null, icon);
	}

	public static void Show(Context context, int Text_id, Runnable Button1Func) {
		if (context == null)
			return;
		Show(context, "", context.getString(Text_id), MessageBoxButtons.OK, Button1Func, null, null, MessageBoxIcon.OK);
	}

	public static void Show(Context context, int Caption_id, int Text_id, Runnable Button1Func) {
		if (context == null)
			return;
		Show(context, context.getString(Caption_id), context.getString(Text_id), MessageBoxButtons.OK, Button1Func, null, null, MessageBoxIcon.OK);
	}

	public static void Show(Context context, int Caption_id, int Text_id, Runnable Button1Func, MessageBoxIcon icon) {
		if (context == null)
			return;
		Show(context, context.getString(Caption_id), context.getString(Text_id), MessageBoxButtons.OK, Button1Func, null, null, icon);
	}

	public static void Show(Context context, int Caption_id, int Text_id, MessageBoxButtons buttons, Runnable Button1Func, Runnable Button2Func, Runnable Button3Func) {
		if (context == null)
			return;
		Show(context, context.getString(Caption_id), context.getString(Text_id), buttons, Button1Func, Button2Func, Button3Func, MessageBoxIcon.OK);
	}

	public static void Show(final Context context, int Caption_id, int Text_id, final MessageBoxButtons buttons, final Runnable Button1Func, final Runnable Button2Func, final Runnable Button3Func,
			final MessageBoxIcon icon) {

		if (context == null)
			return;
		Show(context, context.getString(Caption_id), context.getString(Text_id), buttons, Button1Func, Button2Func, Button3Func, MessageBoxIcon.OK);
	}

	public static void Show(Context context, int Caption_id, String text, Runnable Button1Func) {
		if (context == null)
			return;
		Show(context, context.getString(Caption_id), text, MessageBoxButtons.OK, Button1Func, null, null, MessageBoxIcon.OK);
	}

	public static void Show(Context context, int Caption_id, String text, Runnable Button1Func, MessageBoxIcon icon) {
		if (context == null)
			return;
		Show(context, context.getString(Caption_id), text, MessageBoxButtons.OK, Button1Func, null, null, icon);
	}

	public static void Show(Context context, int Caption_id, String text, MessageBoxButtons buttons, Runnable Button1Func, Runnable Button2Func, Runnable Button3Func) {
		if (context == null)
			return;
		Show(context, context.getString(Caption_id), text, buttons, Button1Func, Button2Func, Button3Func, MessageBoxIcon.OK);
	}

	public static void Show(final Context context, int Caption_id, String text, final MessageBoxButtons buttons, final Runnable Button1Func, final Runnable Button2Func, final Runnable Button3Func,
			final MessageBoxIcon icon) {

		if (context == null)
			return;
		Show(context, context.getString(Caption_id), text, buttons, Button1Func, Button2Func, Button3Func, MessageBoxIcon.OK);
	}

	public static void Show(Context context, String Caption, int Text_id, Runnable Button1Func) {
		if (context == null)
			return;
		Show(context, Caption, context.getString(Text_id), MessageBoxButtons.OK, Button1Func, null, null, MessageBoxIcon.OK);
	}

	public static void Show(Context context, String Caption, int Text_id, Runnable Button1Func, MessageBoxIcon icon) {
		if (context == null)
			return;
		Show(context, Caption, context.getString(Text_id), MessageBoxButtons.OK, Button1Func, null, null, icon);
	}

	public static void Show(Context context, String Caption, int Text_id, MessageBoxButtons buttons, Runnable Button1Func, Runnable Button2Func, Runnable Button3Func) {
		if (context == null)
			return;
		Show(context, Caption, context.getString(Text_id), buttons, Button1Func, Button2Func, Button3Func, MessageBoxIcon.OK);
	}

	public static void Show(final Context context, String Caption, int Text_id, final MessageBoxButtons buttons, final Runnable Button1Func, final Runnable Button2Func, final Runnable Button3Func,
			final MessageBoxIcon icon) {

		if (context == null)
			return;
		Show(context, Caption, context.getString(Text_id), buttons, Button1Func, Button2Func, Button3Func, MessageBoxIcon.OK);
	}

}
