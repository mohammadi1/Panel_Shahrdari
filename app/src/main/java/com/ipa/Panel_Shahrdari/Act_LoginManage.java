package com.ipa.Panel_Shahrdari;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.ipa.Adapter.Ad_LoginManage;
import com.ipa.Tools.Cls_LoginModel;
import com.ipa.Tools.MethodHelper;
import com.ipa.Tools.RunnableOnCompletion;
import com.ipa.Tools.ValueKeeper;
import com.ipa.Tools.WebServiceHelper;

import java.util.ArrayList;

public class Act_LoginManage extends ActionBarActivity {

    Context context;
    Activity act;
    WebServiceHelper whelper;
    ArrayList<Cls_LoginModel> listlogin = new ArrayList<Cls_LoginModel>();
    Cls_LoginModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context=this;
        act=this;
        whelper=new WebServiceHelper(context);
        setContentView(R.layout.act_loginmanage);
        MethodHelper.ShowActionBar(context,this,R.string.str_loginm);
        final ListView lstlogin=(ListView)findViewById(R.id.lstlogin);
        whelper.SendRequest("VALIDREGISTER", null, null, true, new RunnableOnCompletion() {

            @Override
            public void run(String[] arResponse) {
                String[] Ar=new String[arResponse.length-1];
                System.arraycopy(arResponse, 1, Ar, 0, arResponse.length-1);
                for (int i = 0; i < Ar.length; i++) {
                    String[] sp= MethodHelper.Split(Ar[i], ValueKeeper.Sp2);
                    int id=Integer.parseInt(sp[0]);
                    String name=sp[1];
                    String imagadd=sp[5];
                    String valid=sp[6];
                    if (valid.equals("False")) {
                        listlogin.add(new Cls_LoginModel(name, imagadd,id,valid));
                    }
                }
                final Ad_LoginManage ad=new Ad_LoginManage((Activity)context, listlogin);
                act.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        lstlogin.setAdapter(ad);

                    }
                });
            }
        });
    }
}
