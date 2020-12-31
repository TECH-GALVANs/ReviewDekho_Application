package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

public class ReportClass {
    @SerializedName("report_description")
    private String creport_desc;

    @SerializedName("user")
    private UserDataClass user;

    @SerializedName("review")
    private UserReviewClass review;

    public ReportClass(String comment_desc, UserDataClass user, UserReviewClass review) {
        this.creport_desc = comment_desc;
        this.user = user;
        this.review = review;
    }

}
