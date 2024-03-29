package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.CustomCardView.Patients;
import com.example.mohsenqaysi.tbmanagement.Helper.RoundedTransformation;
import com.example.mohsenqaysi.tbmanagement.PatientDetailsRegistrationForm;
import com.example.mohsenqaysi.tbmanagement.PatientsDetails;
import com.example.mohsenqaysi.tbmanagement.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PatientFragment extends Fragment {

    private FloatingActionButton NewRegistaration;

    private RecyclerView mPatientList;
    private DatabaseReference mDatabase;
    private View view;
    private String FIREBASE_URL_PATH = "https://tbmanagement-aff8e.firebaseio.com/patientsName";
    private static Activity viewCotext;


    public PatientFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_patient, container, false);
        viewCotext = getActivity();

        NewRegistaration = (FloatingActionButton) view.findViewById(R.id.FloatingActionButton_ID2);
        NewRegistaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), PatientDetailsRegistrationForm.class));

            }
        });
        mPatientList = (RecyclerView) view.findViewById(R.id.patients_list_ID);

        // @param hasFixedSize true if adapter changes cannot affect the size of the RecyclerView.
        mPatientList.setHasFixedSize(true);
        mPatientList.setLayoutManager(new LinearLayoutManager(getContext()));

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("patients"); // gets all its children
        mDatabase.keepSynced(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<Patients, PatientsViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Patients, PatientsViewHolder>(
                Patients.class,
                R.layout.patients_row, // this is the custom layout
                PatientsViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(PatientsViewHolder viewHolder, final Patients model, int position) {
                viewHolder.setName(model.getFullName());
                viewHolder.setStage(model.getStageDiagnosis());
                viewHolder.setIamge(model.getImage());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(), PatientsDetails.class);
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
        ViewCompat.setNestedScrollingEnabled(getView(), false);
        mPatientList.setAdapter(firebaseRecyclerAdapter);

    }

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

        public void setIamge(final String image) {

            final ImageView display_eImage = (ImageView) mView.findViewById(R.id.patient_RecyclerView_ID);

            Picasso.with(viewCotext.getApplicationContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().resize(200, 200).
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
                            Picasso.with(viewCotext.getApplicationContext()).load(image).centerCrop().resize(200, 200).
                                    transform(new RoundedTransformation(100, 35)).
                                    placeholder(R.drawable.profileplcaeholder).into(display_eImage);
                        }
                    });
        }
    }
}
