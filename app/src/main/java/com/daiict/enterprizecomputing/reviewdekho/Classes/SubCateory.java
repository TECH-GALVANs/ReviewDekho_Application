package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class SubCateory {
    @SerializedName("sub_category_id")
    private int subCategoryId;

    @SerializedName("sub_category_name")
    private String subCategoryName;

    @SerializedName("sub_category_created_at")
    private Timestamp subCreatedAt;

    @SerializedName("sub_category_updated_at")
    private  Timestamp subUpdatedAt;


    private  Category category;

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public Timestamp getSubCreatedAt() {
        return subCreatedAt;
    }

    public Timestamp getSubUpdatedAt() {
        return subUpdatedAt;
    }

    public Category getCategory() {
        return category;
    }
}
