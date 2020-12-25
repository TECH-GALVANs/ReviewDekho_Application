package com.daiict.enterprizecomputing.reviewdekho.SignUp;

import android.os.health.TimerStat;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class SignupClass {
    private int user_id;

    @SerializedName("email_id")
    private String emailID;

    @SerializedName("user_name")
    private String username;

    @SerializedName("password")
    private String accPass;



    public SignupClass(String emailID, String username, String accPass) {
        this.emailID = emailID;
        this.username = username;
        this.accPass = accPass;
    }

}
