package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class PatientsDetails extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details);

//        Intent intent = new Intent(getContext(), PatientsDetails.class);
//        intent.putExtra("image",model.getImage());
//        intent.putExtra("fullName", model.getFullName());
//        intent.putExtra("dataOfBirth",model.getDateOfBirth());
//        intent.putExtra("gender", model.getGender());
//        intent.putExtra("phoneNumber",model.getPhoneNumber());
//        intent.putExtra("satge", model.getStageDiagnosis());
//        intent.putExtra("flatNumber", model.getFlatNumber());
//        intent.putExtra("address",model.getAddress());
//        intent.putExtra("city", model.getCity());
//        intent.putExtra("area", model.getArea());
//        intent.putExtra("postCode",model.getPostalCode());
        String image =  getIntent().getExtras().getString("image");



        Log.w("image: " ,image.toString());


        // TODO: Take in user info and store into in FireBase
        // TODO: Create an object class to send it to FireBase


        // TODO: Update in user info and store into in FireBase

    }
}
