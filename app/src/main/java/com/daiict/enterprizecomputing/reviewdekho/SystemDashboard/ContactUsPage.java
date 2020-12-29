package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.daiict.enterprizecomputing.reviewdekho.R;

public class ContactUsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_page);
    }

    public void mailMe(View view) {
            Log.e("mail","sendEmail");
            Intent intent=new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"address@mail.com"} );
            startActivity(Intent.createChooser(intent,"Choose Gmail To Contact Us!"));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SettingsProfile.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment",fragmentName);
        startActivity(intent);
    }
}