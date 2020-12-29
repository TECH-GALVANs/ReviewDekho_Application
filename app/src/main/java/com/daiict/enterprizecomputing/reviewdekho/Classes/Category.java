package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Category {
    @SerializedName("category_id")
    private int categoryId;

    @SerializedName("category_name")
    private String categoryName;

    @SerializedName("category_created_at")
    private Timestamp categoryCreatedAt;

    @SerializedName("category_updated_at")
    private  Timestamp categoryUpdatedAt;

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public Timestamp getCategoryCreatedAt() {
        return categoryCreatedAt;
    }

    public Timestamp getCategoryUpdatedAt() {
        return categoryUpdatedAt;
    }

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
    public Category()
    {

    }
}
