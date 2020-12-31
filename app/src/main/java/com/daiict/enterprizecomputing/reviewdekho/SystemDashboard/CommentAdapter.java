package com.daiict.enterprizecomputing.reviewdekho.SystemDashboard;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daiict.enterprizecomputing.reviewdekho.Classes.CommentsClass;
import com.daiict.enterprizecomputing.reviewdekho.Classes.SharedPrefManager;
import com.daiict.enterprizecomputing.reviewdekho.Classes.UserReviewClass;
import com.daiict.enterprizecomputing.reviewdekho.R;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private ArrayList<CommentsClass> commentsClasses;
    private Activity activity;
    SharedPrefManager sharedPrefManager;

    public CommentAdapter(Activity activity,ArrayList<CommentsClass> commentsClasses)
    {
        this.activity = activity;
        this.commentsClasses = commentsClasses;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_comments,parent,false);
        sharedPrefManager = new SharedPrefManager(activity);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentsClass commentsClass_Data = commentsClasses.get(position);
//        holder.username.setText("Aakash");
        holder.comments.setText(commentsClass_Data.getCommentDesc());

    }

    @Override
    public int getItemCount() {
        return commentsClasses.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView username,comments;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.username);
            comments = itemView.findViewById(R.id.comments_Loaded);
        }
    }
}
