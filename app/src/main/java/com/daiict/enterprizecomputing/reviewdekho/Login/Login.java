package com.daiict.enterprizecomputing.reviewdekho.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.DashboardLogin.DashboardLogin;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.SignupClass;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.SignUp;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.SystemDashboard;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {
    EditText editTextEmailLogin;
    EditText editTextPasswordLogin;
    Button buttonLogin;

    UserDataClass userDataClass;

    //Progress Bar
    RelativeLayout relativeLayoutProgress;

    //SharedPreference For Sessions Store
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmailLogin = findViewById(R.id.login_edit_email);
        editTextPasswordLogin = findViewById(R.id.login_edit_password);
        buttonLogin = findViewById(R.id.login_btn_login);

        relativeLayoutProgress = findViewById(R.id.progress_bar_login_rl);


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
        if (emailidValidation() && validatePassword()) {
            editTextEmailLogin.setEnabled(false);
            editTextPasswordLogin.setEnabled(false);
            buttonLogin.setEnabled(false);

            // Progress Dialog Here......
            relativeLayoutProgress.setVisibility(View.VISIBLE);
            databaseConnectionLogin(editTextEmailLogin.getText().toString().trim());
        }
    }

    public void loginCreateAccount(View view) {
        // Create Account Button
        Intent intent = new Intent(Login.this, SignUp.class);
        startActivity(intent);
        finish();
    }

    private void databaseConnectionLogin(String emailFetch)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.134:9090/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API instanceofapi = retrofit.create(API.class);
        Call<UserDataClass> call = instanceofapi.getUserData(emailFetch);

        call.enqueue(new Callback<UserDataClass>() {
            @Override
            public void onResponse(Call<UserDataClass> call, Response<UserDataClass> response) {
                if(response.isSuccessful())
                {
                    userDataClass = response.body();
                    Log.e("Data Got is : ",userDataClass.getEmailID() +" "+userDataClass.getAccPass());

                    if(userDataClass != null)
                    {
                        //Perform Shared Preference
                        sharedPrefManager  = new SharedPrefManager(Login.this);
                        sharedPrefManager.setEmail(userDataClass.getEmailID());
                        sharedPrefManager.setAccPassword(userDataClass.getAccPass());
                        sharedPrefManager.setRolePreference(userDataClass.getUserRole());
                        sharedPrefManager.setUserId(userDataClass.getUserRole());
                        sharedPrefManager.setUserName(userDataClass.getUsername());

                        //set progressbar Off
                        relativeLayoutProgress.setVisibility(View.GONE);

                        Intent intent = new Intent(Login.this, SystemDashboard.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();

                    }
                    else
                    {
                        relativeLayoutProgress.setVisibility(View.GONE);
                        View rootView = getWindow().getDecorView().getRootView();
                        Snackbar authenticationFailed = Snackbar.make(rootView, "Oops! Something went wrong", Snackbar.LENGTH_SHORT);
                        authenticationFailed.show();
                        editTextEmailLogin.setEnabled(true);
                        editTextPasswordLogin.setEnabled(true);
                        buttonLogin.setEnabled(true);
                    }
                }

            }

            @Override
            public void onFailure(Call<UserDataClass> call, Throwable t) {
                    editTextEmailLogin.setEnabled(true);
                    editTextPasswordLogin.setEnabled(true);
                    Log.e("btnLoginOnClick: ", "Authentication Failed!!!");

                    relativeLayoutProgress.setVisibility(View.GONE);
                    View rootView = getWindow().getDecorView().getRootView();
                    Snackbar authenticationFailed = Snackbar.make(rootView, "Authentication Failed", Snackbar.LENGTH_SHORT);
                    authenticationFailed.show();
                    buttonLogin.setEnabled(true);
            }
        });




    }

    private boolean emailidValidation()
    {
        String emailtext = editTextEmailLogin.getText().toString().trim();


        if (TextUtils.isEmpty(emailtext)) {
            editTextEmailLogin.setError("Enter Email Address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
            editTextEmailLogin.setError("Email Address Should be Valid");
            return false;
        } else {
            editTextEmailLogin.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = editTextPasswordLogin.getText().toString();

        if (TextUtils.isEmpty(password)) {
            View rootView = getWindow().getDecorView().getRootView();
            Snackbar notEmptyErrorMsg = Snackbar.make(rootView, "Password Cannot Be Empty.", Snackbar.LENGTH_SHORT);
            notEmptyErrorMsg.show();
            return false;
        } else {
            return true;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Login.this, DashboardLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}