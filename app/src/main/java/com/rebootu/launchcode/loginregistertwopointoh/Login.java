package com.rebootu.launchcode.loginregistertwopointoh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class Login extends AppCompatActivity {

    public static final String LOGTAG = "Login.java";

    EditText etUsername, etPassword;
    TextView tvDisplayText;
    Button bRegisterHere;
    UserLocalStorage uls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOGTAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvDisplayText = (TextView) findViewById(R.id.tvDisplayText);
        bRegisterHere = (Button) findViewById(R.id.bRegisterHere);

        uls = new UserLocalStorage(this);
    }

    @Override
    public void onStart() {
        Log.i(LOGTAG, "onStart()");
        super.onStart();

        if(uls.isUserLoggedIn()) {
            bRegisterHere.setText("Override currently saved user!");
        }
    }

    public void login(View view){
        Log.i(LOGTAG, "login(View view)");

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(uls.isUserLoggedIn()){
//        if(uls.isUserDataStored()) {
            if ((!username.matches("") && !password.matches(""))) {
                User user = new User(username, password);
                if (uls.doesUserInputMatchStored(user)) {
                    Log.i(LOGTAG, uls.getLoggedInUser().toString());
                    uls.setUserLoggedIn(true);
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    tvDisplayText.setText("Incorrect username or password!");
                }
            } else {
                tvDisplayText.setText("Please fill out the fields!");
            }
        } else {
            tvDisplayText.setText("There is no user data stored right now! Register now! :D");
        }
    }

    public void registerHere(View view) {
        Log.i(LOGTAG, "registerHere(View view)");

        startActivity(new Intent(this, Register.class));
    }
}
