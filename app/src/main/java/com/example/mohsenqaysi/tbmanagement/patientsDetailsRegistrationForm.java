package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.mohsenqaysi.tbmanagement.R.array.gender;

public class PatientsDetailsRegistrationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details_registration_form);

        Spinner genders_spinner = (Spinner) findViewById(R.id.IndianStates_spinner_ID);
        String[] genders = getResources().getStringArray(gender);

        ArrayAdapter<String> arrayAdapter =  new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,genders);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        genders_spinner.setAdapter(arrayAdapter);
        genders_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"You clicked on: "+ item ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
