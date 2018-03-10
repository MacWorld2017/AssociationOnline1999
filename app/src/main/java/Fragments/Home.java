package Fragments;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.ActiDetail;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.AssDetail;
import com.example.zhangfengwei.associationonline.R;
import com.yalantis.phoenix.PullToRefreshView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import Adapters.HotActivities.Activities;
import Adapters.HotActivities.HotActivities;
import Adapters.HotAssociations.Associations;
import Adapters.HotAssociations.HotAssociations;
import Adapters.MyPagerAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Home extends Fragment{
    private PullToRefreshView mPullToRefreshView1;
    private PullToRefreshView mPullToRefreshView2;
    private ViewPager mViewPaper;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LayoutInflater mInflater;
    private List<String> mTitleLlist=new ArrayList<String>();//标题集合
    private View view1,view2;//试图
    private List<View> mViewList=new ArrayList<View>();
    private MyPagerAdapter mAdapter;
    private Fragment[] mFragmentArrays = new Fragment[2];
    private String[] mTabTitles = new String[2];
    private List<ImageView> images;
    private List<View> dots;
    private HotActivities adapter2;
    private HotAssociations adapter1;
    private ListView list1;
    private ListView list2;
    private int currentItem;
    private View view;
    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    //存放图片的标题
    private String[]  titles = new String[]{
            "巩俐不低俗，我就不能低俗",
            "扑树又回来啦！再唱经典老歌引万人大合唱",
            "揭秘北京电影如何升级",
            "乐视网TV版大派送",
            "热血屌丝的反杀"
    };
    private TextView title;
    private TextView ass_name;
    private TextView acti_name;
    private String str_ass;
    private String str_acti;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;
    private List<Associations> assList=new ArrayList<>();
    private List<Activities> actiList=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home,container,false);
        mTabLayout=(TabLayout)view.findViewById(R.id.tabs);
        mViewPager=(ViewPager)view.findViewById(R.id.vp_view);
        mInflater=LayoutInflater.from(getContext());
        view1=mInflater.inflate(R.layout.view1,null);
        view2=mInflater.inflate(R.layout.view2,null);
        //添加叶卡视图
        mViewList.add(view1);
        mViewList.add(view2);

        //选项
        mTitleLlist.add(" 热门社团 ");
        mTitleLlist.add(" 热门活动 ");

        //设置tab模式
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleLlist.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(mTitleLlist.get(1)));

        //tab与视图关联
        mAdapter=new MyPagerAdapter(mTitleLlist,mViewList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);;
        mViewPaper = (ViewPager) view.findViewById(R.id.vp);
        mPullToRefreshView1=(PullToRefreshView)view1.findViewById(R.id.home_refresh1);
        mPullToRefreshView2=(PullToRefreshView)view2.findViewById(R.id.home_refresh2);
        adapter1 = new HotAssociations(getActivity(), R.layout.list_hot_associations, assList);
        list1=(ListView)view1.findViewById(R.id.hot_associations);
        list1.setAdapter(adapter1);
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view1, int position, long id) {
                ass_name=(TextView)view1.findViewById(R.id.ass_name1);
                str_ass=ass_name.getText().toString();
                Intent intent1=new Intent(getActivity(), AssDetail.class);
                intent1.putExtra("ass_name3",str_ass);
                SharedPreferences.Editor editor2 = getActivity().getSharedPreferences("data2", MODE_PRIVATE).edit();
                editor2. putString("back_activity2", "MainActivity");
                editor2.apply();
                startActivity(intent1);
                getActivity().finish();
            }
        });

        adapter2 = new HotActivities(getActivity(), R.layout.list_hot_activities, actiList);
        list2=(ListView)view2.findViewById(R.id.hot_activities);
        list2.setAdapter(adapter2);
        list2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view2, int position, long id) {
                acti_name=(TextView)view2.findViewById(R.id.ass_name2);
                str_acti=acti_name.getText().toString();
                Intent intent2=new Intent(getActivity(), ActiDetail.class);
                SharedPreferences.Editor editor1 = getActivity().getSharedPreferences("data1", MODE_PRIVATE).edit();
                editor1. putString("back_activity1", "MainActivity");
                editor1.apply();
                startActivity(intent2);
                getActivity().finish();
            }
        });

        initAssociations();
        initActivities();

        mPullToRefreshView1.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list1.setAdapter(adapter1);
                        initAssociations();
                        //1秒后将下拉刷新的状态变为刷新完成
                        mPullToRefreshView1.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        mPullToRefreshView2.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list2.setAdapter(adapter2);
                        initActivities();
                        //1秒后将下拉刷新的状态变为刷新完成
                        mPullToRefreshView2.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        //显示的图片
        images = new ArrayList<ImageView>();
        for(int i = 0; i < imageIds.length; i++){
            ImageView imageView = new ImageView(this.getContext());
            imageView.setBackgroundResource(imageIds[i]);
            images.add(imageView);
        }
        //显示的小点
        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.dot_0));
        dots.add(view.findViewById(R.id.dot_1));
        dots.add(view.findViewById(R.id.dot_2));
        dots.add(view.findViewById(R.id.dot_3));
        dots.add(view.findViewById(R.id.dot_4));

        title = (TextView) view.findViewById(R.id.title1);
        title.setText(titles[0]);

        adapter = new ViewPagerAdapter();
        mViewPaper.setAdapter(adapter);

        mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


            @Override
            public void onPageSelected(int position) {
                title.setText(titles[position]);
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                oldPosition = position;
                currentItem = position;
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
        return view;
    }

    private void initActivities() {
        actiList.clear();
        Activities apple1 = new Activities("茶社","喝茶",R.drawable.ass_demo);
        actiList.add(apple1);
        Activities apple2 = new Activities("学生会","纳新",R.drawable.ass_demo);
        actiList.add(apple2);
        Activities apple3 = new Activities("学生在线","宣讲",R.drawable.ass_demo);
        actiList.add(apple3);
        Activities apple4 = new Activities("电影社","看大片",R.drawable.ass_demo);
        actiList.add(apple4);
        Activities apple5 = new Activities("IBM","竞赛",R.drawable.ass_demo);
        actiList.add(apple5);
    }

    private void initAssociations(){
        assList.clear();
        Associations apple = new Associations("茶社","喝茶",R.drawable.ass_demo);
        assList.add(apple);
        Associations banana = new Associations("电脑社","玩电脑",R.drawable.ass_demo);
        assList.add(banana);
        Associations orange = new Associations("学生在线","学线",R.drawable.ass_demo);
        assList.add(orange);
        Associations watermelon = new Associations("2017学线web组试用期","17-张鑫：怕是要跪地求饶了",R.drawable.ass_demo);
        assList.add(watermelon);
        Associations pear = new Associations("学线软件分站2016","后端-田这字啥玩意：几点的会",R.drawable.ass_demo);
        assList.add(pear);
        Associations grape = new Associations("软件村2017新生群","17 山东 某某：好的谢谢",R.drawable.ass_demo);
        assList.add(grape);
        Associations pineapple = new Associations("群助手","2个群有新消息",R.drawable.ass_demo);
        assList.add(pineapple);
        Associations strawberry = new Associations("董浩","我想想",R.drawable.ass_demo);
        assList.add(strawberry);
    }

    /**
     * 自定义Adapter
     * @author liuyazhuang
     *
     */
    private class ViewPagerAdapter extends PagerAdapter {

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

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getActivity().getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 利用线程池定时执行动画轮播
     */
    @Override
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

    }


    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 接收子线程传递过来的数据
     */
    private Handler mHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
    }
    private void findview() {
    }

    private List<String> getDate() {
        List<String> listT = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            listT.add("我是测试数据->"+i);
        }
        return listT;
    }


    final class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentArrays[position];
        }


        @Override
        public int getCount() {
            return mFragmentArrays.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabTitles[position];

        }
    }
}
