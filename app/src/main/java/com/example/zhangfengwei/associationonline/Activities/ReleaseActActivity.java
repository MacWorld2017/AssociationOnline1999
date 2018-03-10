package com.example.zhangfengwei.associationonline.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.ActiDetail;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.AssDetail;
import com.example.zhangfengwei.associationonline.R;

import java.io.FileNotFoundException;

import Tools.MyImageView;

public class ReleaseActActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView selectImage;
    private TextView edit;
    private MyImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release_act);
        back=(ImageButton)findViewById(R.id.back_from_release_act);
        edit=(TextView)findViewById(R.id.edit_information1);
        selectImage=(TextView)findViewById(R.id.add_image1);
        imageView=(MyImageView)findViewById(R.id.acti_detail_pic);
        edit.setClickable(true);
        selectImage.setClickable(true);
        selectImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReleaseActActivity.this,EditActivity.class);
                SharedPreferences.Editor editor1 = getSharedPreferences("data4", MODE_PRIVATE).edit();
                editor1.putString("back_activity4", "ReleaseActActivity");
                editor1.apply();
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ReleaseActActivity.this,AssActActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        if (resultCode==RESULT_OK){
            Uri uri=data.getData();
            ContentResolver cr=this.getContentResolver();
            try{
                Bitmap bitmap= BitmapFactory.decodeStream(cr.openInputStream(uri));
                //将Bitmap设置到imageView
                imageView.setImageBitmap(bitmap);
            }catch(FileNotFoundException e)
            {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
