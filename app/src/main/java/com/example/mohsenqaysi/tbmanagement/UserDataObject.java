package com.example.mohsenqaysi.tbmanagement;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by mohsenqaysi on 2/2/17.
 */
@IgnoreExtraProperties
public class UserDataObject {

    public String name;
    public String email;

    public UserDataObject() {
        // Default constructor required for calls to DataSnapshot.getValue(UserDataObject.class)
    }

    public UserDataObject(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
