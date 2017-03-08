package com.example.mohsenqaysi.tbmanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.Authatications.Login;
import com.example.mohsenqaysi.tbmanagement.CustomCardView.Patients;
import com.example.mohsenqaysi.tbmanagement.Helper.RoundedTransformation;
import com.example.mohsenqaysi.tbmanagement.PatientsDetails.PatientDetails;
import com.example.mohsenqaysi.tbmanagement.TB_General_Info.GeneralInfoAboutTBList;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class AdminActivity extends AppCompatActivity {

    Login login = new Login();
    private Toolbar toolbar;

    private RecyclerView mPatientList;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private String FIREBASE_URL_PATH_patientsList = "https://tbmanagement-aff8e.firebaseio.com/patientsList";
    private static Activity viewContext;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPatientList = (RecyclerView) findViewById(R.id.adminList_ID);

        // @param hasFixedSize true if adapter changes cannot affect the size of the RecyclerView.
        mPatientList.setHasFixedSize(true);
        mPatientList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fristResponder = mAuth.getCurrentUser();
        String ID = fristResponder.getUid();
        Log.e("PatientFragment_ID: ", ID);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH_patientsList);
        mDatabase.keepSynced(true);
    }

    @Override
    public void onStart() {
        super.onStart();


        // load the data when the app is started.
        FirebaseRecyclerAdapter<Patients, PatientsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Patients, PatientsViewHolder>(
                Patients.class,
                R.layout.patients_row, // this is a custom layout
                PatientsViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(PatientsViewHolder viewHolder, final Patients model, int position) {
                viewHolder.setName(model.getFullName());
                viewHolder.setStage(model.getStageDiagnosis());
//                viewHolder.setImage(model.getImage());
                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), PatientDetails.class);
                        intent.putExtra("image",model.getImage());
                        intent.putExtra("fullName", model.getFullName());
                        intent.putExtra("dataOfBirth",model.getDateOfBirth());
                        intent.putExtra("gender", model.getGender());
                        intent.putExtra("phoneNumber",model.getPhoneNumber());
                        intent.putExtra("stage", model.getStageDiagnosis());
                        intent.putExtra("flatNumber", model.getFlatNumber());
                        intent.putExtra("address",model.getAddress());
                        intent.putExtra("city", model.getCity());
                        intent.putExtra("area", model.getArea());
                        intent.putExtra("postCode",model.getPostalCode());
                        startActivity(intent);
                    }
                });

            }
        };

        mPatientList.setAdapter(firebaseRecyclerAdapter);
    }

    // this class is used to prepare the patients data loaded from Fire-base to be displayed into the
    // Fire-base Recycler Adapter
    public static class PatientsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public PatientsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String fullName) {
            TextView display_Name = (TextView) mView.findViewById(R.id.patient_fullname_ID);
            display_Name.setText(fullName);
        }

        public void setStage(String stage) {
            TextView display_Stage = (TextView) mView.findViewById(R.id.patient_stage_ID);
            display_Stage.setText("Stage: " + stage);
        }

        public void setImage(final String image) {

            final ImageView display_eImage = (ImageView) mView.findViewById(R.id.patient_RecyclerView_ID);

            Picasso.with(viewContext.getApplicationContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().resize(200, 200).
                    transform(new RoundedTransformation(100, 35)).
                    placeholder(R.drawable.profileplcaeholder).into(display_eImage,
                    new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            Log.w("Status: ", "Success!");
                        }
                        @Override
                        public void onError() {
                            Log.w("Error!", "The images are not loaded");
                            Picasso.with(viewContext.getApplicationContext()).load(image).centerCrop().resize(200, 200).
                                    transform(new RoundedTransformation(100, 35)).
                                    placeholder(R.drawable.profileplcaeholder).into(display_eImage);
                        }
                    });
        }
    }

    // Action menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu ... adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.signOut_ID) {
            //Sign Out the user
            signOut();
            return true;
        } else if (id == R.id.about_ID) {
            startActivity(new Intent(this, GeneralInfoAboutTBList.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signOut() {
        login.sigOutUSer();
        startActivity(new Intent(this, Login.class));
        finish();
    }

}
