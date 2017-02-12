package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PatientFragment extends Fragment {

    private ListView patientsList;
    private TextView patiens_nameList;
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

        patientsList = (ListView) view.findViewById(R.id.patients_list_ID);

        rootRef = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH);

        FirebaseListAdapter<String> patientArrayAdapter = new FirebaseListAdapter<String>(

                getActivity(),
                    String.class,
                android.R.layout.simple_list_item_1,
                rootRef.orderByChild("name")
        ) {
//            @Override
//            protected void populateView(View v,HashMap model, int position) {
//                patiens_nameList = (TextView)v.findViewById(android.R.id.text1);
//                String fullName = model.get("fullName").toString();
//                patiens_nameList.setText(fullName);
//            }

            @Override
            protected void populateView(View v, String hashMap, int position) {
                patiens_nameList = (TextView)v.findViewById(android.R.id.text1);
                // Get the key of the item at @position
                DatabaseReference itemRef = getRef(position);
                String itemKey = itemRef.getKey();
                patiens_nameList.setText(itemKey);

            }
        };

        patientsList.setAdapter(patientArrayAdapter);

//        rootRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    //Getting the data from snapshot
//                    PatientsDetailsRegistrationDataObject patient = postSnapshot.getValue(PatientsDetailsRegistrationDataObject.class);
//
//                    //Adding it to a string
//                    String result = "Name: "+patient.fullName+"\nAddress: "+patient.address+"\n\n";
//
//                    Log.w("ObjectValues: ", result);
//
//                }
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


        return view;
    }

}
