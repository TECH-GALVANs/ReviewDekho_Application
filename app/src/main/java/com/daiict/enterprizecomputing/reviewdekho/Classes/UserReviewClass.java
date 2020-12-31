package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

//Creating POJO Class for Reviews
public class UserReviewClass {
    @SerializedName("review_id")
    private int reviewID;

    @SerializedName("review_desc")
    private String reviewDescription;

    @SerializedName("review_added_at")
    private Timestamp reviewAddedAt;

    @SerializedName("review_updated_at")
    private Timestamp reviewUpdatedAt;

    @SerializedName("user")
    private  UserDataClass userData;

    @SerializedName("product")
    private Product product;

    public UserReviewClass(int reviewID) {
        this.reviewID = reviewID;
    }

    public int getReviewID() {
        return reviewID;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public Timestamp getReviewAddedAt() {
        return reviewAddedAt;
    }

    public Timestamp getReviewUpdatedAt() {
        return reviewUpdatedAt;
    }

    public UserDataClass getUserData() {
        return userData;
    }

    public Product getProduct() {
        return product;
    }
}
