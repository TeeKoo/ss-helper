package com.tk.teekoo777.startingstrengthhelper;


import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;


public class RelativeLayoutTouchListener extends GestureDetector.SimpleOnGestureListener {
    private int SWIPE_MIN_DISTANCE = 120;
    private int SWIPE_THRESHOLD_VELOCITY = 200;
    WorkoutActivity act;
    Animation animation;
    LinearLayout layout;
    String layout_id;
    public RelativeLayoutTouchListener(LinearLayout workout_layout, WorkoutActivity workoutActivity) {
        this.animation = AnimationUtils.loadAnimation(workoutActivity, R.anim.slide_left);
        this.layout = workout_layout;
        this.act = workoutActivity;
        layout_id = layout.getResources().getResourceName(layout.getId());
    }



@Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            if ( act.isComplete(layout.getId())==false ){
                layout.startAnimation(animation);
                checkAnimation();
            }
            return false; // Right to left
        }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            return false; // Left to right
        }

        if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            return false; // Bottom to top
        }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
            return false; // Top to bottom
        }
        return false;
    }


    private void checkAnimation(){
        animation.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation anim)
            {
            };
            public void onAnimationRepeat(Animation anim)
            {
            };
            public void onAnimationEnd(Animation anim)
            {
                act.hideKidsFromView(layout);
            };
        });
    }
}