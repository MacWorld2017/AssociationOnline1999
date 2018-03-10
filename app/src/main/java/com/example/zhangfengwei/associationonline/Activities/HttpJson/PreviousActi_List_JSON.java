package com.example.zhangfengwei.associationonline.Activities.HttpJson;

import android.content.Context;
import android.os.Handler;
import android.widget.ListView;

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

import Adapters.DashBoard.AssAll.AssAllAdapter;
import Adapters.PreviousActivity.PreviousActivity;
import Adapters.PreviousActivity.PreviousActivityAdapter;

/**
 * Created by ZFW on 2018/3/8.
 */

public class PreviousActi_List_JSON extends Thread {
    private String url;
    private String url1="http://123.56.12.225:8080/studentOnline/getActivitiesByAssociationId/";
    private String url2="";
    private String url3="";
    private String url4="";
    private String url5="";
    private Long time;
    private Long ass_id;
    private int college=0;
    private Context context;
    private ListView listview;
    private PreviousActivityAdapter adapter;
    private Handler handler;
    private List<PreviousActivity> acti_data;
    public PreviousActi_List_JSON(Handler handler,ListView listview,Context context,Long ass_id,int college,Long time){
        this.context=context;
        this.handler=handler;
        this.listview=listview;
        this.ass_id=ass_id;
        this.college=college;
        this.time=time;

    }
    @Override
    public void run(){
        URL httpUrl;
//        urlByType(position1,position2);
        try {
            httpUrl=new URL(url1+ass_id);
            HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
            conn.setReadTimeout(1000);
            conn.setRequestMethod("GET");
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb= new StringBuffer();
            String str;
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            acti_data=praseJson(sb.toString(),college,time);
            handler.post(new Runnable() {
                @Override
                public void run() {
                  adapter = new PreviousActivityAdapter(context,R.layout.list_previous_acti,acti_data);
                    listview.setAdapter(adapter);
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

    private List<PreviousActivity> praseJson(String json,int pos1,Long pos2){
        List<PreviousActivity> list=new ArrayList<>();
        list.clear();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i< jsonArray.length(); i++) {
                //循环遍历，依次取出JSONObject对象
                //用getInt和getString方法取出对应键值
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if(pos1==0&&pos2==0){
                    String name = jsonObject.getString("activityName");
                    Long id=jsonObject.getLong("id");
                    list.add(new PreviousActivity(name,id+""));
                }
                else if(pos1!=0&&pos2==0){
                    if(jsonObject.getInt("college")==pos1){
                        String name = jsonObject.getString("activityName");
                        Long id=jsonObject.getLong("id");
                        list.add(new PreviousActivity(name,id+""));
                    }
                }
                else if(pos1==0&&pos2!=0){
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
//    private void urlByType(int num1,int num2){
//        if(num1==0&&num2==0){
//            url=url1;
//        }
//        else if(num1==0&&num2!=0){
//            url=url3+(""+num2);
//        }
//        else if(num1!=0&&num2==0){
//            url=url2+(""+num1);
//        }
//        else if(num1!=0&&num2!=0){
//            url=url4+(""+num1)+"/"+num2;
//        }
//    }
//    private void assByType(String num){
//        if(num.equals("1")){
//            ass_type="兴趣爱好";
//        }
//        else if(num.equals("2")){
//            ass_type="社会实践";
//        }
//        else if(num.equals("3")){
//            ass_type="科技创新";
//        }
//        else if(num.equals("4")){
//            ass_type="综合";
//        }
//        else if(num.equals("5")){
//            ass_type="其他";
//        }
//    }
}