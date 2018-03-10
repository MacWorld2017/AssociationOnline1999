package com.example.zhangfengwei.associationonline.Activities.HttpJson;

import android.os.Handler;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Tools.HttpImage;

/**
 * Created by ZFW on 2018/3/8.
 */

public class AssDetail_Leader_JSON extends Thread {
    private String id;
    private TextView name;
    private TextView phone;
    private TextView email;
    private String ass_name="暂无";
    private String ass_phone="暂无";
    private String ass_email="暂无";
    private String url="http://123.56.12.225:8080/studentOnline/getManager/";
    private Handler handler;
    public AssDetail_Leader_JSON(String id, TextView name,TextView phone,TextView email,Handler handler){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.email=email;
        this.handler=handler;
    }
    @Override
    public void run(){
        URL httpUrl;
        try {
            httpUrl=new URL(url+id);
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
                    name.setText("姓名:"+ass_name);
                    phone.setText("电话:"+ass_phone);
                    email.setText("E-mail:"+ass_email);
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
            ass_name= jsonObject.getString("userName");
            ass_email=jsonObject.getString("email");
            ass_phone=jsonObject.getString("telephone");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
