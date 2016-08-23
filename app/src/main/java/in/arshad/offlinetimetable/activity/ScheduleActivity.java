package in.arshad.offlinetimetable.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.arshad.offlinetimetable.R;
import in.arshad.offlinetimetable.adpater.ScheduleAdapter;
import in.arshad.offlinetimetable.global.DbHelper;
import in.arshad.offlinetimetable.global.GlobalFunctions;
import in.arshad.offlinetimetable.model.TimeTable;

/**
 * Created by root on 18/8/16.
 */
public class ScheduleActivity extends AppCompatActivity {

    private static final String TAG = ScheduleActivity.class.getSimpleName();

    @Bind(R.id.rvSchedule)
    RecyclerView rvSchedule;

    @Bind(R.id.tvTrainDetails)
    TextView tvTrainDetails;

    @Bind(R.id.tvSourceDest)
    TextView tvSourceDest;

    private Context mContext;
    private ProgressDialog dialog;
    private DbHelper db;
    private ScheduleAdapter adapter;
    private List<TimeTable> timeTable = new ArrayList<>();
    private String trainNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        ButterKnife.bind(this);
        mContext = this;
        dialog = new ProgressDialog(mContext);
        db = new DbHelper(this);

        if(getIntent().hasExtra("trainNumber")) {
            trainNumber = getIntent().getExtras().getString("trainNumber");
        }

        GlobalFunctions.createVerticalRecyclerView(mContext, rvSchedule);

        timeTable = db.getTimeTable(trainNumber);
        adapter = new ScheduleAdapter(mContext, timeTable);
        rvSchedule.setAdapter(adapter);

        populateProfile(timeTable.get(0));
    }

    private void populateProfile(TimeTable data){
        String trainNameNumber = data.getTrainName() + " (" + data.getTrainNo() + ")";
        tvTrainDetails.setText(trainNameNumber);

        String sourceStation = GlobalFunctions.trim(data.getSourceStnName()) + " (" + GlobalFunctions.trim(data.getSourceStnCode()) + ")";
        String destStation = GlobalFunctions.trim(data.getDestStnName()) + " (" + GlobalFunctions.trim(data.getDestStnCode()) + ")";
        tvSourceDest.setText(sourceStation + " --> " + destStation );
    }
}
