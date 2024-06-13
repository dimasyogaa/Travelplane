package com.yogadimas.travelplane.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yogadimas.travelplane.R;

public class SplashActivity extends AppCompatActivity {

    final long lDelay = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Animation animFade = AnimationUtils.loadAnimation(this, R.anim.anim_fade);
        ImageView ivApp = findViewById(R.id.iv_app);
        TextView tvApp = findViewById(R.id.tv_app);

        ivApp.startAnimation(animFade);
        tvApp.startAnimation(animFade);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent goToMainActivity = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(goToMainActivity);
            finish();
        }, lDelay);


    }
}