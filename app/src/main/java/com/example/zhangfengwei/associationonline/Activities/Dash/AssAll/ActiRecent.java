package com.example.zhangfengwei.associationonline.Activities.Dash.AssAll;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhangfengwei.associationonline.Activities.MainActivity;
import com.example.zhangfengwei.associationonline.Activities.SearchActivity;
import com.example.zhangfengwei.associationonline.R;
import com.yalantis.phoenix.PullToRefreshView;

import Adapters.DashBoard.ActiRecent.ActiRecent2;
import Adapters.DashBoard.ActiRecent.ActiRecentAdapter;

public class ActiRecent extends AppCompatActivity {
    private ViewPager mViewPaper;
    private Spinner spinner1;
    private List<ImageView> images;
    private String[] mItems2;
    private ArrayAdapter<String> adapter2;
    private ActiRecentAdapter adapter3;
    private ListView list;
    private TextView name;
    private String str;
    private List<ActiRecent2> actiList=new ArrayList<>();
    private List<View> dots;
    private int currentItem;
    private ImageButton back;
    private ImageButton srch;
    private PullToRefreshView mPullToRefreshView;
    private int mYear, mMonth, mDay;
    private TextView dateDisplay;
    final int DATE_DIALOG = 1;

    //记录上一次点的位置
//    private int oldPosition = 0;
//    //存放图片的id
//    private int[] imageIds = new int[]{
//            R.drawable.a,
//            R.drawable.b,
//            R.drawable.c,
//            R.drawable.d,
//            R.drawable.e
//    };
//    //存放图片的标题
//    private String[]  titles = new String[]{
//            "巩俐不低俗，我就不能低俗",
//            "扑树又回来啦！再唱经典老歌引万人大合唱",
//            "揭秘北京电影如何升级",
//            "乐视网TV版大派送",
//            "热血屌丝的反杀"
//    };
//    private TextView title;
//    private ViewPagerAdapter adapter;
//    private ScheduledExecutorService scheduledExecutorService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acti_recent);
        srch=(ImageButton)findViewById(R.id.search_acti_rec);
        mPullToRefreshView=(PullToRefreshView)findViewById(R.id.acti_recent_refresh);
//        mViewPaper = (ViewPager) findViewById(R.id.vp2);
        back=(ImageButton)findViewById(R.id.back_dashboard2);
        dateDisplay=(TextView)findViewById(R.id.dateDisplay);
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

        //显示的图片
//        images = new ArrayList<ImageView>();
//        for(int i = 0; i < imageIds.length; i++){
//            ImageView imageView = new ImageView(this);
//            imageView.setBackgroundResource(imageIds[i]);
//            images.add(imageView);
//        }
        //显示的小点
//        dots = new ArrayList<View>();
//        dots.add(findViewById(R.id.dot_00));
//        dots.add(findViewById(R.id.dot_11));
//        dots.add(findViewById(R.id.dot_22));
//        dots.add(findViewById(R.id.dot_33));
//        dots.add(findViewById(R.id.dot_44));

//        title = (TextView) findViewById(R.id.title2);
//        title.setText(titles[0]);
//
//        adapter = new ViewPagerAdapter();
//        mViewPaper.setAdapter(adapter);
//
//        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//
//            @Override
//            public void onPageSelected(int position) {
//                title.setText(titles[position]);
//                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
//                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
//
//                oldPosition = position;
//                currentItem = position;
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//
//            }
//        });
        spinner1 = (Spinner) findViewById(R.id.acti_spinner);
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
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] location = getResources().getStringArray(R.array.location);
                String str2=location[pos];
                Toast.makeText(ActiRecent.this, "你选择的校区是"+str2, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ActiRecent.this, MainActivity.class);
                mainIntent.putExtra("cmd","Dashboard");
                startActivity(mainIntent);
                finish();
            }
        });
        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(ActiRecent.this, SearchActivity.class);
                mainIntent.putExtra("back_pos","ActiRecent");
                startActivity(mainIntent);
                finish();
            }
        });
        adapter3 = new ActiRecentAdapter(ActiRecent.this,R.layout.list_acti_recent, actiList);
        list = (ListView)findViewById(R.id.acti_recent_list);
        list.setAdapter(adapter3);
        initActi();
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.setAdapter(adapter3);
                        initActi();
                        //1秒后将下拉刷新的状态变为刷新完成
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 1000);
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name=(TextView)view.findViewById(R.id.acti_recent_name);
                str=name.getText().toString();
                Intent intent=new Intent();
                intent.setClass(ActiRecent.this, ActiDetail.class);
                intent.putExtra("acti_name",str);
                SharedPreferences.Editor editor1 = getSharedPreferences("data1", MODE_PRIVATE).edit();
                editor1. putString("back_activity1", "ActiRecent");
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
    /**
     * 自定义Adapter
     * @author liuyazhuang
     *
     */
    private class ViewPagerAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
//			super.destroyItem(container, position, object);
//			view.removeView(view.getChildAt(position));
//			view.removeViewAt(position);
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 利用线程池定时执行动画轮播
     */
//    @Override
//    protected void onStart() {
//        // TODO Auto-generated method stub
//        super.onStart();
//        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutorService.scheduleWithFixedDelay(
//                new ViewPageTask(),
//                2,
//                2,
//                TimeUnit.SECONDS);
//    }


//    private class ViewPageTask implements Runnable{
//
//        @Override
//        public void run() {
//            currentItem = (currentItem + 1) % imageIds.length;
//            mHandler.sendEmptyMessage(0);
//        }
//    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
    private void initActi() {
        actiList.clear();
        ActiRecent2 apple1 = new ActiRecent2("活动一","aaaaaaaaaaaaaabbbbbbb");
        actiList.add(apple1);
        ActiRecent2 apple2 = new ActiRecent2("活动二二","bbbbbbbbbbbbbbbbbbbbb");
        actiList.add(apple2);
        ActiRecent2 apple3 = new ActiRecent2("活动三三三","bbbbbbbbbbbbbbbbbbbbbb");
        actiList.add(apple3);
        ActiRecent2 apple4 = new ActiRecent2("活动四四四四","bbbbbbbbbbbbbbbbbbbb");
        actiList.add(apple4);
        ActiRecent2 apple5 = new ActiRecent2("活动五五五五五","bbbbbbbbbbbbbbbbbbb");
        actiList.add(apple5);
        ActiRecent2 apple6 = new ActiRecent2("活动六六六六六六","bbbbbbbbbbbbbbbbbbb");
        actiList.add(apple6);
        ActiRecent2 apple7 = new ActiRecent2("活动七七七七七七七","bbbbbbbbbbbbbbbbbbb");
        actiList.add(apple7);
    }
    }
