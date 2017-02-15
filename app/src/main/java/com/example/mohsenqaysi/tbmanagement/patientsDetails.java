package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PatientsDetails extends AppCompatActivity {

    private String image;
    private String fullName;
    private String dataOfBirth;
    private String stage;
    private ImageView patient_Iamge;
    private TextView patient_fullName;
    private TextView patient_DataOfBirht;
    private TextView patient_stage;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details);

        patient_Iamge = (ImageView) findViewById(R.id.patientsDetails_Image_ID);
        patient_fullName = (TextView) findViewById(R.id.patientDetails_fullName_ID);
        patient_DataOfBirht = (TextView) findViewById(R.id.patientDetails_BirthDay_ID);
        patient_stage = (TextView) findViewById(R.id.patientDetails_Stage_ID);
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
         image =  getIntent().getExtras().getString("image");
        fullName = getIntent().getExtras().getString("fullName");
        dataOfBirth = getIntent().getExtras().getString("dataOfBirth");
        stage = getIntent().getExtras().getString("stage");
        Picasso.with(getApplicationContext()).load(image).centerCrop().resize(700, 700).

                placeholder(R.drawable.profileplcaeholder).into(patient_Iamge,
                new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.w("Status: ", "Success!");
                    }
                    @Override
                    public void onError() {
                        Log.w("Error!", "The images are not loaded");
                    }
                });
        patient_fullName.setText(fullName);
        patient_DataOfBirht.setText("Date of birth: "+ dataOfBirth);
        patient_stage.setText("Stage: " + stage);




        Log.w("satge: " ,stage);


        // TODO: Take in user info and store into in FireBase
        // TODO: Create an object class to send it to FireBase


        // TODO: Update in user info and store into in FireBase

    }
}
