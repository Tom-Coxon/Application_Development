package com.example.tomco.appdevassignment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static android.os.SystemClock.sleep;

public class WelcomeActivity extends Activity {

    private
    Animation myAnimDown;
    Animation myAnimUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startLayout1();
    }

    private void startLayout1() {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        ImageView calView = (ImageView) findViewById(R.id.cal);
        calView.setImageResource(R.drawable.calendar_icon);

        TextView welcomeView = (TextView) findViewById(R.id.welcomeText);
        welcomeView.setText("App Development\nAssignment");

        TextView textView = (TextView) findViewById(R.id.text);
        textView.setText("Thomas Coxon\n100404970");

        myAnimDown = AnimationUtils.loadAnimation(this,R.anim.transition_down);
        myAnimUp = AnimationUtils.loadAnimation(this,R.anim.transition_up);

        calView.setAnimation(myAnimDown);
        welcomeView.setAnimation(myAnimUp);
        textView.setAnimation(myAnimUp);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                finish();
            }
        }, 2000);
    };
}