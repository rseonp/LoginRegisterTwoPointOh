package com.rebootu.launchcode.loginregistertwopointoh;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    public static final String LOGTAG = "MainActivity.java";

    EditText etName, etAge, etUsername;
    UserLocalStorage uls;

//    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOGTAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);

//        sp = getPreferences(MODE_PRIVATE);
        uls = new UserLocalStorage(this);
    }

    @Override
    public void onStart() {
        Log.i(LOGTAG, "onStart()");
        super.onStart();

        if(authenticate()) {
            displayUserDetails();
        } else{
            startActivity(new Intent(this, Login.class));
        }
    }

    public boolean authenticate() {
        Log.i(LOGTAG, "authenticate()");

        return uls.isUserLoggedIn();
    }

    public void displayUserDetails() {
        Log.i(LOGTAG, "displayUserDetails()");

        User user = uls.getLoggedInUser();
        etUsername.setText(user.username);
        etName.setText(user.name);
        etAge.setText(user.age + "");
    }

    public void logout(View view){
        Log.i(LOGTAG, "logout(View view)");

//        uls.clearUserData();
//        uls.setUserLoggedIn(false);

        startActivity(new Intent(this, Login.class));
    }
}
