package com.rebootu.launchcode.loginregistertwopointoh;

/**
 * Created by Victor on 5/28/15.
 */
public class User {

    public String name, username, password;
    public int age;

    public User(String name, int age, String username, String password) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String toString() {
        return name + " " + age + " " + username + " " + password;
    }
}
