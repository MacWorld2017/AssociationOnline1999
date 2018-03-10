package com.example.zhangfengwei.associationonline.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangfengwei.associationonline.R;

import Fragments.Notifications;

public class ControlActivity extends AppCompatActivity {
    private String data_psd;
    private TextView exit;
    private ImageButton back;
    private TextView edit_intro;
    private TextView release_act;
    private TextView release_inform;
    private TextView change_psd;
    private TextView change_phone_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        exit = (TextView) findViewById(R.id.exit_login);
        edit_intro = (TextView) findViewById(R.id.edit_intro);
        release_act = (TextView) findViewById(R.id.release_act);
        back = (ImageButton) findViewById(R.id.back_main3);
        release_inform = (TextView) findViewById(R.id.release_inform);
        change_psd = (TextView) findViewById(R.id.change_psd);
        change_phone_number = (TextView) findViewById(R.id.change_phone_number);
        edit_intro.setClickable(true);
        release_inform.setClickable(true);
        release_act.setClickable(true);
        change_psd.setClickable(true);
        change_phone_number.setClickable(true);
        exit.setClickable(true);
        edit_intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, EditIntroActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, MainActivity.class);
                intent.putExtra("cmd","Notifications");
                startActivity(intent);
                finish();
            }
        });
        release_act.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, AssActActivity.class);
                startActivity(intent);
                finish();
            }
        });
        release_inform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, ReleaseInformActivity.class);
                startActivity(intent);
                finish();
            }
        });
        change_psd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, ChangePsdActivity.class);
                startActivity(intent);
                finish();
            }
        });
        change_phone_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ControlActivity.this, ChangePhoneNumberActivity.class);
                startActivity(intent);
                finish();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ControlActivity.this,MainActivity.class);
                Toast.makeText(ControlActivity.this,"请稍候......",Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getSharedPreferences("login", MODE_PRIVATE).edit();
                editor. putInt("login_state", 0);
                editor.apply();
                Toast.makeText(ControlActivity.this,"已退出登录",Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
    }
}
