package Adapters.JoinAss;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zhangfengwei.associationonline.R;

import java.util.List;

/**
 * Created by zhangfengwei on 2017/12/25.
 */

public class JoinAssAdapter extends ArrayAdapter<JoinAss> {
        private int resourceId;
        private TextView name;
        private TextView time;
        private TextView place;
        private TextView join;
        private Context mContext;
        private LayoutInflater mInflater;
        private List<JoinAss> mDatas;
        private View.OnClickListener onClickListener;
        public JoinAssAdapter(Context context, int textViewResourceId, List<JoinAss> objicts){
            super(context,textViewResourceId,objicts);
            mContext = context;
            mInflater = LayoutInflater.from(mContext);
            mDatas = objicts;
            resourceId = textViewResourceId;
            this.onClickListener = onClickListener;
    }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            JoinAss inform = getItem(position);
            View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            place = (TextView) view.findViewById(R.id.place_join_ass);
            name = (TextView)view.findViewById(R.id.join_ass_name);
            time=(TextView)view.findViewById(R.id.time_join_ass);
            join=(TextView)view.findViewById(R.id.join_join_ass);
            join.setClickable(true);
            name.setText(inform.getName_join());
            time.setText(inform.getTime_join());
            place.setText(inform.getPlace_join());
            return view;
        }
    @Override
    public int getCount() {
        return (mDatas != null ? mDatas.size() : 0);
    }
    @Override
    public JoinAss getItem(int position) {
        return (mDatas != null ? mDatas.get(position) : null);
    }
    public long getItemId(int position) {
        return position;
    }
}
