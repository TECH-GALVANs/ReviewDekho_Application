package com.daiict.enterprizecomputing.reviewdekho.DashboardLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.Login.Login;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.SignUp;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.SystemDashboard;

public class DashboardLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_login);
    }
    // TODO: 12/8/2020
    public void dashboardVisitorLogin(View view) {
        //Direct Visitor Login
        Toast.makeText(this, "You are Logged in as Visitor", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(DashboardLogin.this, SystemDashboard.class);
        startActivity(intent);

    }

    public void dashboardLogin(View view) {
        Intent intent = new Intent(DashboardLogin.this, Login.class);
        Pair[] pairs = new Pair[1];

        //From here the transition that is popup effect will be generated if and only if the API version > 21
        pairs[0] = new Pair<View,String>(findViewById(R.id.btn_login_dashboard),"btn_login_transition");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(DashboardLogin.this,pairs);
            startActivity(intent,options.toBundle());
        }
        else
        {
            startActivity(intent);
            finish();
        }

    }

    public void dashboardSignUp(View view) {
        Intent intent = new Intent(DashboardLogin.this, SignUp.class);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void dashboardHoweWork(View view) {
        // How we Work
    }
}