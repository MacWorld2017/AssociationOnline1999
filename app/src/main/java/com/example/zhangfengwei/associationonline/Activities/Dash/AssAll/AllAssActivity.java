package com.example.zhangfengwei.associationonline.Activities.Dash.AssAll;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import com.example.zhangfengwei.associationonline.Activities.HttpJson.AllAss_JSON;
import com.example.zhangfengwei.associationonline.Activities.MainActivity;
import com.example.zhangfengwei.associationonline.Activities.SearchActivity;
import com.example.zhangfengwei.associationonline.R;
import com.yalantis.phoenix.PullToRefreshView;


import Adapters.DashBoard.AssAll.AssAll;

public class AllAssActivity extends AppCompatActivity {
    private List<AssAll> dashList=new ArrayList<>();
    private ImageButton back;
    private ImageButton srch;
    private int position1=0;
    private int position2=0;
    private ListView list;
    private TextView title;
    private TextView other;
    private String name;
    private String id2;
    private PullToRefreshView mPullToRefreshView;
    private EditText find;
    private String keyword;
    private Handler handler=new Handler();
//    private AssAllAdapter adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_ass);
        mPullToRefreshView=(PullToRefreshView)findViewById(R.id.ass_all_refresh);

//        adapter3 = new AssAllAdapter(AllAssActivity.this,R.layout.ass_all_list,dashList);
        back=(ImageButton)findViewById(R.id.back_dashboard);
        srch=(ImageButton)findViewById(R.id.search_ass_all1);
        find=(EditText)findViewById(R.id.ass_find_text);
        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(AllAssActivity.this,SearchActivity.class);
                mainIntent.putExtra("back_pos","AllAssActivity");
                keyword=find.getText().toString();
                mainIntent.putExtra("keyword_srch",keyword);
                startActivity(mainIntent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(AllAssActivity.this, MainActivity.class);
                mainIntent.putExtra("cmd","Dashboard");
                startActivity(mainIntent);
                finish();
            }
        });
        list=(ListView)findViewById(R.id.ass_all_list);
//        list.setAdapter(adapter3);
        new AllAss_JSON(handler,list,AllAssActivity.this,position1,position2).start();
        Spinner spinner1 = (Spinner) findViewById(R.id.ass_all_spinner1);
        String[] mItems1 = getResources().getStringArray(R.array.type);
// 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems1);
        //设置下拉之前的样式
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //下拉后的样式
//绑定 Adapter到控件
        spinner1 .setAdapter(adapter1);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos1, long id) {
                String[] type = getResources().getStringArray(R.array.type);
                String str1=type[pos1];
                position1=pos1;
                Toast.makeText(AllAssActivity.this, "您选择的兴趣类型是"+str1, Toast.LENGTH_SHORT).show();
                new AllAss_JSON(handler,list,AllAssActivity.this,position1,position2).start();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        Spinner spinner2 = (Spinner) findViewById(R.id.ass_all_spinner2);
        String[] mItems2 = getResources().getStringArray(R.array.location);
// 建立Adapter并且绑定数据源
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems2);
        //设置下拉之前的样式
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //下拉后的样式
//绑定 Adapter到控件
        spinner2 .setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos2, long id) {

                String[] location = getResources().getStringArray(R.array.location);
                String str2=location[pos2];
                position2=pos2;
                Toast.makeText(AllAssActivity.this, "你选择的校区是"+str2, Toast.LENGTH_SHORT).show();
                new AllAss_JSON(handler,list,AllAssActivity.this,position1,position2).start();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new AllAss_JSON(handler,list,AllAssActivity.this,position1,position2).start();
//                        list.setAdapter(adapter3);
//                        initDash();
                        try {
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //3秒后将下拉刷新的状态变为刷新完成
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 5000);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title=(TextView)view.findViewById(R.id.ass_name3);
                other=(TextView)view.findViewById(R.id.ass_id2);
                name=title.getText().toString();
                id2=other.getText().toString();
                Intent intent=new Intent(AllAssActivity.this,AssDetail.class);
                intent.putExtra("ass_name3",name);
                intent.putExtra("ass_id3",id2);
                SharedPreferences.Editor editor2 = getSharedPreferences("data2", MODE_PRIVATE).edit();
                editor2. putString("back_activity2", "AllAssActivity");
                editor2.apply();
                startActivity(intent);
                finish();
            }
        });

    }
//        private void initDash() {
//        dashList.clear();
//        AssAll apple1 = new AssAll("","和有意思的人在一起",R.drawable.icon_all_ass);
//        dashList.add(apple1);
//        AssAll apple2 = new AssAll("学生在","和有意思的人在一起",R.drawable.icon_all_ass);
//        dashList.add(apple2);
//        AssAll apple3 = new AssAll("学生","和有意思的人在一起",R.drawable.icon_all_ass);
//        dashList.add(apple3);
//        AssAll apple4 = new AssAll("学","和有意思的人在一起",R.drawable.icon_all_ass);
//        dashList.add(apple4);
//    }
}

