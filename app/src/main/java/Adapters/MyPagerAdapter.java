package Adapters;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Yanyunfeng on 2016/11/30.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<String> mTitleList;//标题列表
    private List<View> mList;//视图列表

    public MyPagerAdapter(List<String> titleList,List<View> viewList) {
        this.mTitleList=titleList;
        this.mList =viewList;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mList.get(position));
        return mList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mList.get(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
}
