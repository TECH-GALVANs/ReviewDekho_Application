package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Login.Login;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.SignUp;
import com.google.android.material.snackbar.Snackbar;

public class SettingsProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_profile);
    }

    public void backButtonPressed(View view) {
    }

    public void contactUsButtonPressed(View view) {
    }

    public void aboutReviewDekhoButtonPressed(View view) {
    }

    public void logoutButtonPressed(View view) {
        AlertDialog.Builder Builder = new AlertDialog.Builder(SettingsProfile.this);
        View view_pop = getLayoutInflater().inflate(R.layout.popup_logout, null);

        final Button confirm, cancel;
        //hooks
        confirm = view_pop.findViewById(R.id.settings_pop_btn_confirm_logout);
        cancel = view_pop.findViewById(R.id.settings_pop_btn_reject_logout);

        //setting the view
        Builder.setView(view_pop);
        final Dialog dialog = Builder.create();
        dialog.show();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPrefManager sharedPrefManager = new SharedPrefManager(SettingsProfile.this);
                sharedPrefManager.setUserName(null);
                sharedPrefManager.setAccPassword(null);
                sharedPrefManager.setEmail(null);
                sharedPrefManager.setRolePreference(-1);
                sharedPrefManager.setUserId(-1);

                dialog.dismiss();
                Intent intent = new Intent(SettingsProfile.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void generalSettingButtonPressed(View view) {
    }

    public void passwordChangeButtonPressed(View view) {



    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }
}