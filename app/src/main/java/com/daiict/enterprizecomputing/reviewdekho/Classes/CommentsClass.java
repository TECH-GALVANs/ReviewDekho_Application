package com.daiict.enterprizecomputing.reviewdekho.Classes;

import android.service.autofill.UserData;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class CommentsClass {

    @SerializedName("comment_id")
    private int commentId;

    @SerializedName("comment_desc")
    private String commentDesc;

    @SerializedName("comment_created_at")
    private Timestamp commentCreatedAt;

    @SerializedName("comment_updated_at")
    private Timestamp CommentUpdatedAt;

    @SerializedName("isDeleted")
    private boolean isDeleted;

    @SerializedName("review")
    private  UserReviewClass userDataReview;

    @SerializedName("user")
    private UserDataClass userComment;


    public int getCommentId() {
        return commentId;
    }

    public String getCommentDesc() {
        return commentDesc;
    }

    public Timestamp getCommentCreatedAt() {
        return commentCreatedAt;
    }

    public Timestamp getCommentUpdatedAt() {
        return CommentUpdatedAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public UserReviewClass getUserDataReview() {
        return userDataReview;
    }

    public UserDataClass getUserComment() {
        return userComment;
    }
}
