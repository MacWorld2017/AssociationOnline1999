package com.example.zhangfengwei.associationonline.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.ActiRecent;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.AllAssActivity;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.AssDetail;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.JoinAssActivity;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.PreviousActiActivity;
import com.example.zhangfengwei.associationonline.Activities.HttpJson.Search_JSON;
import com.example.zhangfengwei.associationonline.R;

public class SearchActivity extends AppCompatActivity {
    private ImageButton back_btn;

    private String back_str="MainActivity";
    private int back_str_extra=1;
    private String keyword;
    private ListView list;
    private TextView title;
    private String name;
    private Handler handler=new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent=getIntent();
        back_str=intent.getStringExtra("back_pos");
        back_str_extra=intent.getIntExtra("back_current_pos",1);
        keyword=intent.getStringExtra("keyword_srch");
        back_btn=(ImageButton)findViewById(R.id.back_main);
        list=(ListView)findViewById(R.id.srch_result);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                title=(TextView)view.findViewById(R.id.ass_name3);
                name=title.getText().toString();
                Intent intent=new Intent(SearchActivity.this,AssDetail.class);
                intent.putExtra("ass_name3",name);
                SharedPreferences.Editor editor2 = getSharedPreferences("data2", MODE_PRIVATE).edit();
                editor2. putString("back_activity2", "AllAssActivity");
                editor2.apply();
                startActivity(intent);
                finish();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(back_str.equals("MainActivity")){
                    Intent mainIntent = new Intent(SearchActivity.this,MainActivity.class);
                    switch(back_str_extra){
                        case 1:
                            mainIntent.putExtra("cmd","Home");
                            startActivity(mainIntent);
                            SearchActivity.this.finish();
                            break;
                        case 2:
                            mainIntent.putExtra("cmd","Dashboard");
                            startActivity(mainIntent);
                            SearchActivity.this.finish();
                            break;
                        case 3:
                            mainIntent.putExtra("cmd","Notifications");
                            startActivity(mainIntent);
                            SearchActivity.this.finish();
                            break;
                    }

                }
                else if(back_str.equals("AllAssActivity")){
                    Intent mainIntent = new Intent(SearchActivity.this,AllAssActivity.class);
                    startActivity(mainIntent);
                    SearchActivity.this.finish();
                }
                else if(back_str.equals("ActiRecent")){
                    Intent mainIntent = new Intent(SearchActivity.this,ActiRecent.class);
                    startActivity(mainIntent);
                    SearchActivity.this.finish();
                }
                else if(back_str.equals("PreviousActiActivity")){
                    Intent mainIntent = new Intent(SearchActivity.this,PreviousActiActivity.class);
                    startActivity(mainIntent);
                    SearchActivity.this.finish();
                }
                else if(back_str.equals("JoinAssActivity")){
                    Intent mainIntent = new Intent(SearchActivity.this,JoinAssActivity.class);
                    startActivity(mainIntent);
                    SearchActivity.this.finish();
                }
            }
        });
        if(back_str.equals("AllAssActivity")){
            new Search_JSON(handler,list,SearchActivity.this,"ass",keyword).start();
        }

    }
}
