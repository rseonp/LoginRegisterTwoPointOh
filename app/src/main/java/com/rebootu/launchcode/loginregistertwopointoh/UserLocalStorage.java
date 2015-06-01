package com.rebootu.launchcode.loginregistertwopointoh;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Victor on 5/28/15.
 */
public class UserLocalStorage {

    public static final String LOGTAG = "UserLocalStorage.java";
    public static final String SP_NAME = "userDetails";
    SharedPreferences sp;

    public UserLocalStorage(Context context) {
        sp = context.getSharedPreferences(SP_NAME, 0);

    }

    public void storeUserData(User user) {
        Log.i(LOGTAG, "storeUserData(User user)");

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name", user.name);
        editor.putInt("age", user.age);
        editor.putString("username", user.username);
        editor.putString("password", user.password);
        editor.commit();
    }

    public User getLoggedInUser() {
        Log.i(LOGTAG, "getLoggedInUser()");

        String name = sp.getString("name", "Name not found");
        int age = sp.getInt("age", 0);
        String username = sp.getString("username", "Username not found");
        String password = sp.getString("password", "Password not found");

        User loggedInUser = new User(name, age, username, password);

        return loggedInUser;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        Log.i(LOGTAG,"setUserLoggedIn(boolean loggedIn)");

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("loggedIn", loggedIn);
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        Log.i(LOGTAG, "isUserLoggedIn()");

        if(sp.getBoolean("loggedIn", false)) {
            return true;
        } else{
            return false;
        }
    }

//    public boolean isUserDataStored() {
//        Log.i(LOGTAG, "isUserDataStored()");
//
//        if(sp.getBoolean("loggedIn", false)) {
//            return true;
//        } else{
//            return false;
//        }
//    }

    public boolean doesUserInputMatchStored(User user){
        Log.i(LOGTAG, "doesUserInputMatchStored(User user)");

        if(user.username.equals(sp.getString("username", "Username not found")) &&
                user.password.equals(sp.getString("password", "Password not found"))){
            return true;
        }else {
            return false;
        }
    }

    public void clearUserData() {
        Log.i(LOGTAG, "clearUserData()");

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
