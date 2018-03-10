package com.example.zhangfengwei.associationonline.Activities.HttpJson;

import android.content.Context;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Adapters.DashBoard.AssAll.AssAll;
import Adapters.DashBoard.AssAll.AssAllAdapter;
import Tools.HttpImage;

/**
 * Created by ZFW on 2018/3/8.
 */

public class AssDetail_JSON extends Thread {
    private String url;
    private String url1="http://123.56.12.225:8080/studentOnline/getAssociationById/";
    private String url2="http://123.56.12.225:8080/studentOnline/images/association/";
    private String url3;
    private String id;
    private TextView name;
    private TextView intro;
    private TextView acti1;
    private TextView acti2;
    private TextView leader_name;
    private TextView leader_phone;
    private TextView leader_email;
    private ImageView imageView;
    private Context context;
    private Handler handler;
    private String[] detail =new String[7];
    public AssDetail_JSON(Handler handler, String id, TextView name, ImageView imageView,TextView intro, TextView acti1, TextView acti2, TextView leader_name, TextView leader_phone, TextView leader_email, Context context){
        this.context=context;
        this.handler=handler;
        this.name=name;
        this.intro=intro;
        this.acti1=acti1;
        this.acti2=acti2;
        this.leader_name=leader_name;
        this.leader_phone=leader_phone;
        this.leader_email=leader_email;
        this.id=id;
        this.imageView=imageView;
    }
    @Override
    public void run(){
        URL httpUrl;
        try {
            httpUrl=new URL(url1+id);
            HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
            conn.setReadTimeout(1000);
            conn.setRequestMethod("GET");
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb= new StringBuffer();
            String str;
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            praseJson(sb.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    name.setText(detail[0]);
                    intro.setText(detail[1]);
                    new HttpImage(detail[3],handler,imageView).start();
                    new PreviousActi_Small_JSON(id,acti1,acti2,handler).start();
                    new AssDetail_Leader_JSON(detail[4],leader_name,leader_phone,leader_email,handler).start();
                }
            });
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void praseJson(String json){
        try {
            JSONObject jsonObject = new JSONObject(json);
            //循环遍历，依次取出JSONObject对象
            //用getInt和getString方法取出对应键值
            detail[0]= jsonObject.getString("associationName");
            System.out.print(detail[0]);
            detail[1]=jsonObject.getString("introduction");
            detail[2]=(jsonObject.getLong("id"))+"";
            detail[3]=url2+(detail[2]+".jpg");
            detail[4]=jsonObject.getLong("managerId")+"";

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
