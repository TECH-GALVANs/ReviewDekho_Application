package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CategoryView extends AppCompatActivity {
    Category category;
    RecyclerView recyclerView;
    SharedPrefManager sharedPrefManager;

    ArrayList<UserReviewClass> userDataClasses = new ArrayList<UserReviewClass>();
    AdapterUserReviewDisplay adapterUserReviewDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_view);
       // Intent intent = getIntent();
//        category = new Category(Integer.parseInt(intent.getStringExtra("Category_Id")),intent.getStringExtra("Category_Name"));

        sharedPrefManager  = new SharedPrefManager(this);
        recyclerView = findViewById(R.id.category_recyclerview);
        AdapterUserReviewDisplay adapterUserReviewDisplay;
        adapterUserReviewDisplay = new AdapterUserReviewDisplay(this,userDataClasses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterUserReviewDisplay);

        databaseConnection();

    }

    public void backButtonPressed(View view) {
        Intent intent = new Intent(this, SystemDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment", "profilefragment");
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, SystemDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment", "profilefragment");
        startActivity(intent);
        finish();
    }

    public void databaseConnection() {
        if (sharedPrefManager.getCatId() == -1) {
            Toast.makeText(this, "Error Occured", Toast.LENGTH_SHORT).show();
        } else {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(sharedPrefManager.getBaseURL())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            API instanceofapi = retrofit.create(API.class);
            Call<ArrayList<UserReviewClass>> call = instanceofapi.getReviewsByCategoryId(sharedPrefManager.getCatId());
            call.enqueue(new Callback<ArrayList<UserReviewClass>>() {
                @Override
                public void onResponse(Call<ArrayList<UserReviewClass>> call, Response<ArrayList<UserReviewClass>> response) {
                    if (response.isSuccessful()) {
                        userDataClasses = response.body();
                        Log.e("CategoryView : ", "Data Got : " + userDataClasses.size());
                        dataChangeRView();
//                    Toast.makeText(getContext(), userDataClasses.size(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<UserReviewClass>> call, Throwable t) {
                    Log.e("Category Fragment : ", "Error : " + t.getMessage());

                }
            });
        }
    }
    private void dataChangeRView()
    {
        adapterUserReviewDisplay  = new AdapterUserReviewDisplay(this,userDataClasses);
        recyclerView.setAdapter(adapterUserReviewDisplay);
        sharedPrefManager.setCatId(-1);
    }
}