package com.example.mohsenqaysi.tbmanagement.PatientsDetails;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohsenqaysi.tbmanagement.Helper.RoundedTransformation;
import com.example.mohsenqaysi.tbmanagement.R;
import com.example.mohsenqaysi.tbmanagement.ViewInfoDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PatientDetails extends AppCompatActivity {

    private String image;
    private String fullName;
    private String dataOfBirth;
    private String stage;
    private ImageView patient_Iamge;
    private TextView patient_fullName;
    private TextView patient_DataOfBirht;
    private TextView patient_stage;
    private TextView patient_drugName;
    private TextView patient_startDate;
    private TextView patient_endDate;
    private TextView patient_schedule;
    private ImageButton info;

    private FloatingActionButton addDrugInfo;
    // init fire-base
    private RecyclerView drugsList;
    private DatabaseReference mDatabase;
    private String FIREBASE_URL_PATH_VISITS = "https://tbmanagement-aff8e.firebaseio.com/PatientsVisits";
    private FirebaseAuth mAuth;


    private String currentChild_pushKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details);

        addDrugInfo = (FloatingActionButton) findViewById(R.id.FloatingActionButtonPatientDetails_ID);
        addDrugInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), DrungsinfoAndDates.class));
            }
        });

        // BottomNavigationView Buttons
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_ID);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                Toast.makeText(getApplicationContext(),"action_home",Toast.LENGTH_LONG).show();
//                                startActivity(new Intent(getApplicationContext(), PatientDetails.class));
                                return  true;
                            case R.id.action_graph:
                                Toast.makeText(getApplicationContext(),"action_graph",Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getApplicationContext(), DrungsinfoAndDates.class));
                                return  true;
                        }
                        return true;
                    }
                });


        patient_Iamge = (ImageView) findViewById(R.id.patientsDetails_Image_ID);
        patient_fullName = (TextView) findViewById(R.id.patientDetails_fullName_ID);
        patient_DataOfBirht = (TextView) findViewById(R.id.patientDetails_BirthDay_ID);
        patient_stage = (TextView) findViewById(R.id.patientDetails_Stage_ID);
        info = (ImageButton) findViewById(R.id.infoImageButton_ID);

         image =  getIntent().getExtras().getString("image");
        fullName = getIntent().getExtras().getString("fullName");
        dataOfBirth = getIntent().getExtras().getString("dataOfBirth");
        stage = getIntent().getExtras().getString("stage");
        currentChild_pushKey = getIntent().getExtras().getString("currentChild_pushKey");
        Log.e("currentChild_pushKey: ",currentChild_pushKey);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // show the patient info on a dialog box
                ViewInfoDialog alert = new ViewInfoDialog();

//                fullAddress
                alert.showDialog(PatientDetails.this, image, fullName
                        ,getIntent().getExtras().getString("phoneNumber")
                        ,getIntent().getExtras().getString("fullAddress"));
            }
        });

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
        patient_drugName = (TextView) findViewById(R.id.drugName_ID);
        patient_startDate = (TextView) findViewById(R.id.startDateValue_ID);
        patient_endDate =  (TextView) findViewById(R.id.endDateValue_ID);
        patient_schedule = (TextView) findViewById(R.id.scheduleDateValue_ID);
        Log.w("satge: " ,stage);


        // TODO: Read
        // TODO: Update in user info and store into in FireBase
    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fristResponder = mAuth.getCurrentUser();
        String ID = fristResponder.getUid();
        Log.e("PatientFragment_ID: ", ID);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH_VISITS).child(ID).child(currentChild_pushKey).child("visit");
        mDatabase.keepSynced(true);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                patient_drugName.setText(dataSnapshot.child("drungNmae").getValue().toString());
                patient_startDate.setText(dataSnapshot.child("startDate").getValue().toString());
                patient_schedule.setText(dataSnapshot.child("schedule").getValue().toString());
                patient_endDate.setText(dataSnapshot.child("endDate").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                patient_drugName.setText(R.string.No_date);
                patient_startDate.setText(R.string.No_date);
                patient_schedule.setText(R.string.No_date);
                patient_endDate.setText(R.string.No_date);
            }
        });
    }

}
