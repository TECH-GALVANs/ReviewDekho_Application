package com.daiict.enterprizecomputing.reviewdekho.SignUp;

import com.google.gson.annotations.SerializedName;

public class Posts {
    private int userid;
    private int id;
    private String title;

    @SerializedName("body")
    private String body;

    public int getUserid() {
        return userid;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
