package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.Classes.Product;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SubCategory;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.Comments.CommentsView;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterUserReviewDisplay extends RecyclerView.Adapter<AdapterUserReviewDisplay.ViewHolder> {
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
            else{
                btnLike.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendLikeData();
                       btnLike.setClickable(false);
                      // btnLike.setBackgroundColor();

                    }
                });

                btnComment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(activity, CommentsView.class);
                        //intent.putExtra("Fragment", "profilefragment");
                        activity.startActivity(intent);
                    }
                });

                btnReport.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
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
                               dialog.cancel();
//                                sendReportData();
//                                btnReport.setClickable(false);
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
    }

    private void sendReportData() {

    }

    private void sendLikeData()
    {

    }
}
