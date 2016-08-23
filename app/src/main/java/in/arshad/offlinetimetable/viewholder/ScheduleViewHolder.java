package in.arshad.offlinetimetable.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.arshad.offlinetimetable.R;

/**
 * Created by Arshad on 08-03-2016.
 */
public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.tvStationName)
    public TextView tvStationName;

    @Bind(R.id.tvArrivalTime)
    public TextView tvArrivalTime;

    @Bind(R.id.tvDepTime)
    public TextView tvDepTime;

    public ScheduleViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }

}
