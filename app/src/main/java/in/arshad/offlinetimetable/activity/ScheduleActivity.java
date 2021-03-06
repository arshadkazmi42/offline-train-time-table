package in.arshad.offlinetimetable.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

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

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;

    @Bind(R.id.rvSchedule)
    RecyclerView rvSchedule;

    @Bind(R.id.tvTrainDetails)
    TextView tvTrainDetails;

    @Bind(R.id.tvSourceDest)
    TextView tvSourceDest;

    @Bind(R.id.adView)
    AdView mAdView;

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

//        setSupportActionBar(toolbar);
//        TextView tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tvToolbarTitle);
//        tvToolbarTitle.setText("Schedule");
        GlobalFunctions.setupAdUnit(mContext, mAdView);

        if(getIntent().hasExtra("trainNumber")) {
            trainNumber = getIntent().getExtras().getString("trainNumber");
        }

        GlobalFunctions.createVerticalRecyclerView(mContext, rvSchedule);

        timeTable = db.getTimeTable(trainNumber);
        adapter = new ScheduleAdapter(mContext, timeTable);
        rvSchedule.setAdapter(adapter);

        if(timeTable.size() > 0) {
            populateProfile(timeTable.get(0));
        } else {
            GlobalFunctions.toastShort(mContext, "Not Found");
        }
    }

    private void populateProfile(TimeTable data){
        String trainNameNumber = data.getTrainName() + " (" + data.getTrainNo() + ")";
        tvTrainDetails.setText(trainNameNumber);

        String sourceStation = GlobalFunctions.trim(data.getSourceStnName()) + " (" + GlobalFunctions.trim(data.getSourceStnCode()) + ")";
        String destStation = GlobalFunctions.trim(data.getDestStnName()) + " (" + GlobalFunctions.trim(data.getDestStnCode()) + ")";
        tvSourceDest.setText(sourceStation + " --> " + destStation );
    }
}
