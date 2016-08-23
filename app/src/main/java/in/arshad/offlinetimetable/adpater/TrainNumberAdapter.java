package in.arshad.offlinetimetable.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.arshad.offlinetimetable.R;
import in.arshad.offlinetimetable.global.GlobalFunctions;

/**
 * Created by root on 18/8/16.
 */
public class TrainNumberAdapter extends BaseAdapter {

    List<String> trainNumbers;
    Context mContext;

    public TrainNumberAdapter(Context mContext, List<String> trainNumbers){
        this.mContext = mContext;
        this.trainNumbers = trainNumbers;
    }

    @Override
    public int getCount() {
        return trainNumbers.size();
    }

    @Override
    public Object getItem(int i) {
        return trainNumbers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public class ViewHolder{
        @Bind(R.id.tvText)
        TextView tvText;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder;
        if(convertView == null) {
            convertView = inflater.inflate(R.layout.single_row_train_number, null);
            holder = new ViewHolder(convertView);
            holder.tvText = (TextView) convertView.findViewById(R.id.tvText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvText.setText(trainNumbers.get(i));

        return convertView;
    }
}
