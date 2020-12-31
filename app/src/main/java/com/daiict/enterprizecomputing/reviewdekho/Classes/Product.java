package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class Product {
    @SerializedName("product_id")
    private int productid;

    @SerializedName("subCategory")
    private SubCategory subCateory;

    @SerializedName("product_name")
    private String productName;

    @SerializedName("product_desc")
    private String productDesc;

    @SerializedName("product_price")
    private int productPrice;

    @SerializedName("image")
    private String image;


    @SerializedName("product_created_at")
    private Timestamp createdAt;

    @SerializedName("product_updated_at")
    private Timestamp updatedAt;

    public Product(int productid) {
        this.productid = productid;
    }

    public String getImage(){return image;}

    public int getProductid() {
        return productid;
    }

    public SubCategory getSubCateory() {
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
