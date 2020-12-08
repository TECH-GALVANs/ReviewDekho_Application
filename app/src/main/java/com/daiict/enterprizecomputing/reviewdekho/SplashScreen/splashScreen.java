package com.daiict.enterprizecomputing.reviewdekho.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiict.enterprizecomputing.reviewdekho.DashboardLogin.DashboardLogin;
import com.daiict.enterprizecomputing.reviewdekho.IntroSlider.introSlider;
import com.daiict.enterprizecomputing.reviewdekho.R;

public class splashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    //above value is in milisecs

    //creating variable for animation
    Animation top, bottom;

    //Creating Objects of layouts
    ImageView imageView;
    TextView txttag, txtini;

    //Shared Peferences for letting know the user entry
    SharedPreferences shared_introslider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
        //Removing Status bar from the top
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        top = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        imageView = findViewById(R.id.imageView);
        txttag = findViewById(R.id.txtag);
        txtini = findViewById(R.id.txtini);

        //Assigning animations
        imageView.setAnimation(top);
        txtini.setAnimation(bottom);
        txttag.setAnimation(bottom);

        //Lets give the timeout after which nextpage will be loaded;
        //Create a Handler
        //within run intent passed

        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void run() {
                shared_introslider = getSharedPreferences("introSlider", MODE_PRIVATE);
                boolean isfirsttimeUser = shared_introslider.getBoolean("isfirsttime", true);

                if (isfirsttimeUser) {
                    //After getting first time as true for the next time whem user arrives make it false
                    SharedPreferences.Editor editor = shared_introslider.edit();
                    editor.putBoolean("isfirsttime", false);
                    editor.commit();


                    Intent intent = new Intent(splashScreen.this, introSlider.class);
                    startActivity(intent);
                    finish();
                } else {



                    Intent intent = new Intent(splashScreen.this, DashboardLogin.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, SPLASH_SCREEN);

    }
}