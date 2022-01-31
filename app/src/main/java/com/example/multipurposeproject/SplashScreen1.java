package com.example.multipurposeproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Objects;

public class SplashScreen1 extends AppCompatActivity {
    TextView textView, textView2;
    Animation fade_in_anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen1);
        textView = findViewById(R.id.textView6);
        textView2 = findViewById(R.id.textView45);
        getSupportActionBar().hide();                                      // To remove the action bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fade_in_anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        textView.startAnimation(fade_in_anim);
        textView2.startAnimation(fade_in_anim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen1.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);


        Thread td = new Thread(){

            public void run(){
                try{
                    sleep(3000);

                }
                catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    Intent i = new Intent(SplashScreen1.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };
        td.start();
    }
}