package com.example.zhangfengwei.associationonline.Activities.Dash.AssAll;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.Activities.HttpJson.AssDetail_JSON;
import com.example.zhangfengwei.associationonline.Activities.MainActivity;
import com.example.zhangfengwei.associationonline.Activities.SearchActivity;
import com.example.zhangfengwei.associationonline.R;

public class AssDetail extends AppCompatActivity {
    private TextView text;
    private Intent intent;
//    private String str1;
    private String str2;
    private String id;
    private ImageButton back;
    private ImageButton more1;
    private ImageButton more2;
    private TextView more3;
    private TextView acti1;
    private TextView acti2;
    private TextView intro;
    private TextView leader_name;
    private TextView leader_phone;
    private TextView leader_email;
    private ImageView logo;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_detail);
        SharedPreferences pref = getSharedPreferences("data2",MODE_PRIVATE);
        str2 = pref.getString("back_activity2","MainActivity");
        text=(TextView)findViewById(R.id.detail_name);
        acti1=(TextView)findViewById(R.id.detail_act1);
        acti2=(TextView)findViewById(R.id.detail_act2);
        intro=(TextView)findViewById(R.id.detail_intro);
        logo=(ImageView)findViewById(R.id.detail_pic);
        leader_name=(TextView)findViewById(R.id.ass_leader_name);
        leader_phone=(TextView)findViewById(R.id.ass_leader_phone);
        leader_email=(TextView)findViewById(R.id.ass_leader_email);
        back=(ImageButton)findViewById(R.id.back_ass_all);
        more1=(ImageButton)findViewById(R.id.detail_more1);
        more2=(ImageButton)findViewById(R.id.detail_more2);
        more3=(TextView) findViewById(R.id.detail_more3);
        more3.setClickable(true);
        more1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acti1.getText().toString().equals("暂无")){

                }else{


                    Intent intent=new Intent(AssDetail.this,ActiDetail.class);
                    SharedPreferences.Editor editor1 = getSharedPreferences("data1", MODE_PRIVATE).edit();
                    editor1. putString("back_activity1", "AssDetail");
                    editor1.apply();
                    startActivity(intent);
                    finish();
                }
            }
        });
        more2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acti2.getText().toString().equals("暂无")){

                }else{
                    Intent intent=new Intent(AssDetail.this,ActiDetail.class);
                    SharedPreferences.Editor editor1 =getSharedPreferences("data1", MODE_PRIVATE).edit();
                    editor1. putString("back_activity1", "AssDetail");
                    editor1.apply();
                    startActivity(intent);
                    finish();
                }
            }
        });
        intent=getIntent();
        id=intent.getStringExtra("ass_id3");
        new AssDetail_JSON(handler,id,text,logo,intro,acti1,acti2,leader_name,leader_phone,leader_email,AssDetail.this).start();
        more3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data4", MODE_PRIVATE).edit();
                editor. putString("back_activity4", "AssDetail");
                editor.apply();
                Intent intent=new Intent(AssDetail.this,PreviousActiActivity.class);
                intent.putExtra("ass_id4",id);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(str2){
                    case "AllAssActivity":
                        Intent intent1=new Intent(AssDetail.this,AllAssActivity.class);
                        startActivity(intent1);
                        finish();
                        break;
                    case "MainActivity":
                        Intent intent2=new Intent(AssDetail.this, MainActivity.class);
                        startActivity(intent2);
                        finish();
                        break;
                    default:
                        Intent intent3=new Intent(AssDetail.this, AllAssActivity.class);
                        startActivity(intent3);
                        finish();
                }
            }
        });
    }
}
