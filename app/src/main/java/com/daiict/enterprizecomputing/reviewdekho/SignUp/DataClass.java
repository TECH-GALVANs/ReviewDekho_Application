package com.daiict.enterprizecomputing.reviewdekho.SignUp;

import android.os.health.TimerStat;

import com.google.gson.annotations.SerializedName;

import java.sql.Timestamp;

public class DataClass {
    private int user_id;

    @SerializedName("email_id")
    private String emailID;

    @SerializedName("user_name")
    private String username;

    @SerializedName("password")
    private String accPass;

    @SerializedName("user_role")
    private int userrole;

    @SerializedName("user_created_at")
    private Timestamp usercreatedat;

    @SerializedName("user_updated_at")
    private Timestamp  userupdatedat;



    public DataClass(String emailID, String username, String accPass) {
        this.emailID = emailID;
        this.username = username;
        this.accPass = accPass;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getUsername() {
        return username;
    }

    public String getAccPass() {
        return accPass;
    }

    public int getUserid() {
        return user_id;
    }

    public int getUserrole() {
        return userrole;
    }

    public Timestamp getUsercreatedat() {
        return usercreatedat;
    }

    public Timestamp getUserupdatedat() {
        return userupdatedat;
    }
}
