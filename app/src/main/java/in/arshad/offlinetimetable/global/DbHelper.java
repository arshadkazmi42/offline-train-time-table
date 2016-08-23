package in.arshad.offlinetimetable.global;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import in.arshad.offlinetimetable.model.TimeTable;

/**
 * Created by Arshad on 8-8-16.
 */

public class DbHelper extends SQLiteOpenHelper {

    private static final String TAG = DbHelper.class.getName();

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "raildb.sqlite";
    private static final String DB_PATH_SUFFIX = "/databases/";

    // Tables name
    private static final String TABLE_TIME_TABLE = "timetable";

    private static final String KEY_ID = "id";
    private static final String KEY_TRAIN_NO = "trainno";
    private static final String KEY_TRAIN_NAME = "trainname";
    private static final String KEY_ISL_NO = "islno";
    private static final String KEY_STATION_CODE = "stationcode";
    private static final String KEY_STATION_NAME = "stationname";
    private static final String KEY_ARRIVAL_TIME = "arrivaltime";
    private static final String KEY_DEPARTURE_TIME = "departuretime";
    private static final String KEY_DISTANCE = "distance";
    private static final String KEY_SOURCE_STN_CODE = "sourcestncode";
    private static final String KEY_SOURCE_STN_NAME = "sourcestnname";
    private static final String KEY_DEST_STN_CODE = "deststncode";
    private static final String KEY_DEST_STN_NAME = "deststnname";

    private Context context;

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
    }


    public void CopyDataBaseFromAsset() throws IOException {

        InputStream myInput = context.getAssets().open(DATABASE_NAME);

        String outFileName = getDatabasePath();

        File f = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
        if (!f.exists())
            f.mkdir();

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    private String getDatabasePath() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX
                + DATABASE_NAME;
    }

    public SQLiteDatabase openDataBase() throws SQLException {
        File dbFile = context.getDatabasePath(DATABASE_NAME);

        if (!dbFile.exists()) {
            try {
                CopyDataBaseFromAsset();
                System.out.println("Copying sucess from Assets folder");
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }

        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.NO_LOCALIZED_COLLATORS | SQLiteDatabase.CREATE_IF_NECESSARY);
    }


    public List<String> getTrainNumber(String input){
        List<String> result = new ArrayList<>();
        try {
            String query = "SELECT " + KEY_TRAIN_NO + ", " + KEY_TRAIN_NAME + " FROM " + TABLE_TIME_TABLE + " WHERE " + KEY_TRAIN_NO
                    + " LIKE '%" + input + "%' OR " + KEY_TRAIN_NAME + " LIKE '%" + input + "%'";
            Log.e(TAG, query);
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if(cursor.moveToFirst()) {
                do {
                    result.add(cursor.getString(1) + " (" + cursor.getString(0) + ")");
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<TimeTable> getTimeTable(String trainNumber){
        List<TimeTable> timeTable = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + TABLE_TIME_TABLE + " WHERE " + KEY_TRAIN_NO + " = '" + trainNumber + "'";
            Log.e(TAG, query);
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                do {
                    Log.e(TAG, "has list");
                    TimeTable data = new TimeTable();
                    data.setId(1);
                    data.setTrainNo(cursor.getString(0));
                    data.setTrainName(cursor.getString(1));
                    data.setIslNo(cursor.getString(2));
                    data.setStationCode(cursor.getString(3));
                    data.setStationName(cursor.getString(4));
                    data.setArrivalTime(cursor.getString(5));
                    data.setDepartureTime(cursor.getString(6));
                    data.setDistance(cursor.getString(7));
                    data.setSourceStnCode(cursor.getString(8));
                    data.setSourceStnName(cursor.getString(9));
                    data.setDestStnCode(cursor.getString(10));
                    data.setDestStnName(cursor.getString(11));
                    timeTable.add(data);
                    Log.e(TAG, cursor.getString(10));
                } while (cursor.moveToNext());
            }
        } catch (SQLiteDatabaseLockedException e) {
            e.printStackTrace();
        }

        return timeTable;
    }


}