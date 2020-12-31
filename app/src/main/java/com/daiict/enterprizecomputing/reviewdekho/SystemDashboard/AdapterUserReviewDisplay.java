package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentSend;
import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentsClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.LikeClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Product;
import com.daiict.enterprizecomputing.reviewdekho.Classes.ReportClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SubCategory;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.Comments.CommentsView;
import com.daiict.enterprizecomputing.reviewdekho.DatabaseConnection.API;
import com.daiict.enterprizecomputing.reviewdekho.R;
import com.google.gson.Gson;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterUserReviewDisplay extends RecyclerView.Adapter<AdapterUserReviewDisplay.ViewHolder> {
    int var = -1;
    boolean status = false;
    boolean statusReport = false;
    int reviewid = -1;

    //Variables
    private ArrayList<UserReviewClass> reviewData;
    private Activity activity;
    SharedPrefManager sharedPrefManager;

    public AdapterUserReviewDisplay(Activity activity,ArrayList<UserReviewClass> userReviewClasses)
    {
        this.activity = activity;
        reviewData = userReviewClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Assign the created Layout over here.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_feed_recycler,parent,false);
        return new ViewHolder(view);
        //Return new ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserReviewClass reviewClass = reviewData.get(position);

        UserDataClass userDataClass = reviewClass.getUserData();
        holder.textViewName.setText(userDataClass.getUsername());

        holder.textViewCategory.setText(reviewClass.getProduct().getSubCateory().getCategory().getCategoryName());
        holder.textViewSubCategory.setText(reviewClass.getProduct().getSubCateory().getSubCategoryName());

        Timestamp timestamp = reviewClass.getReviewAddedAt();
        //Date Formatter
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date((timestamp.getTime())));

        holder.textViewTime.setText(dateString);
        holder.textViewDetails.setText(reviewClass.getReviewDescription());


        byte[] decodedString = Base64.decode(reviewClass.getProduct().getImage(), Base64.DEFAULT);
        Glide.with(activity).asBitmap().load(decodedString).into(holder.imageView);

        byte[] decodedStringProfile = Base64.decode(reviewClass.getProduct().getImage(), Base64.DEFAULT);
        Glide.with(activity).asBitmap().load(decodedStringProfile).circleCrop().into(holder.imageViewProfile);


    }

    @Override
    public int getItemCount() {
        return reviewData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName,textViewCategory,textViewSubCategory,textViewTime,textViewDetails;
        ImageView imageView;
        ImageView btnLike,btnComment,btnReport;
        de.hdodenhof.circleimageview.CircleImageView imageViewProfile;
        boolean stateLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sharedPrefManager = new SharedPrefManager(activity);

            textViewName = itemView.findViewById(R.id.feed_frag_name);
            textViewCategory = itemView.findViewById(R.id.feed_frag_category);
            textViewSubCategory = itemView.findViewById(R.id.subcategory);
            textViewTime = itemView.findViewById(R.id.card_home_date);
            textViewDetails = itemView.findViewById(R.id.feed_frag_details);

            imageViewProfile = itemView.findViewById(R.id.circleImageView);
            var = getAdapterPosition();





            //Image
            imageView = itemView.findViewById(R.id.feed_frag_image);

            //Button Hooks
               btnLike = itemView.findViewById(R.id.card_feed_like);
               btnComment= itemView.findViewById(R.id.card_feed_comment);
               btnReport = itemView.findViewById(R.id.card_feed_report);


            if(sharedPrefManager.getRolePreference()==5)
            {
                btnLike.setVisibility(View.INVISIBLE);
                btnReport.setVisibility(View.INVISIBLE);
            }

                btnLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                       btnLike.setClickable(false);
                      // btnLike.setBackgroundColor();
                        var = getAdapterPosition();
                        sendLikeData();
                        if(status)
                        {
                            btnLike.setColorFilter(ContextCompat.getColor(activity,R.color.red));
                        }

                    }
                });

                btnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, CommentsView.class);
                        //intent.putExtra("Fragment", "profilefragment");
                        //Toast.makeText(activity, ""+reviewData.get(getAdapterPosition()).getReviewID(), Toast.LENGTH_SHORT).show();
                         intent.putExtra("review_id",reviewData.get(getAdapterPosition()).getReviewID());
                         intent.putExtra("desc",reviewData.get(getAdapterPosition()).getReviewDescription());
                          activity.startActivity(intent);
                    }
                });

                btnReport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       // sendReportData();
                        reviewid   = reviewData.get(getAdapterPosition()).getReviewID();
                        AlertDialog.Builder Builder = new AlertDialog.Builder(activity);
                        View view_pop = LayoutInflater.from(activity).inflate(R.layout.report_view, null);

                         Button confirm, cancel;
                        //hooks
                        confirm = view_pop.findViewById(R.id.pop_up_report_report);
                        cancel = view_pop.findViewById(R.id.pop_up_report_cancel);

                        //setting the view
                        Builder.setView(view_pop);
                        final Dialog dialog = Builder.create();
                        dialog.setCancelable(false);
                        dialog.show();



                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                sendReportData();
                                 //statusReport = true;
                                dialog.cancel();
                                //ReportData(int userID,int reviewID,String desc);
                            }
                        });

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });

                    }
                });
            }




    }

    private void sendReportData() {

        Intent intent = new Intent(activity, ReportView.class);
        //intent.putExtra("Fragment", "profilefragment");
        //Toast.makeText(activity, ""+reviewData.get(getAdapterPosition()).getReviewID(), Toast.LENGTH_SHORT).show();
        intent.putExtra("review_id",reviewid);
        activity.startActivity(intent);
    }

    private void sendLikeData()
    {
        databaseSendLike();
    }




    private void databaseSendLike() {
        LikeClass likeClass;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(sharedPrefManager.getBaseURL())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //TOdo

        try {
            UserDataClass userDataClass = new UserDataClass(sharedPrefManager.getUserId());
            UserReviewClass userReviewClass = new UserReviewClass(reviewData.get(var).getReviewID());
            likeClass = new LikeClass(userReviewClass,userDataClass);
            Log.e("data "," "+userDataClass.getUser_id()+" "+userReviewClass.getReviewID());


            API instanceofapi = retrofit.create(API.class);
            Call<LikeClass> call = instanceofapi.doLike(likeClass);
            call.enqueue(new Callback<LikeClass>() {
                @Override
                public void onResponse(Call<LikeClass> call, Response<LikeClass> response) {
                    if(response.isSuccessful()) {
                        Log.e("Data Updated", "done");
                        status = true;
                    }
                }

                @Override
                public void onFailure(Call<LikeClass> call, Throwable t) {
                    Log.e("Data not Updated ","failed");
                    // databaseConnection();
                }
            });

        } catch (Exception e) {

        }
    }
}
