package com.example.zhangfengwei.associationonline.Activities.HttpJson;

import android.os.Handler;
import android.widget.TextView;

import org.json.JSONArray;
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

public class PreviousActi_Small_JSON extends Thread {
    private String url="http://123.56.12.225:8080/studentOnline/getActivitiesByAssociationId/";
    private TextView acti1;
    private TextView acti2;
    private String id;
    private Handler handler;
    private String name1="暂无";
    private String name2="暂无";
    public PreviousActi_Small_JSON(String id,TextView acti1,TextView acti2,Handler handler){
        this.handler=handler;
        this.id=id;
        this.acti1=acti1;
        this.acti2=acti2;
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
                    acti1.setText(name1);
                    acti2.setText(name2);
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
            JSONArray jsonArray = new JSONArray(json);
                //循环遍历，依次取出JSONObject对象
                //用getInt和getString方法取出对应键值
            if(jsonArray.length()>=2){
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                JSONObject jsonObject2 = jsonArray.getJSONObject(1);
                name1=jsonObject1.getString("activityName");
                name2=jsonObject2.getString("activityName");
            }
            else{
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                name1=jsonObject1.getString("activityName");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
