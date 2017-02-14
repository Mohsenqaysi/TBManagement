package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.mohsenqaysi.tbmanagement.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PatientFragment extends Fragment {

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
         view =  inflater.inflate(R.layout.fragment_patient, container, false);
         viewCotext = getActivity();


        mPatientList = (RecyclerView) view.findViewById(R.id.patients_list_ID);

        // @param hasFixedSize true if adapter changes cannot affect the size of the RecyclerView.
        mPatientList.setHasFixedSize(true);
        mPatientList.setLayoutManager(new LinearLayoutManager(getContext()));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("patients"); // gets all its children


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Patients,PatientsViewHolder > firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Patients, PatientsViewHolder>(
                Patients.class,
                R.layout.patients_row, // this is the custom layout
                PatientsViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(PatientsViewHolder viewHolder, Patients model, int position) {
                viewHolder.setName(model.getFullName());
                viewHolder.setStage(model.getStageDiagnosis());
                viewHolder.setIamge(model.getImage());

            }
        };
        mPatientList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class PatientsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public PatientsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String fullName){
            TextView display_Name = (TextView) mView.findViewById(R.id.patient_fullname_ID);
            display_Name.setText(fullName);
        }
        public void setStage(String stage){
            TextView display_Stage = (TextView) mView.findViewById(R.id.patient_stage_ID);
            display_Stage.setText("Stage: "+ stage);
        }
        public void setIamge(String image){

//          ImageView display_eImage = (ImageView) mView.findViewById(R.id.patient_RecyclerView_ID);
            ImageView display_eImage = (ImageView) mView.findViewById(R.id.patient_RecyclerView_ID);

//            Picasso.with(viewCotext.getApplicationContext()).load(image).placeholder(R.drawable.profileplcaeholder).into(display_eImage);

            Picasso.with(viewCotext.getApplicationContext()).load(image).centerCrop().resize(200,200).transform(new RoundedTransformation(100,35)).placeholder(R.drawable.profileplcaeholder).into( display_eImage,
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


        }
    }
}
