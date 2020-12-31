package com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection;

import com.daiict.enterprizecomputing.reviewdekho.Classes.AddReviewClassData;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentSend;
import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentsClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.LikeClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Product;
import com.daiict.enterprizecomputing.reviewdekho.Classes.ReportClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SubCategory;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.SignUp.SignupClass;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @POST("user")
    Call<SignupClass> createUser(@Body SignupClass signupClass);

    //Testing purpose
//    @GET("posts")
//       Call<ArrayList<Posts>>  getPosts();

    @GET("user/emailid/{email}")
    Call<UserDataClass> getUserData(@Path("email") String email_id);

    @GET("review")
    Call<ArrayList<UserReviewClass>> getReviews();

    @GET("category")
    Call<ArrayList<Category>> getCategory();

    @GET("review/user/{id}")
    Call<ArrayList<UserReviewClass>> getReviewsById(@Path("id") int userid);

    @GET("review/category/{id}")
    Call<ArrayList<UserReviewClass>> getReviewsByCategoryId(@Path("id") int categoryid);


    @GET("sub-category/category/{id}")
    Call<ArrayList<SubCategory>> getSubCategoryByID(@Path("id") int categoryid);

    @GET("product/sub_category/{id}")
    Call<ArrayList<Product>> getProductsById(@Path("id") int sub_categoryId);

    @GET("comment/review/{id}")
    Call<ArrayList<CommentsClass>> getCommentsByReviewId(@Path("id") int reviewId);

    @POST("comment")
    Call<CommentSend> createComment(@Body CommentSend commentSend);

    @POST("like")
    Call<LikeClass> doLike(@Body LikeClass likeClass);

    @POST("report")
    Call<ReportClass> doReport(@Body ReportClass reportClass);

    @POST("review")
    Call<AddReviewClassData> reportReview(@Body AddReviewClassData addReview);



}
