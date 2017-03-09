package com.example.mohsenqaysi.tbmanagement.PatientsDetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohsenqaysi.tbmanagement.Fragments.GraphFragment;
import com.example.mohsenqaysi.tbmanagement.Helper.RoundedTransformation;
import com.example.mohsenqaysi.tbmanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
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

    // init fire-base
    private RecyclerView drugsList;
    private DatabaseReference mDatabase;
    private String FIREBASE_URL_PATH_VISITS = "https://tbmanagement-aff8e.firebaseio.com/PatientsVisits";
    private FirebaseAuth mAuth;

    // Fragment
    private Fragment fragment;
    private FragmentManager fragmentManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details);


        fragmentManager = getSupportFragmentManager();
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
//                                startActivity(new Intent(getApplicationContext(), GeneralInfoAboutTBList.class));
                                 fragment = new GraphFragment();
                                break;
//                                return  true;
                        }
                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.patientFragment_ID, fragment).commit();
                        return true;
                    }
                });
//
//        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser fristResponder = mAuth.getCurrentUser();
//        String ID = fristResponder.getUid();
//        Log.e("PatientFragment_ID: ", ID);
//
//
//        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH_VISITS).child(ID).child("visit");
//        mDatabase.keepSynced(true);

        patient_Iamge = (ImageView) findViewById(R.id.patientsDetails_Image_ID);
        patient_fullName = (TextView) findViewById(R.id.patientDetails_fullName_ID);
        patient_DataOfBirht = (TextView) findViewById(R.id.patientDetails_BirthDay_ID);
        patient_stage = (TextView) findViewById(R.id.patientDetails_Stage_ID);

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


        // TODO: Read
        // TODO: Update in user info and store into in FireBase




    }
}
