package com.tk.teekoo777.startingstrengthhelper.lift;

/**
 * Created by teekoo777 on 3.1.2015.
 */
public class Workout {

    private String id;
    private String workout_type;
    private String created_at;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public String getDate() {
        return this.created_at;
    }

    public void setDate(String created_at) {
         this.created_at = created_at;
    }

    public String getWorkoutType(){
        return this.workout_type;
    }

    public void setWorkoutType(String workout_type) {
        this.workout_type = workout_type;
    }
}