package com.example.mohsenqaysi.tbmanagement;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by mohsenqaysi on 2/2/17.
 */
@IgnoreExtraProperties
public class User {

    public String username_ID;
    public String email;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userID, String email) {
        this.username_ID = userID;
        this.email = email;
    }
}
