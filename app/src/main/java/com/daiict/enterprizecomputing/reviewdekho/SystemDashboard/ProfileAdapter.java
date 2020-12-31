package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserDataClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.R;

import org.w3c.dom.Text;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    //Variables
    private ArrayList<UserReviewClass> reviewData;
    private Activity activity;

    public ProfileAdapter(Activity activity, ArrayList<UserReviewClass> dataItems)
    {
        this.activity = activity;
        this.reviewData = dataItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Assign the created Layout over here.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_profile,parent,false);
        return new ViewHolder(view);
        //Return new ViewHolder
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserReviewClass reviewClass = reviewData.get(position);
        holder.name.setText(reviewClass.getUserData().getUsername());
        Timestamp timestamp = reviewClass.getReviewAddedAt();
        //Date Formatter
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date((timestamp.getTime())));
        holder.date.setText(dateString);

        holder.desc.setText(reviewClass.getReviewDescription());

        byte[] decodedString = Base64.decode(reviewClass.getProduct().getImage(), Base64.DEFAULT);
        Glide.with(activity).asBitmap().load(decodedString).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);


        byte[] decodedStringProfile = Base64.decode(reviewClass.getProduct().getImage(), Base64.DEFAULT);
        Glide.with(activity).asBitmap().load(decodedStringProfile).circleCrop().into(holder.circleImageView);
    }

    @Override
    public int getItemCount() {
        return reviewData.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        de.hdodenhof.circleimageview.CircleImageView circleImageView;
        ImageView imageView;
        TextView desc;
        TextView name;
        TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            desc = itemView.findViewById(R.id.desc_profile);
            name = itemView.findViewById(R.id.card_name);
            date = itemView.findViewById(R.id.card_date);
            circleImageView = itemView.findViewById(R.id.card_circleImageView);
        }
    }
}
