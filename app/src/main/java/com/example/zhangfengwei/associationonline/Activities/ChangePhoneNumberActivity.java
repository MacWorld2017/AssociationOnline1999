package com.example.zhangfengwei.associationonline.Activities;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.zhangfengwei.associationonline.R;

public class ChangePhoneNumberActivity extends AppCompatActivity {
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_phone_number);
        back=(ImageButton)findViewById(R.id.back_from_change_phone_number);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChangePhoneNumberActivity.this,ControlActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
