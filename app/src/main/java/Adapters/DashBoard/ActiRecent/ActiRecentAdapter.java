package Adapters.DashBoard.ActiRecent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

import java.util.List;

/**
 * Created by zhangfengwei on 2017/12/24.
 */

public class ActiRecentAdapter extends ArrayAdapter<ActiRecent2> {
    private int resourceId;
    private ActiRecent2 acti;
    private TextView actiName;
    private TextView assIntro;
    public ActiRecentAdapter(Context context, int textViewResourceId, List<ActiRecent2> objicts) {
        super(context, textViewResourceId, objicts);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        acti = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        actiName = (TextView)view.findViewById(R.id.acti_recent_name);
        assIntro = (TextView)view.findViewById(R.id.acti_recent_intro);
        actiName.setText(acti.getName2());
        assIntro.setText(acti.getIntro2());
        return view;
    }
}
