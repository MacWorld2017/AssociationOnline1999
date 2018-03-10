package com.example.zhangfengwei.associationonline.Activities;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

import java.io.FileNotFoundException;

import Tools.MyImageView;

public class EditIntroActivity extends AppCompatActivity {
    private ImageButton back;
    private TextView save;
    private TextView selectImage;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_intro);
        back=(ImageButton)findViewById(R.id.back_from_edit_intro);
        save=(TextView)findViewById(R.id.finish2);
        selectImage=(TextView)findViewById(R.id.add_image2);
        imageView=(ImageView)findViewById(R.id.ass_detail_pic);
        save.setClickable(true);
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(EditIntroActivity.this,ControlActivity.class);
                startActivity(intent);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

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
