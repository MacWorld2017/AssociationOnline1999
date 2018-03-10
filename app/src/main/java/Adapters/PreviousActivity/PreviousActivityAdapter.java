package Adapters.PreviousActivity;

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

public class PreviousActivityAdapter extends ArrayAdapter<PreviousActivity> {
    private int resourceId;
    private TextView actiName;
    private TextView actiId;
    public PreviousActivityAdapter(Context context, int textViewResourceId, List<PreviousActivity> objicts){
        super(context,textViewResourceId,objicts);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        PreviousActivity acti = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        actiName = (TextView)view.findViewById(R.id.previous_acti_name);
        actiId=(TextView)view.findViewById(R.id.previous_acti_id);
        actiName.setText(acti.getName());
        actiId.setText("id:"+acti.getId());
        return view;
    }
}
