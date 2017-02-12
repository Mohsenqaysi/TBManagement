package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import static com.example.mohsenqaysi.tbmanagement.R.array.gender;
import static com.example.mohsenqaysi.tbmanagement.R.array.india_states;

public class PatientsDetailsRegistrationForm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details_registration_form);

        // Get the type of the gender from the spinner
        Spinner genders_spinner = (Spinner) findViewById(R.id.gender_spinner_ID);
        String[] genders = getResources().getStringArray(gender);

        ArrayAdapter<String> arrayAdapter_genders =  new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,genders);
        arrayAdapter_genders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genders_spinner.setAdapter(arrayAdapter_genders);
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


        // Get the name of the state from the spinner
        Spinner indian_States_spinner = (Spinner) findViewById(R.id.IndianStates_spinner_ID);
        String[] states = getResources().getStringArray(india_states);

        ArrayAdapter<String> arrayAdapter_states = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item, states
        );
        arrayAdapter_states.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        indian_States_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
