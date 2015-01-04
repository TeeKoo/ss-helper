package com.tk.teekoo777.startingstrengthhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tk.teekoo777.startingstrengthhelper.lift.LiftContent;
import com.tk.teekoo777.startingstrengthhelper.lift.Workout;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by teekoo777 on 28.12.2014.
 */
public class Workouts extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "SSWorkouts_tk.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "workouts";
    private static final String DICTIONARY_TABLE_NAME2 = "workout_days";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS "+DICTIONARY_TABLE_NAME+
                    "(" +
                    "id integer primary key, " +
                    "workout_id int, " +
                    "lift text, " +
                    "set1 int, " +
                    "set2 int, " +
                    "set3 int, " +
                    "weight real)";

    private static final String DICTIONARY_TABLE_CREATE2 =
            "CREATE TABLE IF NOT EXISTS "+DICTIONARY_TABLE_NAME2+
                    "(" +
                    "id integer primary key, " +
                    "workout_type text," +
                    "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";
    Workouts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DICTIONARY_TABLE_CREATE2);
        db.execSQL(DICTIONARY_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
    String squat
    String bench
    String deadlift
    String power_clean
    String press
    String row
    */
    public long insertWorkout  (String workout_id, String lift, int set1, int set2, int set3, double weight)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("workout_id", workout_id);
        contentValues.put("lift", lift);
        contentValues.put("set1", set1);
        contentValues.put("set2", set2);
        contentValues.put("set3", set3);
        contentValues.put("weight", weight);

        return db.insert(DICTIONARY_TABLE_NAME, null, contentValues);
    }


    /*public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }*/

       public String getLastWorkoutType(){
           SQLiteDatabase db = this.getReadableDatabase();
           Cursor res =  db.rawQuery( "select * from workout_days where id = (SELECT MAX(ID) FROM workout_days)", null );
           if (res.getCount() > 0){
               res.moveToFirst();
               return res.getString(res.getColumnIndex("workout_type"));
           } else{
               return "";
           }
       }



    public Workout getTodaysWorkout(){
        SQLiteDatabase db = this.getReadableDatabase();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        Workout w = new Workout();
        Cursor res = db.query("workout_days", null, "date(created_at) = ?", new String[] {date}, null, null, null);

        if ( res.getCount() > 0 ){
            res.moveToFirst();
            w.setDate(res.getString(res.getColumnIndex("created_at")));
            w.setId(res.getString(res.getColumnIndex("id")));
            w.setWorkoutType(res.getString(res.getColumnIndex("workout_type")));
        }

        return w;
    }

    public String setTodaysWorkout(String workout_type){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("workout_type", workout_type);
        return String.valueOf(db.insert(DICTIONARY_TABLE_NAME2, null, contentValues));
    }


    public ArrayList<LiftContent.Lift> getAllWorkouts()
    {
        ArrayList<LiftContent.Lift> array_list = new ArrayList<LiftContent.Lift>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from workouts JOIN workout_days ON workouts.workout_id=workout_days.id", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){

            LiftContent.Lift lift = new LiftContent.Lift
                    (
                      res.getString(res.getColumnIndex("id")),
                      res.getString(res.getColumnIndex("workout_id")),
                      res.getString(res.getColumnIndex("lift")),
                      res.getString(res.getColumnIndex("set1")),
                      res.getString(res.getColumnIndex("set2")),
                      res.getString(res.getColumnIndex("set3")),
                      res.getString(res.getColumnIndex("weight")),
                      res.getString(res.getColumnIndex("workout_type"))
                    );

            array_list.add(lift);
            res.moveToNext();
        }
        return array_list;

    }


}
