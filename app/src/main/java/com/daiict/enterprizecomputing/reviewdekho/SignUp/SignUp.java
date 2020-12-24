package com.daiict.enterprizecomputing.reviewdekho.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.daiict.enterprizecomputing.reviewdekho.DashboardLogin.DashboardLogin;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {
    ArrayList<DataClass> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.0.134/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//       //DataClass dataClass = new DataClass("aakashdubey742@gmail.com","Aakash Dubey","Helloworld");
//
//        API instanceofapi = retrofit.create(API.class);
//        Call<ArrayList<DataClass>> call = instanceofapi.getData();
//        call.enqueue(new Callback<ArrayList<DataClass>>() {
//            @Override
//            public void onResponse(Call<ArrayList<DataClass>> call, Response<ArrayList<DataClass>> response) {
//                if(response.isSuccessful()) {
//                    Toast.makeText(SignUp.this, "Data get Properly", Toast.LENGTH_LONG).show();
//                    arrayList = response.body();
//
//                }
//                Toast.makeText(SignUp.this, "Data not get"+response.code()+"  /// ", Toast.LENGTH_SHORT).show();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<DataClass>> call, Throwable t) {
//
//            }
//        });
//














        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

               API api = retrofit.create(API.class);
               Call<ArrayList<Posts>> call = api.getPosts();
               call.enqueue(new Callback<ArrayList<Posts>>() {
                   @Override
                   public void onResponse(Call<ArrayList<Posts>> call, Response<ArrayList<Posts>> response) {
                       if(response.isSuccessful())
                       {
                           Toast.makeText(SignUp.this, "Data getting", Toast.LENGTH_SHORT).show();
                       }
                       Toast.makeText(SignUp.this, "Data  not getting", Toast.LENGTH_LONG).show();
                   }

                   @Override
                   public void onFailure(Call<ArrayList<Posts>> call, Throwable t) {
                       Toast.makeText(SignUp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                   }
               });


    }

    //Todo
    public void registerButtonClicked(View view) {
        // On Register data should be pushed


    }

    public void loginBtnSignUp(View view) {

        //Login Details


        // Intent intent = new Intent(this, Login.class);
        //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //startActivity(intent);
        //finish();
    }

    public void backBtnSignUp(View view) {
        // On back pressed the page is loaded is Dashboard
        Intent intent = new Intent(this, DashboardLogin.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}