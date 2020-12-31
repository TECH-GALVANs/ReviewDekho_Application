package com.daiict.enterprizecomputing.reviewdekho.DashboardLogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.daiict.enterprizecomputing.reviewdekho.Login.Login;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.daiict.enterprizecomputing.reviewdekho.SystemDashboard.SystemDashboard;

import org.w3c.dom.Text;

public class HowWeWork extends AppCompatActivity {

    TextView details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_we_work);
        details = findViewById(R.id.text_details);

        details.setText("Review Management System is an online platform that is used to\n" +
                "view and add reviews of any products.\nThis platform is available in mobile as well as in web also. As, we have\n" +
                "made web-app and android application of these platform.\nThe reviewers will share their opinion as well as reviews of any\n" +
                "products. ");

    }

    public void backButtonPressed(View view) {
        Intent intent = new Intent(this, DashboardLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, DashboardLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();

    }
}