package in.arshad.offlinetimetable.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.sql.SQLException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.arshad.offlinetimetable.R;
import in.arshad.offlinetimetable.adpater.TrainNumberAdapter;
import in.arshad.offlinetimetable.global.DbHelper;
import in.arshad.offlinetimetable.global.GlobalFunctions;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;

    @Bind(R.id.btSubmit)
    TextView btSubmit;

    @Bind(R.id.etSearch)
    EditText etSearch;

    private DbHelper db;
    private Context mContext;
    private ProgressDialog dialog;
    private List<String> trainNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        dialog = new ProgressDialog(mContext);

//        setSupportActionBar(toolbar);
//        TextView tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tvToolbarTitle);
//        tvToolbarTitle.setText("Search Train");

        db = new DbHelper(this);
        try {
            db.openDataBase();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        btSubmit.setOnClickListener(this);
    }

    private void fetchTrains(String input){
        try {
            trainNumbers = GlobalFunctions.removeDuplicatesFromList(db.getTrainNumber(input));
            if (trainNumbers == null || trainNumbers.size() == 0) {
                GlobalFunctions.toastShort(mContext, "No data found");
            } else if (trainNumbers.size() == 1) {
                String nameNumber = trainNumbers.get(0);
                String number = nameNumber.substring(nameNumber.indexOf('(')+1, nameNumber.indexOf(')'));
                startScheduleActivity(number);
            } else {
                showTrainPopup(trainNumbers);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startScheduleActivity(String number){
        GlobalFunctions.stopProgressDialog(dialog);
        Intent intent = new Intent(mContext, ScheduleActivity.class);
        intent.putExtra("trainNumber", number);
        startActivity(intent);
    }

    private void showTrainPopup(final List<String> trainNumbers){
        GlobalFunctions.stopProgressDialog(dialog);
        final Dialog popupDialog = new Dialog(mContext);
        popupDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        popupDialog.setContentView(R.layout.popup_train_number);
        popupDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        ListView lv = (ListView) popupDialog.findViewById(R.id.lvList);
        TrainNumberAdapter adapter = new TrainNumberAdapter(mContext, trainNumbers);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    String nameNumber = trainNumbers.get(i);
                    String number = nameNumber.substring(nameNumber.indexOf('(')+1, nameNumber.indexOf(')'));
                    Log.e(TAG, number);
                    startScheduleActivity(number);
                    popupDialog.hide();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        try{
            popupDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        popupDialog.setCancelable(true);
        popupDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btSubmit:
                GlobalFunctions.startProgressDialog(dialog, "Please wait...");
                String input = etSearch.getText().toString();
                if(input.isEmpty()) {
                    GlobalFunctions.toastShort(mContext, "Enter train number / name");
                } else {
                    fetchTrains(input);
                }
                break;
        }
    }
}
