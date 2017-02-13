package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.CustomCardView.Patients;
import com.example.mohsenqaysi.tbmanagement.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientFragment extends Fragment {

    private RecyclerView mPatientList;
    private DatabaseReference mDatabase;
    private String FIREBASE_URL_PATH = "https://tbmanagement-aff8e.firebaseio.com/patientsName";

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
        View view =  inflater.inflate(R.layout.fragment_patient, container, false);

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
            TextView name = (TextView) mView.findViewById(R.id.patient_fullname_ID);
            name.setText(fullName);
        }
        public void setStage(String stage){
            TextView name = (TextView) mView.findViewById(R.id.patient_stage_ID);
            name.setText(stage);
        }
//        public void setIamge(String image){
//            TextView name = (TextView) mView.findViewById(R.id.patient_RecyclerView_ID);
//            name.setText(image);
//        }
    }
}
