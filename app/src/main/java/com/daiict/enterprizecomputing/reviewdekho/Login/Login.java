package com.daiict.enterprizecomputing.reviewdekho.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.daiict.enterprizecomputing.reviewdekho.DashboardLogin.DashboardLogin;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.SignUp;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void loginBack(View view) {
        // Back button pressed
        Intent intent = new Intent(Login.this, DashboardLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void loginForgotPassword(View view) {
        // Forgot Password Button
        Intent intent = new Intent(Login.this, ForgotPassword.class);
        startActivity(intent);
    }

      // TODO: 12/8/2020
    public void btnLoginOnClick(View view) {
        // Login Button Check whether the data is valid or not
    }

    public void loginCreateAccount(View view) {
        // Create Account Button
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        finish();
    }
}