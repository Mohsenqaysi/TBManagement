package com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by mohsenqaysi on 2/2/17.
 */
@IgnoreExtraProperties
public class PatientsDataObject {

    private String name;
    private String email;

    public PatientsDataObject() {
        // Default constructor required for calls to DataSnapshot.getValue(PatientsDataObject.class)
    }

    public PatientsDataObject(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
