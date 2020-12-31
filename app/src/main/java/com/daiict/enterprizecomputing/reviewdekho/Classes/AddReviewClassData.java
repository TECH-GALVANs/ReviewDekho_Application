package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

public class AddReviewClassData {
    @SerializedName("review_desc")
    private String desc;

    @SerializedName("product")
    private Product product;

    @SerializedName("user")
    private UserDataClass user;

    public AddReviewClassData(String desc, Product product, UserDataClass user) {
        this.desc = desc;
        this.product = product;
        this.user = user;
    }
}
