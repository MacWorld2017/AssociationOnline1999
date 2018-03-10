package com.example.zhangfengwei.associationonline.Activities.HttpJson;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.ActiDetail;

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

public class ActiDetail_JSON extends Thread {
    private String url;
    private String url1="http://123.56.12.225:8080/studentOnline/getActivitiesByName/";
    private String url2="";
    private String url3;
    private String name;
    private ImageView imageView;
    private TextView title;
    private TextView start;
    private TextView end;
    private TextView place;
    private TextView content;
    private TextView object;
    private TextView sign_way;
    private TextView leader_name;
    private TextView phone;
    private TextView email;
    private TextView organizer;
    private TextView other;
    private Handler handler;
    private String[] detail=new String[13];
    private TextView ass_id;

    public ActiDetail_JSON(TextView ass_id,String name, ImageView imageView, TextView title,TextView start,TextView end,TextView place,TextView content,TextView object,TextView sign_way,TextView leader_name,TextView phone,TextView email,TextView organizer,TextView other,Handler handler){
        this.name=name;
        this.imageView=imageView;
        this.title=title;
        this.start=start;
        this.end=end;
        this.place=place;
        this.content=content;
        this.object=object;
        this.sign_way=sign_way;
        this.leader_name=leader_name;
        this.phone=phone;
        this.email=email;
        this.organizer=organizer;
        this.other=other;
        this.handler=handler;
        this.ass_id=ass_id;
        for(int i=0;i<detail.length;i++){
            detail[i]="暂无";
        }
    }

    @Override
    public void run(){
        URL httpUrl;
        try {
            httpUrl=new URL(url1+name);
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
                    title.setText(detail[0]);
                    start.setText(detail[1]);
                    end.setText(detail[2]);
                    place.setText(detail[3]);
                    content.setText(detail[4]);
                    object.setText(detail[5]);
                    sign_way.setText(detail[6]);
                    leader_name.setText(detail[7]);
                    phone.setText(detail[8]);
                    email.setText(detail[9]);
                    organizer.setText(detail[10]);
                    other.setText(detail[11]);
                    ass_id.setText(detail[12]);

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
            JSONArray jsonArray=new JSONArray(json);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            //循环遍历，依次取出JSONObject对象
            //用getInt和getString方法取出对应键值
            detail[0]=jsonObject.getString("activityName");
            detail[3]=jsonObject.getString("activityPlace");
            detail[4]=jsonObject.getString("activityContent");
            detail[12]=jsonObject.getLong("associationId")+"";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
