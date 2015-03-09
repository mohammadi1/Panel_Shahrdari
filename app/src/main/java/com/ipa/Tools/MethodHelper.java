/**
 * Created by TAGHIMOHAMMADI on 28/02/2015.
 */

package com.ipa.Tools;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ipa.Panel_Shahrdari.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class MethodHelper{
    public static String FontName = "BYekan.ttf";
    private static Typeface face;
    public static boolean SHOWLOG = true;
    public static String tag = "KFL";

    /**
     *
     * @param context
     * baraye eemal font be matn
     */
    private static void initTypeFace(Context context) {
        face = Typeface.createFromAsset(context.getAssets(), "font/" + FontName + "");
    }

    private static void initTypeFace(Context context,String fontName)
    {
        face = Typeface.createFromAsset(context.getAssets(),"font/"+fontName+"");
    }

    public static Typeface getTypeFace(Context context) {
        initTypeFace(context);
        return face;
    }

    public static Typeface getTypeFace(Context context,String fontName)
    {
        initTypeFace(context, fontName);
        return face;
    }

    /**
     *
     * @param context
     * @param text
     * baraye namayesh dadane Toast
     */
    public  static void ShowToast(Context context,int text){
        Toast.makeText(context,text,Toast.LENGTH_SHORT).show();

    }

    /**
     *
     * @param context
     * @return WebServiceHeloer
     */
    public static WebServiceHelper CallWebhelper(Context context) {
        WebServiceHelper whelper = new WebServiceHelper(context);
        return whelper;
    }

    /**
     *
     * @param context
     * @param actionBarActivity
     * @Description baraye namayesh ActionBar
     */
    public static void ShowActionBar(Context context,ActionBarActivity actionBarActivity,int name){

        actionBarActivity.getSupportActionBar().setDisplayShowCustomEnabled(true);
        actionBarActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        actionBarActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.act_action_bar, null);
        ((TextView)v.findViewById(R.id.txtaction)).setText(PersianReshape.reshape(context.getString(name)));
        ((TextView)v.findViewById(R.id.txtaction)).setTypeface(MethodHelper.getTypeFace(context, "mjDinarTowMedium.ttf"));
        actionBarActivity.getSupportActionBar().setCustomView(v);

    }

    /**
     *
     * @param original
     * @param separator
     * @return String[]
     */

    public static String[] Split(String original, char separator) {
        if (original.charAt(0) == separator) {
            original = original.substring(1);
        }
        ArrayList lst = new ArrayList();
        char[] chars = original.toCharArray();
        String buff = "";
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == separator) {
                lst.add(buff);
                buff = "";
            } else {
                buff += chars[i];
            }
        }
        // Add Latest Item
        if (buff != "") {
            lst.add(buff);
        }
        String[] ret = new String[lst.size()];
        for (int k = 0; k < lst.size(); k++) {
            ret[k] = lst.get(k).toString();
        }
        return ret;
    }

    /**
     *
     * @param context
     * @param assetsFileDirectory
     * @param diretoryToSave
     * @param DBasePath
     * @param fileName
     * @return boolean
     */
    public static boolean copyFileFromAsset(Context context,String assetsFileDirectory,String diretoryToSave, String DBasePath, String fileName) {
        boolean retValue = false;
        try {
            InputStream is = context.getAssets().open(assetsFileDirectory);
            File soundFile = new File(DBasePath + "/" + diretoryToSave);
            soundFile.mkdirs();
            File dbFile = new File(DBasePath + "/" + diretoryToSave+"/"+fileName);
            if (dbFile.exists()) {
                int sz = (int) dbFile.length();
                int szis = is.available();
                if (sz == szis) {
                    MethodHelper.ShowDebugLog(null, "Copy Database From Assets: DataBase Exists!");
                    return true;
                }
            }

            OutputStream os = new FileOutputStream(dbFile);

            byte[] buffer = new byte[1024];
            while (is.read(buffer) > 0) {
                os.write(buffer);
            }

            os.flush();
            os.close();
            is.close();
            retValue = true;
            MethodHelper.ShowDebugLog(null, "Copy Database From Assets Succeed!");
        } catch (Exception e) {
            MethodHelper.ShowErrorLog(null, "Error Copy Database From Assets: " + e.getLocalizedMessage());
        }
        return retValue;
    }

    /**
     *
     * @param inputArr
     * @param sep
     * @return string
     */
    public static String CompileStringArray(String[] inputArr, char sep) {
        String ret = "";
        for (String str : inputArr) {
            ret += str + sep;
        }
        ret = ret.substring(0, ret.length() - 1);
        return ret;
    }

    /**
     *
     * @param Tag
     * @param Message
     */
    public static void ShowDebugLog(String Tag, String Message) {

        if (!SHOWLOG)
            return;
        if (Tag == null)
            Tag = tag;
        Log.d(Tag, Message);
    }

    /**
     *
     * @param Tag
     * @param ErrText
     */
    public static void ShowErrorLog(String Tag, String ErrText) {
        if (!SHOWLOG)
            return;
        if (Tag == null)
            Tag = tag;
        Log.e(Tag, ErrText);
    }
}
