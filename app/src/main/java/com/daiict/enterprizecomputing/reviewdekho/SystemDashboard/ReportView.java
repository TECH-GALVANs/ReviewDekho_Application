package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.daiict.enterprizecomputing.reviewdekho.R;

public class ReportView extends AppCompatActivity {
    int review_id=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_view);

        if (getIntent().getExtras() != null) {
            //do here
            Intent mIntent = getIntent();
            review_id = mIntent.getIntExtra("review_id", 0);
        }

    }
}