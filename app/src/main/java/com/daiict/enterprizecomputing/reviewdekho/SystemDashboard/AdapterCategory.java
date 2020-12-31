package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daiict.enterprizecomputing.reviewdekho.Classes.Category;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.ArrayList;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    private Activity activity;
    SharedPrefManager sharedPrefManager ;

    public AdapterCategory(Activity activity_context,ArrayList<Category> categoryArrayList)
    {
        this.activity = activity_context;
        this.categoryArrayList = categoryArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Assign the created Layout over here.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ccardview_getcategory,parent,false);
        return new ViewHolder(view);
        //Return new ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
          //Create a object of Category Class
          Category category= categoryArrayList.get(position);

        //Assign values
        //Glide.with(activity).load(dataItem.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
        holder.category.setText(category.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.textview_category_name);
           itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Category categoryObj = new Category();
            Intent intent = new Intent(activity,CategoryView.class);
            sharedPrefManager = new SharedPrefManager(activity);
            sharedPrefManager.setCatId((categoryArrayList.get(getAdapterPosition()).getCategoryId()));
            //intent.putExtra("Fragment", "profilefragment");
           // categoryObj = categoryArrayList.get(getAdapterPosition());
            intent.putExtra("Category_Name",(categoryArrayList.get(getAdapterPosition()).getCategoryName()));
            //intent.putExtra("Category_Id",categoryObj.getCategoryId());
            activity.startActivity(intent);
        }
    }
}
