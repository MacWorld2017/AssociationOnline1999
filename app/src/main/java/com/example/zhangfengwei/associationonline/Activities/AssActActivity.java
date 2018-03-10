package com.example.zhangfengwei.associationonline.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

public class AssActActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ass_act);
        back=(ImageButton)findViewById(R.id.back_to_control);
        add=(TextView)findViewById(R.id.ass_add_act);
        add.setClickable(true);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AssActActivity.this,ReleaseActActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AssActActivity.this,ControlActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
