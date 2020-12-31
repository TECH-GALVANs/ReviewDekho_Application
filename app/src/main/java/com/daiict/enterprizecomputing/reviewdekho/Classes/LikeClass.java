package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

public class LikeClass {
    @SerializedName("review")
    private  UserReviewClass userDataReview;

    @SerializedName("user")
    private UserDataClass userComment;

    public LikeClass(UserReviewClass userDataReview, UserDataClass userComment) {
        this.userDataReview = userDataReview;
        this.userComment = userComment;
    }

}
