package com.daiict.enterprizecomputing.reviewdekho.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.DashboardLogin.DashboardLogin;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.Login.Login;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {

    EditText editTextEmail;
    EditText editTextUsername;
    EditText editTextPassword;
    TextView errorDetails;

    //Data Class
    SignupClass userdData ;

    //Shared Preference Manager
    SharedPrefManager sharedPrefManager;


    private static final Pattern ALPHABET_ONLY = Pattern.compile("^[a-zA-Z ]+$");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

         editTextEmail = findViewById(R.id.txtv_signup_email);
         editTextUsername = findViewById(R.id.txtv_signup_username);
         editTextPassword = findViewById(R.id.txtv_signup_pass);
         errorDetails = findViewById(R.id.txtPasswordMsg);

         sharedPrefManager = new SharedPrefManager(this);


        editTextPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (editTextPassword.getText().length() <= 0) {
                    errorDetails.setText(getResources().getString(R.string.password_message));
                    errorDetails.setVisibility(View.VISIBLE);
                }
                if(!PASSWORD_PATTERN.matcher(editTextPassword.getText().toString()).matches())
                {
                    errorDetails.setText(getResources().getString(R.string.signup_password_pattern));
                    errorDetails.setVisibility(View.VISIBLE);
                }
                else {
                    errorDetails.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    //Todo
    public void registerButtonClicked(View view) {
        // On Register data should be pushed

        if(emailidValidation() && validatePassword() && validateUsername())
        {
            // If all the things right then Fetch the data
            //set Data to the dataclass object

            userdData = new SignupClass(editTextEmail.getText().toString().trim(),editTextUsername.getText().toString().trim(),editTextPassword.getText().toString().trim());
            databaseConnection();
            //Toast.makeText(this, "Data Posted", Toast.LENGTH_SHORT).show();
        }

    }

    public void loginBtnSignUp(View view) {

        //Login Details


        Intent intent = new Intent(this, Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void backBtnSignUp(View view) {
        // On back pressed the page is loaded is Dashboard
        Intent intent = new Intent(this, DashboardLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    public void databaseConnection(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        try {

            API instanceofapi = retrofit.create(API.class);
            Call<SignupClass> call = instanceofapi.createUser(userdData);
            call.enqueue(new Callback<SignupClass>() {
                @Override
                public void onResponse(Call<SignupClass> call, Response<SignupClass> response) {
                    Log.e("Code Signup : " +response.code(),"     Code number");
                    if (!response.isSuccessful()) {
                        Toast.makeText(SignUp.this, "Error", Toast.LENGTH_LONG).show();
                    }

                    if(response.isSuccessful())
                    {

                        editTextEmail.setText("");
                        editTextPassword.setText("");
                        editTextUsername.setText("");
                        Log.e("SignUp","Data Posted");
                        Snackbar.make(findViewById(android.R.id.content), "Signed Up Successful ! Login... ", Snackbar.LENGTH_LONG)
                                .setAction("Okay", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(SignUp.this, Login.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .setActionTextColor(Color.RED)
                                .show();
                    }                }

                @Override
                public void onFailure(Call<SignupClass> call, Throwable t) {
                    Log.e("Error Generated : ", t.getMessage());
                    Snackbar.make(findViewById(android.R.id.content), "Something went Wrong!!", Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.RED)
                            .show();

                }
            });
        }catch (Exception exception)
        {
            Snackbar.make(findViewById(android.R.id.content), "Oops! Error Occurred", Snackbar.LENGTH_LONG)
                    .setActionTextColor(Color.RED)
                    .show();
        }

    }

    private boolean emailidValidation()
    {
        String emailtext =editTextEmail.getText().toString().trim();


        if (TextUtils.isEmpty(emailtext)) {
            editTextEmail.setError("Enter Email Address");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailtext).matches()) {
            editTextEmail.setError("Email Address Should be Valid");
            return false;
        } else {
            editTextEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername()
    {
        String username = editTextUsername.getText().toString().trim();
        /*
         *If First Name is Empty or First Name contains not only Alphabets then return false
         * Else Return True
         */
        if (TextUtils.isEmpty(username)) {
            editTextUsername.setError("Enter Username");
            return false;
        } else if (!ALPHABET_ONLY.matcher(username).matches()) {
            editTextUsername.setError("Username Name contains only Alphabets");
            return false;
        } else {
            editTextUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String password = editTextPassword.getText().toString();

        /*
         * If password isn't matches the given password format or expression then return false
         * Else return true
         */

        if (TextUtils.isEmpty(password)) {
            errorDetails.setText(getResources().getString(R.string.signup_password_error));
            errorDetails.setVisibility(View.VISIBLE);
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            errorDetails.setText(getResources().getString(R.string.password_message));
            errorDetails.setVisibility(View.VISIBLE);
            return false;
        } else {
            errorDetails.setVisibility(View.GONE);
            return true;
        }

    }


}
