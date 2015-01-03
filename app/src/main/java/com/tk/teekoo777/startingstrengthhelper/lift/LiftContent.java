package com.tk.teekoo777.startingstrengthhelper.lift;

import java.util.ArrayList;
import java.util.List;

public class LiftContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<Lift> ITEMS = new ArrayList<Lift>();


    private static void addItem(Lift item) {
        ITEMS.add(item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class Lift {
        public String id;
        public String lift;
        public String set1;
        public String set2;
        public String set3;
        public String weight;
        public String created_at;
        public String workout_type;

        public Lift(String id, String workout_type, String lift, String set1, String set2, String set3, String weight, String created_at) {
            this.id = id;
            this.lift = lift;
            this.set1 = set1;
            this.set2 = set2;
            this.set3 = set3;
            this.weight = weight;
            this.workout_type = workout_type;
            this.created_at = created_at;
        }

    }
}
