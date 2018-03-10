package Adapters.HotActivities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.widget.ArrayAdapter;
import com.example.zhangfengwei.associationonline.R;

import java.util.List;

/**
 * Created by zhangfengwei on 2017/12/1.
 */

public class HotActivities extends ArrayAdapter<Activities> {
    private int resourceId;
    public HotActivities(Context context, int textViewResourceId, List<Activities> objicts){
        super(context,textViewResourceId,objicts);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Activities acti = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView assImage = (ImageView)view.findViewById(R.id.ass_pic2);
        TextView assName = (TextView)view.findViewById(R.id.ass_name2);
        TextView assInform = (TextView)view.findViewById(R.id.ass_inform);
        assImage.setImageResource(acti.getImageId());
        assName.setText(acti.getName());
        assInform.setText(acti.getContent());
        return view;
    }
}
