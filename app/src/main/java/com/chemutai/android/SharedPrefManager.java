package com.chemutai.android;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPrefManager {
    private static Context mCtx;

    private static final String SHARED_PREF_NAME ="mySharedPref";
    private static final String KEY_NAME = "name";
    private static final String KEY_PHONE_NO = "phone_no";
    private static final String KEY_NATIONAL_ID = "national_id";
    private static final String KEY_USER_ID = "user_id";
    private SharedPrefManager(Context context) {
        mCtx = context;

    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (sharedPrefManager == null) {
            sharedPrefManager = new SharedPrefManager(context);
        }
        return sharedPrefManager;
    }

    public boolean userLogin(int user_id, String name, String phone_no, String national_id){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_USER_ID, user_id);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_PHONE_NO, phone_no);
        editor.putString(KEY_NATIONAL_ID, national_id);
        editor.apply();

        return true;
    }

    public boolean isLogin(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        if (sharedPreferences.getString(KEY_PHONE_NO, null) != null){
            return true;
        }
        return false;
    }

    public boolean logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;//move all the values from the editor and save it
    }

    public String getPhoneNo(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PHONE_NO,null);
    }


    private static SharedPrefManager sharedPrefManager;
}
