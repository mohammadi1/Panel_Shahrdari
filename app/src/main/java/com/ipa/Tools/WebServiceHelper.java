package com.ipa.Tools;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;

import com.ipa.Panel_Shahrdari.Act_Login;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



//import android.annotation.SuppressLint;

/**
 * 
 * @author MrHadi
 */
// @SuppressLint("DefaultLocale")
public class WebServiceHelper {

	static Context context;

	public WebServiceHelper(Context con) {
		context = con;
	}

	private void enableInternet(boolean yes) {
		ConnectivityManager iMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		Method iMthd = null;
		try {
			iMthd = ConnectivityManager.class.getDeclaredMethod("setMobileDataEnabled", boolean.class);
		} catch (Exception e) {
		}
		iMthd.setAccessible(false);

		if (yes) {

			try {
				iMthd.invoke(iMgr, true);

			} catch (Exception ex) {
				MethodHelper.ShowErrorLog(null, "Error Set GPRS ON: " + ex.getMessage());
			}

		} else {
			try {
				iMthd.invoke(iMgr, true);
			} catch (Exception e) {

                MethodHelper.ShowErrorLog(null, "Error Set GPRS OFF: " + e.getMessage());
			}
		}
	}

	public static boolean CheckInternetConnection(Context con, String RequestName) {
		int reqID = 10000;
		if (RequestName != null) {
			char[] ar = RequestName.toCharArray();
			for (char ch : ar) {
				reqID += (int) ch;
			}
		}

        MethodHelper.ShowDebugLog(null, "Checking Internet Conectivity...");
		ConnectivityManager cm = (ConnectivityManager) con.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnected()) {
            MethodHelper.ShowDebugLog(null, "Connection Found,Testing Internet...");
			// return true;
			try {
				URL url = new URL("http://www.kanoon.ir/Common/PageCommon/MobileLog.aspx?c=" + ValueKeeper.UserName + "&sid=" + reqID);
				HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
				urlc.setConnectTimeout(5000);
				urlc.connect();
				if (urlc.getResponseCode() == 200) {
                    MethodHelper.ShowDebugLog(null, "Internet Connected");
					return true;
				}
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        MethodHelper.ShowDebugLog(null, "No internet Connection!");
		return false;
	}

	public void SendRequest(final String RequestName, final String RequestParams, final byte[] RequestData, boolean isUserPasRequired, final RunnableOnCompletion runnable) {
		try {
            MethodHelper.ShowDebugLog(null, "With Parameters:" + RequestParams);

			if (ValueKeeper.UserName.equals("") && ValueKeeper.Password.equals("") && isUserPasRequired) {
				MessageBox.HideLoading(context);
				context.startActivity(new Intent(context, Act_Login.class));
				return;
			}

			Thread tr = new Thread(new Runnable() {
				public void run() {

					ReqFromWS(RequestName, RequestParams, RequestData, ValueKeeper.UserName, ValueKeeper.Password, false, runnable);
				}
			});
			tr.start();
			MessageBox.ShowLoading(context, "", "", true);
		} catch (Exception e) {
            MethodHelper.ShowErrorLog(null, "Error Send Request: " + e.getMessage());
		} catch (Error e) {

		}
	}
//--------------------------------------------
	public void SendHiddenRequest(final String RequestName, final String RequestParams, final byte[] RequestData, boolean isUserPasRequired, final RunnableOnCompletion runnable) {

		Thread tr = new Thread(new Runnable() {
			public void run() {

				ReqFromWS(RequestName, RequestParams, RequestData, ValueKeeper.UserName, ValueKeeper.Password, true, runnable);
			}
		});
		tr.start();
	}
//	----------------------------------------------------

	int RetryCount = 3;
	boolean Use_Encryption = false;
	boolean Convert_ToHex = false;

	private void ReqFromWS(String RequestName, String RequestParams, byte[] RequestData, String SenderID, String SenderPass, boolean isHidden, RunnableOnCompletion runnable) {

		try {
			if (CheckInternetConnection(context, RequestName)) {
				

				String ret_Key = "rtrnky89rtrnky89";
				for (int i = 1; i <= RetryCount; i++) {
					MethodHelper.ShowDebugLog(null, "ReqFromWs Retry No: " + i);
					Object Out = null;
					try {
						MethodHelper.ShowDebugLog(null, "Sending Request To WS :");
						MethodHelper.ShowDebugLog(null, "Request Name: " + RequestName);
						MethodHelper.ShowDebugLog(null, "Request Params: " + RequestParams);
						MethodHelper.ShowDebugLog(null, "SenderID: " + SenderID);
						// MethodHelper.ShowDebugLog(null,"SenderPass: "+SenderPass);
						SoapObject request = new SoapObject(ValueKeeper.NAMESPACE, ValueKeeper.METHOD_NAME);
						request.addProperty("ReqType", RequestName);
						request.addProperty("ReqParams", RequestParams);
						request.addProperty("MID", "1");
						if (RequestData != null) {
							MethodHelper.ShowDebugLog(null, "RequestImage Len: " + RequestData.length + " Convering To Base64...");
							request.addProperty("ReqData", Base64.encodeToString(RequestData, Base64.DEFAULT));
						}
						
							request.addProperty("UserName", SenderID);
						
						request.addProperty("Password", SenderPass);

						SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

						envelope.dotNet = true;
						envelope.setOutputSoapObject(request);
						HttpTransportSE Transport = new HttpTransportSE(ValueKeeper.ServerURL + "/" + ValueKeeper.WSURL);
						Transport.call(ValueKeeper.SOAP_ACTION, envelope);
						SoapObject Result = (SoapObject) envelope.bodyIn;

						Out = Result.getProperty(0);// GET REQ RESULT
						

						if (!isHidden) {
							
								WebDataRecived(Out, runnable);
						} else {
							
								WebDataRecieved_Hidden(Out, runnable);
						}

						break;
					} catch (Exception ex) {
						if (i == RetryCount) {
							String err = "";
							if (ex != null)
								err = ex.getMessage();
							MethodHelper.ShowErrorLog(null, "Error Requesting WS: " + err);
							if (!isHidden) {
								MessageBox.Show(context, "خطا!", "خطا در برقراری ارتباط با سرور", MessageBox.MessageBoxButtons.OK, null, null, null, MessageBox.MessageBoxIcon.Error);
							}
							break;
						}

					}
				}
			} else {
				MethodHelper.ShowErrorLog(null, "Error Connecting To Internet ");
				if (!isHidden)
					MessageBox.Show(context, "خطا!", "خطا در اتصال به اینترنت", MessageBox.MessageBoxButtons.OK, null, null, null, MessageBox.MessageBoxIcon.Error);
			}
		} catch (Exception e) {
			MethodHelper.ShowErrorLog(null, "Error ReqFromWS: " + e.getLocalizedMessage());
		}

	}

//	-------------------------------------------------------------

	public void WebDataRecieved_Hidden(Object obj, RunnableOnCompletion runnable) {
		try {
			MethodHelper.ShowDebugLog(null, "WebData Recieved!");
			MethodHelper.ShowDebugLog(null, "Proccessing Data...");
			MessageBox.HideLoading(context);
			if (obj.getClass().equals(String.class)) {
				MethodHelper.ShowDebugLog(null, "String Data: " + obj.toString());
				MethodHelper.ShowDebugLog(null, "Splitting Request...");
				String[] arResponse = MethodHelper.Split(obj.toString(), ValueKeeper.Sp1);
				MethodHelper.ShowDebugLog(null, "Response Type is: " + arResponse[0]);
				String ResponseType = arResponse[0].toLowerCase();
				if ((!ResponseType.equalsIgnoreCase("CONTACTMSG")) && (!arResponse[1].equals("NEWSATTACHMENT")) && (!ResponseType.equals("KANOONTIZER"))) {
					if (CheckResponseForErrors_Hidden(arResponse))
						return;
				}
				runnable.run(arResponse);
			}
		} catch (Exception e) {
			MethodHelper.ShowErrorLog(null, "Server Side Error: " + e.getLocalizedMessage());
		}
	}

	
//	-----------------------------------------------------------------------------------

	public void WebDataRecived(Object obj, RunnableOnCompletion runnable) {
		try {
			MethodHelper.ShowDebugLog(null, "WebData Recieved!");
			MethodHelper.ShowDebugLog(null, "Proccessing Data...");
			MessageBox.HideLoading(context);
			if (obj.getClass().equals(String.class)) {
				MethodHelper.ShowDebugLog(null, "String Data: " + obj.toString());
				MethodHelper.ShowDebugLog(null, "Splitting Request...");
				String[] arResponse = MethodHelper.Split(obj.toString(), ValueKeeper.Sp1);
				MethodHelper.ShowDebugLog(null, "Response Type is: " + arResponse[0]);
				String ResponseType = arResponse[0].toLowerCase();
				if (!ResponseType.equalsIgnoreCase("login") && !ResponseType.equalsIgnoreCase("VALIDATIONREGISTER") ) {
					if (CheckResponseForErrors(arResponse))
						return;
				}
				runnable.run(arResponse);
			}
		} catch (Exception e) {
			MessageBox.Show(context, "خطا!", "خطا در مرکز", MessageBox.MessageBoxButtons.OK, null, null, null, MessageBox.MessageBoxIcon.Error);
		}
		
	}



	private boolean CheckResponseForErrors(String[] arResponse) {
		boolean retValue = false;
		try {
			if (arResponse[2].equals("-1")) // Error
			{
				MethodHelper.ShowDebugLog(null, " Error -1");
				String Err = "";
				try {
					Err = arResponse[2];
				} catch (Exception e) {
				}
				MessageBox.Show(context, "خطا!", "خطا در پردازش درخواست توسط سرور : " + Err, MessageBox.MessageBoxButtons.OK, null, null, null, MessageBox.MessageBoxIcon.Error);
				retValue = true;
			} else if (arResponse[2].equals("-2")) // Error
			{
				MethodHelper.ShowDebugLog(null, " Error -2");
				String Err = "";
				// ********************SHOW CUSTOM MESSAGE FOR IDEA
			
					MessageBox.Show(context, "خطا!", "اطلاعاتی برای نمایش یافت نشد", MessageBox.MessageBoxButtons.OK, null, null, null, MessageBox.MessageBoxIcon.Error);
								retValue = true;
			}
		} catch (Exception e) {

		}
		if (arResponse[1].equals("-1")) // Error
		{
			MethodHelper.ShowDebugLog(null, " Error -1");
			String Err = "";
			try {
				Err = arResponse[2];
			} catch (Exception e) {
			}
			MessageBox.Show(context, "خطا!", "خطا در پردازش درخواست توسط سرور : " + Err, MessageBox.MessageBoxButtons.OK, null, null, null, MessageBox.MessageBoxIcon.Error);
			retValue = true;
		} else if (arResponse[1].equals("-2")) // Error
		{
			MethodHelper.ShowDebugLog(null, " Error -2");
			String Err = "";

			
				MessageBox.Show(context, "خطا!", "اطلاعاتی برای نمایش یافت نشد", MessageBox.MessageBoxButtons.OK, null, null, null, MessageBox.MessageBoxIcon.Error);
						retValue = true;
		}
		return retValue;
	}

	private boolean CheckResponseForErrors_Hidden(String[] arResponse) {
		boolean retValue = false;
		try {
			if (arResponse[2].equals("-1")) // Error
			{

				String Err = "";
				try {
					Err = arResponse[2];
				} catch (Exception e) {
				}
				MethodHelper.ShowDebugLog(null, " Error -1: " + Err);
				retValue = true;
			} else if (arResponse[2].equals("-2")) // Error
			{
				MethodHelper.ShowDebugLog(null, " Error -2: No Data To Display");
				retValue = true;
			}
		} catch (Exception e) {
		}
		if (arResponse[1].equals("-1")) // Error
		{
			String Err = "";
			try {
				Err = arResponse[2];
			} catch (Exception e) {
			}
			MethodHelper.ShowDebugLog(null, " Error -1: " + Err);
			retValue = true;
		} else if (arResponse[1].equals("-2")) // Error
		{
			MethodHelper.ShowDebugLog(null, " Error -2: No Data To Display");
			retValue = true;
		}
		return retValue;
	}


	public boolean SearchForVoice(String DirectoryPath, String FileName) {
		File ReciverVoice = new File(DirectoryPath);
		ReciverVoice.mkdirs();
		String[] VoiceFile = ReciverVoice.list();
		if (VoiceFile.length > 0) {
			for (int Count = 0; Count < VoiceFile.length; Count++) {
				if (VoiceFile[Count].equals(FileName))
					return true;
			}
		}
		return false;
	}

}
