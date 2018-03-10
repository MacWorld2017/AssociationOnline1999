package com.example.zhangfengwei.associationonline.Activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

import Fragments.Dashboard;
import Fragments.Home;
import Fragments.Notifications;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mLinearLayout;
    private ImageButton srch_btn;
    private int back_current_pos=1;
    BottomNavigationView mBnv;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    back_current_pos=1;
                    FragmentManager fm1 = getSupportFragmentManager();
                    FragmentTransaction transaction1 = fm1.beginTransaction();
                    transaction1.replace(R.id.fragment_container, new Home());
                    transaction1.commit();
                    return true;
                case R.id.navigation_dashboard:
                    back_current_pos=2;
                    FragmentManager fm2 = getSupportFragmentManager();
                    FragmentTransaction transaction2 = fm2.beginTransaction();
                    transaction2.replace(R.id.fragment_container, new Dashboard());
                    transaction2.commit();
                    return true;
                case R.id.navigation_notifications:
                    back_current_pos=3;
                    FragmentManager fm3 = getSupportFragmentManager();
                    FragmentTransaction transaction3 = fm3.beginTransaction();
                    transaction3.replace(R.id.fragment_container, new Notifications());
                    transaction3.commit();
                    return true;
            }
            return false;

        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLinearLayout = (LinearLayout) findViewById(R.id.fragment_container);
        mBnv=(BottomNavigationView)findViewById(R.id.navigation);
        srch_btn=(ImageButton)findViewById(R.id.search_btn);
        srch_btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mainIntent = new Intent(MainActivity.this,SearchActivity.class);
                        mainIntent.putExtra("back_pos","MainActivity");
                        mainIntent.putExtra("back_current_pos",back_current_pos);
                        startActivity(mainIntent);
                        finish();
                    }
                });
        int[][] states = new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}};
        int[] colors = new int[]{getResources().getColor(R.color.home_bottom_normal), getResources().getColor(R.color.home_bottom_checked)};
        ColorStateList csl = new ColorStateList(states, colors);
        mBnv.setItemTextColor(csl);
        mBnv.setItemIconTintList(csl);
        Intent intent=getIntent();
        String cmd1=intent.getStringExtra("cmd");
        if(cmd1!=null&&cmd1.equals("Notifications")){
            mBnv.setSelectedItemId(R.id.navigation_notifications);
            FragmentManager fm4 = getSupportFragmentManager();
            FragmentTransaction transaction4 = fm4.beginTransaction();
            transaction4.replace(R.id.fragment_container, new Notifications());
            transaction4.commit();
        }
        else if(cmd1!=null&&cmd1.equals("Dashboard")){
            mBnv.setSelectedItemId(R.id.navigation_dashboard);
            FragmentManager fm4 = getSupportFragmentManager();
            FragmentTransaction transaction4 = fm4.beginTransaction();
            transaction4.replace(R.id.fragment_container, new Dashboard());
            transaction4.commit();
        }
        else if(cmd1!=null&&cmd1.equals("Home")){
            mBnv.setSelectedItemId(R.id.navigation_home);
            FragmentManager fm4 = getSupportFragmentManager();
            FragmentTransaction transaction4 = fm4.beginTransaction();
            transaction4.replace(R.id.fragment_container, new Home());
            transaction4.commit();
        }
        else{
            mBnv.setSelectedItemId(R.id.navigation_home);
            FragmentManager fm4 = getSupportFragmentManager();
            FragmentTransaction transaction4 = fm4.beginTransaction();
            transaction4.replace(R.id.fragment_container, new Home());
            transaction4.commit();
        }
        mBnv = (BottomNavigationView) findViewById(R.id.navigation);
        mBnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
}
