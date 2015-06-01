package com.rebootu.launchcode.loginregistertwopointoh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class Register extends AppCompatActivity {

    public static final String LOGTAG = "Register.java";

    EditText etName, etAge, etUsername, etPassword;
    TextView tvDisplayText;

    UserLocalStorage uls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(LOGTAG, "onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etAge = (EditText) findViewById(R.id.etAge);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        tvDisplayText = (TextView) findViewById(R.id.tvDisplayText);

        uls = new UserLocalStorage(this);
    }

    public void register(View view){
        Log.i(LOGTAG, "register(View view)");

        if(etAge.getText().toString().matches("")||!etAge.getText().toString().matches("\\d+")){
            tvDisplayText.setText("Please fill out all the fields correctly!");
            return;
        }else {
            String name = etName.getText().toString();
            int age = Integer.parseInt(etAge.getText().toString());
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (name.matches("") || username.matches("") || password.matches("")) {
                tvDisplayText.setText("Please fill out all the fields!");
            } else {
                if(age == 0 || age > 110){
                    tvDisplayText.setText("That age doesn't seem right...");
                    return;
                }
                User user = new User(name, age, username, password);

                Log.i(LOGTAG, user.toString());

                uls.storeUserData(user);
                uls.setUserLoggedIn(true);

                startActivity(new Intent(this, MainActivity.class));
            }
        }
    }

    public void goBack(View view) {
        Log.i(LOGTAG, "goBack(View view)");

        startActivity(new Intent(this, Login.class));
    }
}
