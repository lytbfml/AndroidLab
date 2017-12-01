package com.a388hw.yangxiao.notenner.user;

import com.google.firebase.database.DatabaseReference;

/**
 * @author lytbf on 11/30/2017.
 */

public class User {

    private final DatabaseReference databaseReference;
    private final String email;

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    public String getEmail() {
        return email;
    }

    public User(String email, DatabaseReference databaseReference)
    {
        this.email = email;
        this.databaseReference = databaseReference;
    }
}
