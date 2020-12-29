package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.R;

public class CategoryView extends AppCompatActivity {
    Category category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
        Intent intent = getIntent();
        category = new Category(Integer.parseInt(intent.getStringExtra("Category_Id")),intent.getStringExtra("Category_Name"));

    }
}