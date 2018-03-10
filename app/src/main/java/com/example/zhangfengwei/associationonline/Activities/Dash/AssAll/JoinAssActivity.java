package com.example.zhangfengwei.associationonline.Activities.Dash.AssAll;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangfengwei.associationonline.Activities.MainActivity;
import com.example.zhangfengwei.associationonline.Activities.SearchActivity;
import com.example.zhangfengwei.associationonline.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapters.JoinAss.JoinAss;
import Adapters.JoinAss.JoinAssAdapter;

public class JoinAssActivity extends AppCompatActivity {
    private Spinner spinner1;
    private Spinner spinner2;
    private ListView list;
    private List<JoinAss> informList=new ArrayList<>();
    private int mYear, mMonth, mDay;
    private TextView dateDisplay;
    final int DATE_DIALOG = 1;
    private String[] mItems1;
    private String[] mItems2;
    private ArrayAdapter<String> adapter1;
    private ArrayAdapter<String> adapter2;
    private JoinAssAdapter adapter3;
    private ImageButton back;
    private ImageButton srch;
    private PullToRefreshView mPullToRefreshView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_ass);
        mPullToRefreshView=(PullToRefreshView)findViewById(R.id.join_ass_refresh);
        dateDisplay=(TextView)findViewById(R.id.dateDisplay);
        spinner1 = (Spinner) findViewById(R.id.join_ass_spinner1);
        spinner2 = (Spinner) findViewById(R.id.join_ass_spinner2);
        adapter3=new JoinAssAdapter(JoinAssActivity.this, R.layout.list_join_ass, informList);
        list = (ListView)findViewById(R.id.list_join_ass22);
        list.setAdapter(adapter3);
        back=(ImageButton)findViewById(R.id.back_join_ass);
        srch=(ImageButton)findViewById(R.id.search_join_ass);
        dateDisplay.setClickable(true);
        dateDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG);
            }
        });
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        mItems1 = getResources().getStringArray(R.array.location);
        mItems2 = getResources().getStringArray(R.array.type);
// 建立Adapter并且绑定数据源
        adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems1);
        adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems2);
        //设置下拉之前的样式
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //下拉后的样式
//绑定 Adapter到控件
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] location = getResources().getStringArray(R.array.location);
                String str2=location[pos];
                Toast.makeText(JoinAssActivity.this, "你选择的校区是"+str2, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] location = getResources().getStringArray(R.array.type);
                String str2=location[pos];
                Toast.makeText(JoinAssActivity.this, "你选择的类型是"+str2, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        initInform();
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.setAdapter(adapter3);
                        initInform();
                        //1秒后将下拉刷新的状态变为刷新完成
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(JoinAssActivity.this,MainActivity.class);
                intent.putExtra("cmd","Dashboard");
                startActivity(intent);
                finish();
            }
        });
        srch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(JoinAssActivity.this,SearchActivity.class);
                intent.putExtra("back_pos","JoinAssActivity");
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    public void display() {
        dateDisplay.setText(new StringBuffer().append("   "+mYear).append("/").append(mMonth+1).append("/").append(mDay).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };
    private void initInform() {
        informList.clear();
        JoinAss apple1 = new JoinAss("学生在线软件园校区","2018.11.11-2019.1.1","5区108");
        informList.add(apple1);
        JoinAss apple2 = new JoinAss("学生在线软件园校区","2018.11.11-2019.1.1","5区108");
        informList.add(apple2);
        JoinAss apple3 = new JoinAss("学生在线软件园校区","2018.11.11-2019.1.1","5区108");
        informList.add(apple3);
        JoinAss apple4 = new JoinAss("学生在线软件园校区","2018.11.11-2019.1.1","5区108");
        informList.add(apple4);
        JoinAss apple5 = new JoinAss("学生在线软件园校区","2018.11.11-2019.1.1","5区108");
        informList.add(apple5);
    }
}
