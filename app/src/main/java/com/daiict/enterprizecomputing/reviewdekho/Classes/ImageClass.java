package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class ImageClass {

    @SerializedName("product")
    private int imageID;


    @SerializedName("product")
    private Product product;

    @SerializedName("product")
    private String image;

    @SerializedName("product")
    private Timestamp  createdAt;

    @SerializedName("product")
    private Timestamp updatedAt;


    public int getImageID() {
        return imageID;
    }

    public Product getProduct() {
        return product;
    }

    public String getImage() {
        return image;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
}
