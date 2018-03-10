package Adapters.HotAssociations;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

import java.util.List;

/**
 * Created by zhangfengwei on 2017/12/1.
 * 这是适配器
 */

public class HotAssociations extends ArrayAdapter<Associations> {
    private int resourceId;
    public HotAssociations(Context context, int textViewResourceId, List<Associations> objicts){
        super(context,textViewResourceId,objicts);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Associations ass = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView assImage = (ImageView)view.findViewById(R.id.ass_pic1);
        TextView assName = (TextView)view.findViewById(R.id.ass_name1);
        TextView assIntro = (TextView)view.findViewById(R.id.ass_intro);
        assImage.setImageResource(ass.getImageId());
        assName.setText(ass.getName());
        assIntro.setText(ass.getContent());
        return view;
    }
}