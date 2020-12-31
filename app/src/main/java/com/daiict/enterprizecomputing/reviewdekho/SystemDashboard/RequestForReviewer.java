package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.google.android.material.snackbar.Snackbar;

public class RequestForReviewer extends AppCompatActivity {
    EditText description;
    SharedPrefManager sharedPrefManager;

    private String desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_reviewer);
        description = findViewById(R.id.setting_desc);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SettingsProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment", "profilefragment");
        startActivity(intent);
        finish();
    }

    public void backButtonPressed(View view) {
        Intent intent = new Intent(this, SettingsProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment", "profilefragment");
        startActivity(intent);
        finish();
    }

    public void confirmButtonPressed(View view) {
        if(description.equals(""))
        {
            description.setError("Please Enter Description");
        }
        else
        {
            desc = description.getText().toString();
            if(requestDBConnection(desc,sharedPrefManager.getUserId()))
            {
                Intent intent = new Intent(this, SettingsProfile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //intent.putExtra("Fragment", "profilefragment");
                sharedPrefManager.setRequestType(true);
                startActivity(intent);
                finish();
            }
            else
            {
                Toast.makeText(this, "Oops! Something went wrong PLease try again later", Toast.LENGTH_SHORT).show();
                sharedPrefManager.setRequestType(false);
            }
        }
    }

    private boolean requestDBConnection(String desc, int userId) {
        //RequestDatabase
        return true;
    }
}