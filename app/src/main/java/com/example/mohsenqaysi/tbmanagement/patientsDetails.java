package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.Helper.RoundedTransformation;
import com.squareup.picasso.NetworkPolicy;
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

        // if the images are not alread saved offline load them
        Picasso.with(getApplicationContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().resize(440,440).
                transform(new RoundedTransformation(300, 40)).placeholder(R.drawable.profileplcaeholder).into(patient_Iamge,
                new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.w("Status: ", "Success!");
                    }
                    @Override
                    public void onError() {
                        Log.w("Error!", "The images are not loaded");
                        Picasso.with(getApplicationContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().resize(440,440).
                                transform(new RoundedTransformation(300, 40)).placeholder(R.drawable.profileplcaeholder).into(patient_Iamge);
                    }
                });

        patient_fullName.setText(fullName);
        patient_DataOfBirht.setText("Date of birth: "+ dataOfBirth);
        patient_stage.setText("Stage: " + stage);




        Log.w("satge: " ,stage);

    }
}
