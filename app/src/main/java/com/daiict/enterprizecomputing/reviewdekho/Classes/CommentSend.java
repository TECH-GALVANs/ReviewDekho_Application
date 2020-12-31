package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

public class CommentSend {
    @SerializedName("comment_desc")
    private String comment_desc;

    @SerializedName("user")
    private UserDataClass user_id;

    @SerializedName("review")
    private UserReviewClass review_id;

    public CommentSend(String comment_desc, UserDataClass user_id, UserReviewClass review_id) {
        this.comment_desc = comment_desc;
        this.user_id = user_id;
        this.review_id = review_id;
    }
}
