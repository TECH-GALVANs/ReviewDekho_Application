package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daiict.enterprizecomputing.reviewdekho.Classes.Product;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SubCateory;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.sql.Timestamp;
import java.util.ArrayList;

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
        UserDataClass userDataClass = new UserDataClass(reviewClass.getUserData());
        holder.textViewName.setText(userDataClass.getEmailID());

        Product product = reviewClass.getProduct();
       SubCateory subCateory = product.getSubCateory();
//        Category category = subCateory.getCategory();

       // holder.textViewCategory.setText(category.getCategoryName());
//        holder.textViewSubCategory.setText(subCateory.getSubCategoryName());
        Timestamp timestamp = reviewClass.getReviewAddedAt();
        holder.textViewTime.setText(timestamp.toString());
        holder.textViewDetails.setText(reviewClass.getReviewDescription());


    }

    @Override
    public int getItemCount() {
        return reviewData.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName,textViewCategory,textViewSubCategory,textViewTime,textViewDetails;
        Button btnLike,btnComment,btnReport;
        boolean stateLike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sharedPrefManager = new SharedPrefManager(activity);

            textViewName = itemView.findViewById(R.id.feed_frag_name);
            textViewCategory = itemView.findViewById(R.id.feed_frag_category);
            textViewSubCategory = itemView.findViewById(R.id.subcategory);
            textViewTime = itemView.findViewById(R.id.card_home_date);
            textViewDetails = itemView.findViewById(R.id.feed_frag_details);

            //Button Hooks
//            btnLike = itemView.findViewById(R.id.card_feed_like);
//            btnComment= itemView.findViewById(R.id.card_feed_comment);
//            btnReport = itemView.findViewById(R.id.card_feed_report);


//            if(sharedPrefManager.getRolePreference()==5)
//            {
//                btnLike.setVisibility(View.INVISIBLE);
//                btnReport.setVisibility(View.INVISIBLE);
//            }
//            else{
//                btnLike.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        sendLikeData();
//                       btnLike.setClickable(false);
//                       btnLike.setBackgroundColor(R.color.red);
//
//                    }
//                });
//
//                btnComment.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(activity, CommentsView.class);
//                        //intent.putExtra("Fragment", "profilefragment");
//                        activity.startActivity(intent);
//                    }
//                });
//
//                btnReport.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AlertDialog.Builder Builder = new AlertDialog.Builder(activity);
//                        View view_pop = getLayoutInflater().inflate(R.layout.popup_logout, null);
//
//                        final Button confirm, cancel;
//                        //hooks
//                        confirm = view_pop.findViewById(R.id.settings_pop_btn_confirm_logout);
//                        cancel = view_pop.findViewById(R.id.settings_pop_btn_reject_logout);
//
//                        //setting the view
//                        Builder.setView(view_pop);
//                        final Dialog dialog = Builder.create();
//                        dialog.show();

//                        sendReportData();
//                        btnReport.setClickable(false);
//                    }
//                });
//            }



        }
    }

    private void sendReportData() {

    }

    private void sendLikeData()
    {

    }
}
