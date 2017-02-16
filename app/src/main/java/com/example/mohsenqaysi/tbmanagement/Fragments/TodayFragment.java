package com.example.mohsenqaysi.tbmanagement.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mohsenqaysi.tbmanagement.R;
import com.google.firebase.database.DatabaseReference;

public class TodayFragment extends Fragment {

    private DatabaseReference rootRef;

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

        return view;
    }



}


