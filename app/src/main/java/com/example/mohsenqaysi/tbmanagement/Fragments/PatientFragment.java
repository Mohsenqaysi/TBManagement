package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohsenqaysi.tbmanagement.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientFragment extends Fragment {

    private RecyclerView mPatientList;
    private DatabaseReference rootRef;
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


        rootRef = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH);



        return view;
    }

}
