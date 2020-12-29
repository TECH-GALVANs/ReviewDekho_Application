package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.daiict.enterprizecomputing.reviewdekho.R;

public class EditProfileGeneralSettings extends AppCompatActivity {

    EditText fname,lname,username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_general_settings);
        fname  =findViewById(R.id.textinputlayout_fname);
        lname  =findViewById(R.id.setting_change_lname);
        username  =findViewById(R.id.setting_change_username);

    }

    public void backButtonPressed(View view) {
        Intent intent = new Intent(this, SystemDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment", "profilefragment");
        startActivity(intent);

    }

    public void confirmButtonPressed(View view) {
        //Call Database and set Value
    }
}