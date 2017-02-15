package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mohsenqaysi.tbmanagement.PatientsDetails;
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
//        TBManagement tbManagement = new TBManagement();

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        show_button = (Button) view.findViewById(R.id.info_ID);
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), GeneralInfoAboutTBList.class));
            }
        });

        save_button = (Button) view.findViewById(R.id.Registar_ID);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("Hi", "I am working ;)");
                startActivity(new Intent(getContext(), PatientsDetails.class));
            }
        });
        return view;
    }



}


