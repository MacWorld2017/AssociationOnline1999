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

import Adapters.DashBoard.AssAll.AssAll;
import Adapters.DashBoard.AssAll.AssAllAdapter;

/**
 * Created by ZFW on 2018/3/4.
 */

public class Search_JSON extends Thread {
    private String url;
    private String url1="http://123.56.12.225:8080/studentOnline/searchAssociationByName/";
    private String url2="";
    private String url3="";
    private String url4="http://123.56.12.225:8080/studentOnline/images/association/";
    private String ass_type;
    private String ass_college;
    private Context context;
    private ListView listview;
    private AssAllAdapter adapter;
    private Handler handler;
    private List<AssAll> allass_data;
    private String type;
    private String keyword;
    public Search_JSON(Handler handler,ListView listview,Context context,String type,String keyword){
        this.context=context;
        this.handler=handler;
        this.listview=listview;
        this.type=type;
        this.keyword=keyword;
    }
    @Override
    public void run(){
        URL httpUrl;
        urlByType(type);
        try {
            httpUrl=new URL(url);
            HttpURLConnection conn=(HttpURLConnection)httpUrl.openConnection();
            conn.setReadTimeout(1000);
            conn.setRequestMethod("GET");
            BufferedReader reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb= new StringBuffer();
            String str;
            while((str=reader.readLine())!=null){
                sb.append(str);
            }
            allass_data=praseJson(sb.toString());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    adapter = new AssAllAdapter(context, R.layout.ass_all_list,allass_data);
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

    private List<AssAll> praseJson(String json){
        List<AssAll> list=new ArrayList<>();
        list.clear();
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i< jsonArray.length(); i++) {
                //循环遍历，依次取出JSONObject对象
                //用getInt和getString方法取出对应键值
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("associationName");
                String type = jsonObject.getString("type");
                int college=jsonObject.getInt("college");
                int id=jsonObject.getInt("id");
                String imageUrl=url4+(id+""+".jpg");
                assByCollege(college);
                assByType(type);
                list.add(new AssAll(name,"位置:"+ass_college+"  "+"类型:"+ass_type,imageUrl,id+""));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
    private void urlByType(String str){
        if(str.equals("ass")){
            url=url1+keyword;
        }
    }
    private void assByType(String num){
        if(num.equals("1")){
            ass_type="兴趣爱好";
        }
        else if(num.equals("2")){
            ass_type="社会实践";
        }
        else if(num.equals("3")){
            ass_type="科技创新";
        }
        else if(num.equals("4")){
            ass_type="综合";
        }
        else if(num.equals("5")){
            ass_type="其他";
        }
    }
    private void assByCollege(int num){
        if(num==1){
            ass_college="中心校区";
        }
        else if(num==2){
            ass_college="洪家楼校区";
        }
        else if(num==3){
            ass_college="千佛山校区";
        }
        else if(num==4){
            ass_college="趵突泉校区";
        }
        else if(num==5){
            ass_college="软件园校区";
        }
        else if(num==6){
            ass_college="兴隆山校区";
        }
        else if(num==7){
            ass_college="青岛校区";
        }
    }
}
