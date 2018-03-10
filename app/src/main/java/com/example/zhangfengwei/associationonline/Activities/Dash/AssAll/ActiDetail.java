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

import com.example.zhangfengwei.associationonline.Activities.HttpJson.ActiDetail_JSON;
import com.example.zhangfengwei.associationonline.Activities.MainActivity;
import com.example.zhangfengwei.associationonline.R;

import Tools.MyImageView;

public class ActiDetail extends AppCompatActivity {
    private ImageButton back;
    private String acti_name;
    private String back_str;
    private Intent mainIntent=new Intent();
    private SharedPreferences pref;
    private Handler handler=new Handler();
    private ImageView logo;
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
    private TextView ass_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_detail);
        back=(ImageButton)findViewById(R.id.back_ass_detail);
        logo=(ImageView)findViewById(R.id.acti_detail_pic);
        title=(TextView)findViewById(R.id.acti_title);
        start=(TextView)findViewById(R.id.acti_time_start);
        end=(TextView)findViewById(R.id.acti_time_end);
        place=(TextView)findViewById(R.id.acti_place);
        content=(TextView)findViewById(R.id.acti_detail);
        object=(TextView)findViewById(R.id.acti_for);
        sign_way=(TextView)findViewById(R.id.acti_sign_way);
        leader_name=(TextView)findViewById(R.id.acti_leader_name);
        phone=(TextView)findViewById(R.id.acti_leader_phone);
        email=(TextView)findViewById(R.id.acti_leader_email);
        organizer=(TextView)findViewById(R.id.acti_builder);
        other=(TextView)findViewById(R.id.acti_other);
        ass_id=(TextView)findViewById(R.id.acti_ass_id);
        mainIntent=getIntent();
        acti_name=mainIntent.getStringExtra("acti_name");
        new ActiDetail_JSON(ass_id,acti_name,logo,title,start,end,place,content,object,sign_way,leader_name,phone,email,organizer,other,handler).start();
        pref = getSharedPreferences("data1",MODE_PRIVATE);
        back_str = pref. getString("back_activity1","MainActivity");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(back_str){
                    case "AssDetail":
                        Intent intent1=new Intent(ActiDetail.this,AssDetail.class);
                        String ass=ass_id.getText().toString();
                        intent1.putExtra("ass_id3",ass);
                        startActivity(intent1);
                        finish();
                        break;
                    case "ActiRecent":
                        Intent intent2=new Intent(ActiDetail.this,ActiRecent.class);
                        startActivity(intent2);
                        finish();
                        break;
                    case "MainActivity":
                        Intent intent3=new Intent(ActiDetail.this,MainActivity.class);
                        startActivity(intent3);
                        finish();
                        break;
                    case "PreviousActiActivity":
                        Intent intent4=new Intent(ActiDetail.this,PreviousActiActivity.class);
                        startActivity(intent4);
                        finish();
                }
            }
        });
    }
}
