package com.daiict.enterprizecomputing.reviewdekho.Classes;

import android.content.Context;
import android.content.SharedPreferences;

//This class is for data preference and shared preference

public class SharedPrefManager {
    public static final String PREFERENCE_NAME = "PREFERENCE_DATA";
    private final SharedPreferences sharedpreferencesData;

    //except commit use apply as it store data in background process

    // Role 5 is visitor

    public SharedPrefManager(Context context) {
        sharedpreferencesData = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    public void setUserId(int userid)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putInt("user_id", userid);
        editor.apply();
    }

    public void setRequestType(boolean requestReviewer)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putBoolean("request_reviewer", requestReviewer);
        editor.apply();
    }

    public void setUserName(String userName)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("user_name", userName);
        editor.apply();
    }

    public void setEmail(String email)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("email_id", email);
        editor.apply();
    }

    public void setAccPassword(String password)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("password", password);
        editor.apply();
    }

    public void setRolePreference(int role)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putInt("Role", role);
        editor.apply();
    }

    public void setBaseURL(String url_Test)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("URL", url_Test);
        editor.apply();
    }

    public void setImage(String image)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putString("user_image", image);
        editor.apply();
    }

    public String getImage()
    {
        return sharedpreferencesData.getString("user_image","");
    }

    public void setCatId(int catId)
    {
        SharedPreferences.Editor editor = sharedpreferencesData.edit();
        editor.putInt("category_id", catId);
        editor.apply();
    }

    public int getCatId(){return sharedpreferencesData.getInt("category_id",-1);}

    public String getBaseURL()
    {
        return sharedpreferencesData.getString("URL","http://192.168.0.134:9090/api/");
    }

    public boolean getRequestType()
    {
        return sharedpreferencesData.getBoolean("request_reviewer",false);
    }

    public int getUserId()
    {
       return sharedpreferencesData.getInt("user_id", -1);
    }

    public String getUserName()
    {
        return sharedpreferencesData.getString("user_name", "");
    }

    public String getEmail()
    {
        return sharedpreferencesData.getString("email_id", "");
    }
    public String getAccPassword()
    {
        return sharedpreferencesData.getString("password", "");
    }
    public int getRolePreference()
    {
        return sharedpreferencesData.getInt("Role", -1);
    }

}