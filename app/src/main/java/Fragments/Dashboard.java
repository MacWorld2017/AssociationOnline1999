package Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.ActiRecent;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.AllAssActivity;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.JoinAssActivity;
import com.example.zhangfengwei.associationonline.Activities.Dash.AssAll.PreviousActiActivity;
import com.example.zhangfengwei.associationonline.R;

import java.util.ArrayList;
import java.util.List;

import Adapters.DashBoard.Dash;
import Adapters.DashBoard.DashAdapter;

import static android.content.Context.MODE_PRIVATE;

public class Dashboard extends Fragment {
    private List<Dash> dashList=new ArrayList<>();
    private TextView name;
    private String str;
    private ListView list;
    private DashAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        adapter = new DashAdapter(getActivity(), R.layout.dash_item, dashList);
        list = (ListView) view.findViewById(R.id.dashboard);
        list.setAdapter(adapter);
        initDash();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                name=(TextView)view.findViewById(R.id.dash_text);
                str=name.getText().toString();
                Intent intent=new Intent();
                switch(str){
                    case "社团汇总":
                        intent.setClass(getActivity(), AllAssActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    case "最近活动":
                        intent.setClass(getActivity(), ActiRecent.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    case "往期活动":
                        SharedPreferences.Editor editor = getActivity().getSharedPreferences("data4", MODE_PRIVATE).edit();
                        editor. putString("back_activity4", "MainActivity");
                        editor.apply();
                        intent.setClass(getActivity(), PreviousActiActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    case "加入社团":
                        intent.setClass(getActivity(), JoinAssActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;

                }
            }
        });
        return view;
    }
    private void initDash() {
        Dash apple1 = new Dash("社团汇总",R.drawable.icon_all_ass);
        dashList.add(apple1);
        Dash apple2 = new Dash("最近活动",R.drawable.icon_rec_acti);
        dashList.add(apple2);
        Dash apple3 = new Dash("加入社团",R.drawable.icon_join_ass);
        dashList.add(apple3);
        Dash apple4 = new Dash("创建社团",R.drawable.icon_create_ass);
        dashList.add(apple4);
        Dash apple5 = new Dash("往期活动",R.drawable.icon_ever_acti);
        dashList.add(apple5);
    }
}
