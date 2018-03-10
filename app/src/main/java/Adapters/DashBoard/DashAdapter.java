package Adapters.DashBoard;

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
 * Created by zhangfengwei on 2017/12/15.
 */

public class DashAdapter extends ArrayAdapter<Dash> {
    private int resourceId;
    public DashAdapter(Context context, int textViewResourceId, List<Dash> objicts){
        super(context,textViewResourceId,objicts);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Dash acti = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView assImage = (ImageView)view.findViewById(R.id.dash_pic);
        TextView assName = (TextView)view.findViewById(R.id.dash_text);
        assImage.setImageResource(acti.getPic());
        assName.setText(acti.getText());
        return view;
    }
}
