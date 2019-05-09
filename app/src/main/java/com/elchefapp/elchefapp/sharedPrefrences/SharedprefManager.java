package com.elchefapp.elchefapp.sharedPrefrences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.elchefapp.elchefapp.models.models_chef.login_chef.ClassChefLoginData;
import com.elchefapp.elchefapp.models.models_client.login.ClassCustomerLoginData;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.gson.Gson;

import static com.elchefapp.elchefapp.sharedPrefrences.PreferencesUtility.LOGGED_IN_PREF;

public class SharedprefManager {
    private static final String SHARED_PREF_NAME = "my_shared_preff";
    private static SharedprefManager mInstance;
    private Context mCtx;

    private SharedprefManager(Context mCtx) {
        this.mCtx = mCtx;
    }
    public static synchronized SharedprefManager getInstance(Context mCtx) {

        if (mInstance == null) {
            mInstance = new SharedprefManager(mCtx);
        }
        return mInstance;
    }
    public void saveUserData(ClassCustomerLoginData data) {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }
    public void saveUser(GoogleSignInAccount  account) {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(account);
        prefsEditor.putString("user", json);
        prefsEditor.commit();
    }
    public ClassCustomerLoginData getUserData() {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("user", "");
        ClassCustomerLoginData obj = gson.fromJson(json, ClassCustomerLoginData.class);
        return obj;
    }
    public GoogleSignInAccount getUser() {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("user", "");
        GoogleSignInAccount obj = gson.fromJson(json, GoogleSignInAccount.class);
        return obj;
    }
    public void saveChefData(ClassChefLoginData data) {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(data);
        prefsEditor.putString("chef", json);
        prefsEditor.commit();
    }
    public void saveChef(GoogleSignInAccount account) {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(account);
        prefsEditor.putString("chef", json);
        prefsEditor.commit();
    }
    public ClassChefLoginData getChefData() {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("chef", "");
        ClassChefLoginData obj = gson.fromJson(json, ClassChefLoginData.class);
        return obj;
    }
    public GoogleSignInAccount getChef() {
        SharedPreferences mPrefs = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = mPrefs.getString("chef", "");
        GoogleSignInAccount obj = gson.fromJson(json, GoogleSignInAccount.class);
        return obj;
    }

    /*  ------------------ keep user login --------------------  */

    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Login Status
     * @param context
     * @param loggedIn
     */
    public static void setLoggedIn(Context context, boolean loggedIn) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putBoolean(LOGGED_IN_PREF, loggedIn);
        editor.apply();
    }

    /**
     * Get the Login Status
     * @param context
     * @return boolean: login status
     */
    public static boolean getLoggedStatus(Context context) {
        return getPreferences(context).getBoolean(LOGGED_IN_PREF, false);
    }


}
