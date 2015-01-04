package com.tk.teekoo777.startingstrengthhelper.lift;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Button;

import com.tk.teekoo777.startingstrengthhelper.WorkoutActivity;

import java.util.ArrayList;

/**
 * Created by teekoo777 on 4.1.2015.
 */
public class ButtonFunctions {
    ArrayList<Button> buttons;
    Activity activity;
    public ButtonFunctions(WorkoutActivity workoutActivity, ArrayList<Button> buttons){
        this.activity = workoutActivity;
        this.buttons = buttons;
    }

    public void setAllTexts(){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        for (int i = 0; i < buttons.size(); i++){
            editor.putString(String.valueOf(buttons.get(i).getId()), (String) buttons.get(i).getText());
        }
        editor.clear();
        editor.commit();
    }

    public void restoreTexts() {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        for (int i = 0; i < buttons.size(); i++){
            buttons.get(i).setText(sharedPref.getString(String.valueOf(buttons.get(i).getId()), ""));
        }

    }

    public void clearAll(){
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.clear();
        editor.commit();
    }

}
