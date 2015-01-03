package com.tk.teekoo777.startingstrengthhelper;

import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tk.teekoo777.startingstrengthhelper.lift.LiftContent;

import java.util.ArrayList;


public class WorkoutActivity extends ActionBarActivity {

    private GestureDetector mDetector;
    private String DEBUG_TAG = "workout_activity: ";
    private ArrayList<Integer> is_completed = new ArrayList<Integer>();
    MediaPlayer mp;
    Workouts workouts;
    private String workout_type = "A";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mp = MediaPlayer.create(getApplicationContext(), R.raw.weight_drop);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        workouts = new Workouts(this);
        LinearLayout workout1_layout = (LinearLayout) findViewById(R.id.workoutlayout_1);
        LinearLayout workout2_layout = (LinearLayout) findViewById(R.id.workoutlayout_2);
        LinearLayout workout3_layout = (LinearLayout) findViewById(R.id.workoutlayout_3);

        setWorkoutTexts("A");
        final GestureDetector gdt = new GestureDetector(new RelativeLayoutTouchListener(workout1_layout, this));
        final GestureDetector gdt2 = new GestureDetector(new RelativeLayoutTouchListener(workout2_layout, this));
        final GestureDetector gdt3 = new GestureDetector(new RelativeLayoutTouchListener(workout3_layout, this));



        workout1_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            }
        });
        workout2_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt2.onTouchEvent(event);
                return true;
            }
        });
        workout3_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt3.onTouchEvent(event);
                return true;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_workout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void changeSetNumber(View view){
        Button btn = (Button)findViewById(view.getId());
        String set = (String) btn.getText();
        switch (set) {
            case "5": btn.setText("4");
                break;
            case "4": btn.setText("3");
                break;
            case "3": btn.setText("2");
                break;
            case "2": btn.setText("1");
                break;
            case "1": btn.setText("");
                break;
            case "": btn.setText("5");
                break;
        }
    }


    public void hideKidsFromView(LinearLayout layout) {
        if ( is_completed.contains(layout.getId())==false ){
            mp.start();
            for ( int i = 0; i < layout.getChildCount();  i++ ){
                View view = layout.getChildAt(i);
                view.setVisibility(View.GONE);
            }
            String layout_id = layout.getResources().getResourceName(layout.getId());
            layout.setOrientation(LinearLayout.VERTICAL);
            TextView tv = new TextView(getApplicationContext());
            TextView tv2 = new TextView(getApplicationContext());
            tv.setText("Workout complete!");
            tv2.setText("5/5/5 @ 95kg");
            tv.setTextSize(35);
            tv2.setTextSize(15);
            tv.setTextColor(getResources().getColor(R.color.basic_black));
            tv2.setTextColor(getResources().getColor(R.color.basic_gray));
            layout.addView(tv);
            layout.addView(tv2);
            insertWorkouts(layout_id);
            is_completed.add(layout.getId());
            ArrayList<LiftContent.Lift> all_lifts = workouts.getAllWorkouts();
        }
    }

    public boolean isComplete(Integer layout_id){
        return is_completed.contains(layout_id);
    }

    private void setWorkoutTexts(String wtype){
        TextView txt1 = (TextView) findViewById(R.id.workout1);
        TextView txt2 = (TextView) findViewById(R.id.workout2);
        TextView txt3 = (TextView) findViewById(R.id.workout3);

        if (wtype.equals("A")){
            txt1.setText("Squat");
            txt2.setText("Bench Press");
            txt3.setText("Deadlift");
        }
        if (wtype.equals("B")){
            txt1.setText("Press");
            txt2.setText("Row");
            txt3.setText("Squat");
        }

    }

    private void insertWorkouts(String layout_id){
       if (layout_id.equals("com.tk.teekoo777.startingstrengthhelper:id/workoutlayout_1") ){
           Button btn1 = (Button) findViewById(R.id.workout_weight_1);
           Button btn2 = (Button) findViewById(R.id.workout_weight_2);
           Button btn3 = (Button) findViewById(R.id.workout_weight_3);
           TextView weighttext = (TextView) findViewById(R.id.weight1);
           String set1 = btn1.getText().toString();
           String set2 = btn2.getText().toString();
           String set3 = btn3.getText().toString();
           String weight_txt = weighttext.getText().toString();
           int lift1 =  (set1.equals("")) ? 0 : Integer.parseInt(set1);
           int lift2 =  (set2.equals("")) ? 0 : Integer.parseInt(set2);
           int lift3 =  (set3.equals("")) ? 0 : Integer.parseInt(set3);
           long weight = Long.parseLong(weight_txt);
           workouts.insertWorkout(workout_type, "squat", lift1, lift2, lift3, weight);
       }
        if (layout_id.equals("com.tk.teekoo777.startingstrengthhelper:id/workoutlayout_2")){
            Button btn1 = (Button) findViewById(R.id.workout_weight_4);
            Button btn2 = (Button) findViewById(R.id.workout_weight_5);
            Button btn3 = (Button) findViewById(R.id.workout_weight_6);
            TextView weighttext = (TextView) findViewById(R.id.weight2);
            TextView workout_txt = (TextView) findViewById(R.id.workout2);
            String set1 = btn1.getText().toString();
            String set2 = btn2.getText().toString();
            String set3 = btn3.getText().toString();
            String weight_txt = weighttext.getText().toString();
            int lift1 =  (set1.equals("")) ? 0 : Integer.parseInt(set1);
            int lift2 =  (set2.equals("")) ? 0 : Integer.parseInt(set2);
            int lift3 =  (set3.equals("")) ? 0 : Integer.parseInt(set3);
            long weight = Long.parseLong(weight_txt);
            String workout_text = workout_txt.getText().toString();
            workouts.insertWorkout(workout_type, workout_text, lift1, lift2, lift3, weight);
        }
        if (layout_id.equals("com.tk.teekoo777.startingstrengthhelper:id/workoutlayout_3")){
            Button btn1 = (Button) findViewById(R.id.workout_weight_7);
            Button btn2 = (Button) findViewById(R.id.workout_weight_8);
            Button btn3 = (Button) findViewById(R.id.workout_weight_9);
            TextView weighttext = (TextView) findViewById(R.id.weight3);
            TextView workout_txt = (TextView) findViewById(R.id.workout3);
            String set1 = btn1.getText().toString();
            String set2 = btn2.getText().toString();
            String set3 = btn3.getText().toString();
            String weight_txt = weighttext.getText().toString();
            int lift1 =  (set1.equals("")) ? 0 : Integer.parseInt(set1);
            int lift2 =  (set2.equals("")) ? 0 : Integer.parseInt(set2);
            int lift3 =  (set3.equals("")) ? 0 : Integer.parseInt(set3);
            long weight = Long.parseLong(weight_txt);
            String workout_text = workout_txt.getText().toString();
            workouts.insertWorkout(workout_type, workout_text, lift1, lift2, lift3, weight);
        }
    }
}
