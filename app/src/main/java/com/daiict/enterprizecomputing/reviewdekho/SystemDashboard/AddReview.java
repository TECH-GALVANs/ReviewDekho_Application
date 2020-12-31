package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.daiict.enterprizecomputing.reviewdekho.Classes.AddReviewClassData;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentsClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.ImageClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Product;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SubCategory;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddReview extends AppCompatActivity {
ImageView imageView;
SharedPrefManager sharedPrefManager;
ArrayList<Category>  arrayListC = new ArrayList<>();
ArrayList<SubCategory>  arrayListS = new ArrayList<>();
ArrayList<Product>  arrayListP = new ArrayList<>();

ArrayList<String> arrayListCName = new ArrayList<>();
ArrayList<String> arrayListSName = new ArrayList<>();
ArrayList<String> arrayListPName = new ArrayList<>();
int prodIndex=-1;
Spinner category,subCategory, product;
EditText review;
int prodid=-1;


Button upload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        sharedPrefManager = new SharedPrefManager(this);

        imageView = findViewById(R.id.image_load);
        category = findViewById(R.id.category_Spinner);
        subCategory = findViewById(R.id.subcategory_Spinner);
        product = findViewById(R.id.Product_Spinner);

        upload = findViewById(R.id.btn_upload);
        review = findViewById(R.id.edit_review);



       // loadData();
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i = category.getSelectedItemPosition();
                DBSubCategory(arrayListC.get(i).getCategoryId());
                arrayListPName.clear();
                arrayListSName.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                upload.setClickable(false);

            }
        });

        subCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int i= subCategory.getSelectedItemPosition();
                DBProduct(arrayListS.get(i).getSubCategoryId());
                arrayListPName.clear();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                upload.setClickable(false);
            }
        });

        product.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prodIndex= parent.getSelectedItemPosition();

                byte[] decodedString = Base64.decode(arrayListP.get(prodIndex).getImage(), Base64.DEFAULT);
                Glide.with(getApplicationContext()).asBitmap().load(decodedString).into(imageView);

                prodid = arrayListP.get(prodIndex).getProductid();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                upload.setClickable(false);
            }
        });

        loadDataC();

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

    public void backButtonPressed(View view) {
        Intent intent = new Intent(this, SystemDashboard.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //intent.putExtra("Fragment", "profilefragment");
        startActivity(intent);
        finish();
    }

    void loadDataC() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API instanceofapi = retrofit.create(API.class);
        Call<ArrayList<Category>> call = instanceofapi.getCategory();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                arrayListC = response.body();
                Log.e("Category Got","Category getting successfull");
                storeAllStringCat();
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {

            }
        });

    }

    private void storeAllStringCat()
    {
        for(int i=0;i<arrayListC.size();i++)
        {
            arrayListCName.add(arrayListC.get(i).getCategoryName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrayListCName);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapter);


    }
    private void DBSubCategory(int subcatid)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API instanceofapi = retrofit.create(API.class);
        Call<ArrayList<SubCategory>> call = instanceofapi.getSubCategoryByID(subcatid);
        call.enqueue(new Callback<ArrayList<SubCategory>>() {
            @Override
            public void onResponse(Call<ArrayList<SubCategory>> call, Response<ArrayList<SubCategory>> response) {
                arrayListS = response.body();
                Log.e(" sub Category Got"," sub Category getting successfull");
                storeAllStringsub();
            }

            @Override
            public void onFailure(Call<ArrayList<SubCategory>> call, Throwable t) {

            }
        });
    }

    private void storeAllStringsub()
    {
        for(int i=0;i<arrayListS.size();i++)
        {
            arrayListSName.add(arrayListS.get(i).getSubCategoryName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrayListSName);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subCategory.setAdapter(adapter);


    }


    private void DBProduct(int prodid)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API instanceofapi = retrofit.create(API.class);
        Call<ArrayList<Product>> call = instanceofapi.getProductsById(prodid);
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                arrayListP = response.body();
                Log.e(" sub Category Got"," sub Category getting successfull");
                storeAllStringprod();
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {

            }
        });
    }

    private void storeAllStringprod()
    {
        for(int i=0;i<arrayListP.size();i++)
        {
            arrayListPName.add(arrayListP.get(i).getProductName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, arrayListPName);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        product.setAdapter(adapter);


    }

    public void addImage(View view) {

    }

    public void ConfirmButtonPressed(View view) {
        if(category.getSelectedItemPosition()>-1 && subCategory.getSelectedItemPosition()>-1 && product.getSelectedItemPosition()>-1)
        {
            if(review.getText().toString().equals(""))
            {
                review.setError("Kindly Enter Review");
            }
            else
            {
                sendDataForReviewer();
            }
        }
    }

    private void sendDataForReviewer()
    {
        //Database To store Data

        Product p = new Product(prodid);
        UserDataClass userDataClass = new UserDataClass(sharedPrefManager.getUserId());
        AddReviewClassData addReview = new AddReviewClassData(review.getText().toString(),p,userDataClass);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        API instanceofapi = retrofit.create(API.class);
        Call<AddReviewClassData> call = instanceofapi.reportReview(addReview);
        call.enqueue(new Callback<AddReviewClassData>() {
            @Override
            public void onResponse(Call<AddReviewClassData> call, Response<AddReviewClassData> response) {
                if (response.isSuccessful()) {

                    Toast.makeText(AddReview.this, "Your Post has been Posted", Toast.LENGTH_LONG).show();
                    review.setText("");
//                   Toast.makeText(getContext(), userDataClasses.size(), Toast.LENGTH_SHORT).show();
                }
                // Log.e("Feed Fragment : ","Data Got : "+userDataClasses.size());
            }

            @Override
            public void onFailure(Call<AddReviewClassData> call, Throwable t) {
                Log.e("Comments : ", "Error : " + t.getMessage());

            }
        });
    }


}