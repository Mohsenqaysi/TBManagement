package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mohsenqaysi.tbmanagement.R;
import com.example.mohsenqaysi.tbmanagement.TB_General_Info.GeneralInfoAboutTBList;
import com.google.firebase.database.DatabaseReference;

public class TodayFragment extends Fragment {

    private DatabaseReference rootRef;
    private Button show_button;
    private Button save_button;

    public TodayFragment() {
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
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        show_button = (Button) view.findViewById(R.id.info_ID);
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GeneralInfoAboutTBList.class));
            }
        });

        save_button = (Button) view.findViewById(R.id.save_data_ID);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Hi", "I am working ;)");
//                saveUserDataToFirebaseDatabase();
            }
        });
        return view;
    }

//    private void saveUserDataToFirebaseDatabase() {
//
//        String fullName = "Mohsen Qaysi";
//        String gender = "Meal";
//        String phoneNumber = "0871310812";
//        String stageDiagnosis = "3";
//        String flatNumber = "26";
//        String address = "75 Pembroke road";
//        String city = "Dublin";
//        String area = "BallsBridge";
//        String postalCode = "D4";
//        writeNewPost(fullName, gender, phoneNumber, stageDiagnosis, flatNumber, address, city, area, postalCode);
//
//
//    }

//    private void writeNewPost(String fullName, String gender, String phoneNumber, String stageDiagnosis, String flatNumber, String address, String city, String area, String postalCode) {
//
//        rootRef = FirebaseDatabase.getInstance().getReference();
//        String key = rootRef.child("users").push().getKey();
//        Log.w("Hi", "I am working ;)");
//
//        PatientsDetailsRegistrationDataObject post = new PatientsDetailsRegistrationDataObject(fullName, gender, phoneNumber, stageDiagnosis, flatNumber, address, city, area, postalCode);
//        Map<String, Object> postValues = post.toMapObject();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//
//        childUpdates.put("/users/" + key, postValues);
////        childUpdates.put("/user-posts/" + key, "False");
//        rootRef.updateChildren(childUpdates);
//
//
//    }


}


