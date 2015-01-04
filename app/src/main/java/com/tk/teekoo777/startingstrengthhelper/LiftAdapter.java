package com.tk.teekoo777.startingstrengthhelper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.tk.teekoo777.startingstrengthhelper.lift.LiftContent;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LiftAdapter extends ArrayAdapter<LiftContent.Lift> {
    public LiftAdapter(Context context, ArrayList<LiftContent.Lift> lifts) {
        super(context, 0, lifts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        LiftContent.Lift lift = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.lift_custom, parent, false);
        }
        // Lookup view for data population
        TextView lift1 = (TextView) convertView.findViewById(R.id.label);
        TextView lift2 = (TextView) convertView.findViewById(R.id.label2);
        TextView lift3 = (TextView) convertView.findViewById(R.id.label3);
        TextView type = (TextView) convertView.findViewById(R.id.workout_type);
        // Populate the data into the template view using the data object
        if (lift.workout_type=="A"){
            type.setBackgroundColor(convertView.getResources().getColor(R.color.workout_type_blue));
        } else {
            type.setBackgroundColor(convertView.getResources().getColor(R.color.workout_type_green));
        }

        lift1.setText(lift.lift);
       // lift2.setText(lift.created_at);
        lift3.setText(lift.set3);
        // Return the completed view to render on screen
        return convertView;
    }
}