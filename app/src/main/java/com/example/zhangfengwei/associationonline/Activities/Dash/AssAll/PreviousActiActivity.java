package com.example.zhangfengwei.associationonline.Activities.Dash.AssAll;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zhangfengwei.associationonline.Activities.MainActivity;
import com.example.zhangfengwei.associationonline.Activities.SearchActivity;
import com.example.zhangfengwei.associationonline.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapters.PreviousActivity.PreviousActivity;
import Adapters.PreviousActivity.PreviousActivityAdapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;


public class PreviousActiActivity extends AppCompatActivity{
	private List<PreviousActivity> list = new ArrayList<>();
	private ListView listView;
	private PullToRefreshView mPullToRefreshView;

	private PreviousActivityAdapter  adapter;
	private ArrayAdapter adapter2;
	private Spinner spinner1;
	private String[] mItems2;
	private ImageButton back;
	private ImageButton srch;
	private Intent mainIntent=new Intent();
	private String back_str;
	private TextView name;
	private String acti_name;
	private SharedPreferences pref;
	private Long ass_id;
	private int mYear, mMonth, mDay;
	private TextView dateDisplay;
	final int DATE_DIALOG = 1;
	private int position1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_previous_acti);

		dateDisplay=(TextView)findViewById(R.id.dateDisplay2);
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

		back=(ImageButton)findViewById(R.id.back_ass_detail2);
		mainIntent=getIntent();
		ass_id=Long.parseLong(mainIntent.getStringExtra("ass_id4"));
		pref = getSharedPreferences("data4",MODE_PRIVATE);
		back_str=pref.getString("back_activity4","MainActivity");
		back=(ImageButton)findViewById(R.id.back_ass_detail2);
		srch=(ImageButton)findViewById(R.id.search_acti_pre);
		back.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(back_str.equals("MainActivity")){
					Intent intent=new Intent(PreviousActiActivity.this, MainActivity.class);
					intent.putExtra("cmd","Dashboard");
					startActivity(intent);
					finish();
				}
				else if(back_str.equals("AssDetail")){
					Intent intent=new Intent(PreviousActiActivity.this, AssDetail.class);
					startActivity(intent);
					finish();
				}
			}
		});
		srch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(PreviousActiActivity.this,SearchActivity.class);
				intent.putExtra("back_pos","PreviousActiActivity");
				startActivity(intent);
				finish();
			}
		});
		spinner1 = (Spinner) findViewById(R.id.acti_previous_spinner);
		mItems2 = getResources().getStringArray(R.array.location);
// 建立Adapter并且绑定数据源
		adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, mItems2);
		//设置下拉之前的样式
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		//下拉后的样式
//绑定 Adapter到控件
		spinner1.setAdapter(adapter2);
		spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,int pos, long id) {

				String[] location = getResources().getStringArray(R.array.location);
				position1=pos;
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// Another interface callback
			}
		});

		listView = (ListView) findViewById(R.id.acti_pre_list);
//		adapter = new PreviousActivityAdapter(PreviousActiActivity.this,R.layout.list_previous_acti,list);
//		listView.setAdapter(adapter);
//		setData();
		mPullToRefreshView=(PullToRefreshView)findViewById(R.id.pull_to_refresh1);
		mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
			@Override
			public void onRefresh() {
				mPullToRefreshView.postDelayed(new Runnable() {
					@Override
					public void run() {
						listView.setAdapter(adapter);
//						setData();
						//1秒后将下拉刷新的状态变为刷新完成
						mPullToRefreshView.setRefreshing(false);
					}
				}, 1000);
			}
		});

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				name=(TextView)view.findViewById(R.id.previous_acti_name);
				acti_name=name.getText().toString();
				Intent intent=new Intent();
				intent.setClass(PreviousActiActivity.this, ActiDetail.class);
				SharedPreferences.Editor editor1 =getSharedPreferences("data1", MODE_PRIVATE).edit();
				editor1. putString("back_activity1", "PreviousActiActivity");
				editor1.apply();
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

//	private List<PreviousActivity> setData() {
//		//模拟添加数据
//		List<PreviousActivity> listT = new ArrayList<>();
//		list.clear();
//		for (int i = 0; i < 2; i++) {
//			PreviousActivity apple1 = new PreviousActivity("包饺子");
//			list.add(apple1);
//			PreviousActivity apple2 = new PreviousActivity("百团大战");
//			list.add(apple2);
//		}
//		return listT;
//	}
}
