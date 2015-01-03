package com.tk.teekoo777.startingstrengthhelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tk.teekoo777.startingstrengthhelper.lift.LiftContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by teekoo777 on 28.12.2014.
 */
public class Workouts extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "SSWorkouts_tk.db";
    private static final int DATABASE_VERSION = 2;
    private static final String DICTIONARY_TABLE_NAME = "workouts";
    private static final String DICTIONARY_TABLE_CREATE =
            "CREATE TABLE IF NOT EXISTS "+DICTIONARY_TABLE_NAME+
                    "(" +
                    "id integer primary key, " +
                    "workout_type text, " +
                    "lift text, " +
                    "set1 int, " +
                    "set2 int, " +
                    "set3 int, " +
                    "weight real, " +
                    "created_at DATETIME DEFAULT CURRENT_TIMESTAMP);";

    Workouts(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
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
    public long insertWorkout  (String workout_type, String lift, int set1, int set2, int set3, double weight)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("workout_type", workout_type);
        contentValues.put("lift", lift);
        contentValues.put("set1", set1);
        contentValues.put("set2", set2);
        contentValues.put("set3", set3);
        contentValues.put("weight", weight);

        return db.insert(DICTIONARY_TABLE_NAME, null, contentValues);
    }

   /* public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        //int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }
    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
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
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("contacts",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList getAllWorkouts()
    {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }*/

    public ArrayList<LiftContent.Lift> getAllWorkouts()
    {
        ArrayList<LiftContent.Lift> array_list = new ArrayList<LiftContent.Lift>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from workouts", null );
        res.moveToFirst();
        while(res.isAfterLast() == false){

            LiftContent.Lift lift = new LiftContent.Lift
                    (
                      res.getString(res.getColumnIndex("id")),
                      res.getString(res.getColumnIndex("workout_type")),
                      res.getString(res.getColumnIndex("lift")),
                      res.getString(res.getColumnIndex("set1")),
                      res.getString(res.getColumnIndex("set2")),
                      res.getString(res.getColumnIndex("set3")),
                      res.getString(res.getColumnIndex("weight")),
                      res.getString(res.getColumnIndex("created_at"))
                    );

            array_list.add(lift);
            res.moveToNext();
        }
        return array_list;

    }
}
