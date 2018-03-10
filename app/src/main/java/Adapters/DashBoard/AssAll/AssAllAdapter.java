package Adapters.DashBoard.AssAll;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

import Tools.HttpImage;

/**
 * Created by zhangfengwei on 2017/12/15.
 */
public class AssAllAdapter extends ArrayAdapter<AssAll> {
    private int resourceId;
    private android.os.Handler handler=new android.os.Handler();
    public AssAllAdapter(Context context, int textViewResourceId, List<AssAll> objicts){
        super(context,textViewResourceId,objicts);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        AssAll acti = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        ImageView assImage3 = (ImageView)view.findViewById(R.id.ass_pic3);
        TextView assName3 = (TextView)view.findViewById(R.id.ass_name3);
        TextView assIntro2 = (TextView)view.findViewById(R.id.ass_intro2);
        TextView assid2=(TextView)view.findViewById(R.id.ass_id2);
        new HttpImage(acti.getPic3(),handler,assImage3).start();
        assName3.setText(acti.getName2());
        assIntro2.setText(acti.getIntro2());
        assid2.setText(acti.getId());

        return view;
    }
}
