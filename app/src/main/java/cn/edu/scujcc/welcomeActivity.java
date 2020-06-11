package cn.edu.scujcc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import cn.edu.scujcc.weather.R;

public class welcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_welcome);
        Animation alpha = new AlphaAnimation(0.0f,1.0f);
        alpha.setRepeatCount(0);
        alpha.setDuration(4000);
        View v = View.inflate(this,R.layout.activity_welcome,null);
        setContentView(v);
        v.startAnimation(alpha);
        alpha.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
             tiaozhuan();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    private void tiaozhuan(){
        Intent intent = new Intent(welcomeActivity.this,loginActivity.class);
        startActivity(intent);
        finish();
    }
}