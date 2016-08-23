package in.arshad.offlinetimetable.adpater;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.sql.Time;
import java.util.List;

import in.arshad.offlinetimetable.R;
import in.arshad.offlinetimetable.global.DbHelper;
import in.arshad.offlinetimetable.global.GlobalFunctions;
import in.arshad.offlinetimetable.model.TimeTable;
import in.arshad.offlinetimetable.viewholder.ScheduleViewHolder;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private static final String TAG = ScheduleAdapter.class.getSimpleName();

    private List<TimeTable> timeTable;
    private Context mContext;

    public ScheduleAdapter(Context mContext, List<TimeTable> timeTable) {
        this.mContext = mContext;
        this.timeTable = timeTable;
    }

    @Override
    public int getItemCount() {
        return (null != timeTable ? timeTable.size() : 0);

    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, final int position) {
        final TimeTable data = timeTable.get(position);
        holder.tvStationName.setText(GlobalFunctions.trim(data.getStationName()) + " ("
                + GlobalFunctions.trim(data.getStationCode()) + ")");
        holder.tvArrivalTime.setText(data.getArrivalTime());
        holder.tvDepTime.setText(data.getDepartureTime());
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(viewGroup.getContext());
        ViewGroup mainGroup = (ViewGroup) mInflater.inflate(
                R.layout.single_row_schedule, viewGroup, false);
        return new ScheduleViewHolder(mainGroup);
    }

}
