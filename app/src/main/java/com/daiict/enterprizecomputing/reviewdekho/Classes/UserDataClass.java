package com.daiict.enterprizecomputing.reviewdekho.Classes;

import com.google.gson.annotations.SerializedName;

public class UserDataClass {
    @SerializedName("user_id")
    private int user_id;

    @SerializedName("email_id")
    private String emailID;

    @SerializedName("user_name")
    private String username;

    @SerializedName("password")
    private String accPass;

    @SerializedName("user_role")
    private int userRole;

    public UserDataClass(String emailID, String username, String accPass, int userRole) {
        this.emailID = emailID;
        this.username = username;
        this.accPass = accPass;
        this.userRole = userRole;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccPass() {
        return accPass;
    }

    public void setAccPass(String accPass) {
        this.accPass = accPass;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }

}
