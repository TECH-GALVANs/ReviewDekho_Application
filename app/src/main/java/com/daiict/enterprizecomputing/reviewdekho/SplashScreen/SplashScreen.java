package com.daiict.enterprizecomputing.reviewdekho.SplashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.DashboardLogin.DashboardLogin;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.IntroSlider.IntroSlider;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.SystemDashboard;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN = 5000;
    //above value is in milisecs

    //creating variable for animation
    Animation top, bottom;

    //Creating Objects of layouts
    ImageView imageView;
    TextView txttag, txtini;

    //Shared Peferences for letting know the user entry
    SharedPreferences shared_introslider;

    private String email;
    private String pass;
    UserDataClass userDataClass;
    private boolean state = false;
    private boolean isUSer = false;
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

        if(methodForFirstTime())
        {
            Log.e("Data ","Data Got Properly "+email+"    "+pass);
            try{
                databaseCheck();
            }catch (Exception e)
            {
                Log.e("Error","Exception Generated in Database in DashBoard Login");
            }

        }

        //Lets give the timeout after which nextpage will be loaded;
        //Create a Handler
        //within run intent passed

        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ApplySharedPref")
            @Override
            public void run() {
                shared_introslider = getSharedPreferences("introSlider", MODE_PRIVATE);
                boolean isfirsttimeUser = shared_introslider.getBoolean("isfirsttime", true);
                if(isUSer)
                {
                    Intent intent = new Intent(SplashScreen.this, SystemDashboard.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }

                else{
                    if (state) {
                        Intent intent = new Intent(SplashScreen.this, SystemDashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    } else {

                        if (isfirsttimeUser) {
                            //After getting first time as true for the next time whem user arrives make it false
                            SharedPreferences.Editor editor = shared_introslider.edit();
                            editor.putBoolean("isfirsttime", false);
                            editor.commit();


                            Intent intent = new Intent(SplashScreen.this, IntroSlider.class);
                            startActivity(intent);
                            finish();
                        } else {


                            Intent intent = new Intent(SplashScreen.this, DashboardLogin.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                }
                }
        }, SPLASH_SCREEN);


    }

    //Database Checking and data checking
    private boolean methodForFirstTime()
    {
        SharedPrefManager sharedPrefManager = new SharedPrefManager(this);
        String tempEmail;
        String tempPass;
        if(sharedPrefManager.getRolePreference() ==5)
        {
            isUSer = true;
            return false;
        }

        tempEmail = sharedPrefManager.getEmail();
        tempPass  =sharedPrefManager.getAccPassword();
        if(tempEmail.equals("") && tempPass.equals(""))
        {
            return false;
        }
        else
        {
            email = tempEmail;
            pass = tempPass;
            return true;
        }

    }

    private void databaseCheck() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.134:9090/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API instanceofapi = retrofit.create(API.class);
        Call<UserDataClass> call = instanceofapi.getUserData(email);

        call.enqueue(new Callback<UserDataClass>() {
            @Override
            public void onResponse(Call<UserDataClass> call, Response<UserDataClass> response) {
                if(response.isSuccessful())
                {
                    Log.e("Dashboard Login","    Data Getting");
                    userDataClass = response.body();
                    if(email.equals(userDataClass.getEmailID()) && pass.equals(userDataClass.getAccPass()))
                    {
                        state = true;
                        return;
                    }
                }
            }

            @Override
            public void onFailure(Call<UserDataClass> call, Throwable t) {
                View rootView = getWindow().getDecorView().getRootView();
                Snackbar authenticationFailed = Snackbar.make(rootView, "Session Expired !! Kindly Re-Login", Snackbar.LENGTH_SHORT);
                authenticationFailed.show();

                Intent intent = new Intent(SplashScreen.this, DashboardLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
    }
}