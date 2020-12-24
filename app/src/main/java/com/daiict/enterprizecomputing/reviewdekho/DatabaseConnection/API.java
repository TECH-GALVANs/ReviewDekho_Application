package com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection;

import com.daiict.enterprizecomputing.reviewdekho.SignUp.DataClass;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.Posts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    @POST("user")
    Call<DataClass> createUser(@Body DataClass dataClass);

    @GET("posts")
       Call<ArrayList<Posts>>  getPosts();

    @GET("user")
    Call<ArrayList<DataClass>> getData();
}
