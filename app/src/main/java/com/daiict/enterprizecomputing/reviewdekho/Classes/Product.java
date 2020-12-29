package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Product {
    @SerializedName("product_id")
    private int productid;

    private SubCateory subCateory;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("product_desc")
    private String productDesc;

    @SerializedName("product_price")
    private int productPrice;

    @SerializedName("product_created_at")
    private Timestamp createdAt;

    @SerializedName("product_updated_at")
    private Timestamp updatedAt;

    public int getProductid() {
        return productid;
    }

    public SubCateory getSubCateory() {
        return subCateory;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
