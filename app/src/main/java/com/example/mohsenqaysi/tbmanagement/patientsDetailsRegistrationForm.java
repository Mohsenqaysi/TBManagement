package com.example.mohsenqaysi.tbmanagement;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects.PatientsDetailsRegistrationDataObject;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.mohsenqaysi.tbmanagement.R.array.gender;
import static com.example.mohsenqaysi.tbmanagement.R.array.india_states;

public class PatientsDetailsRegistrationForm extends AppCompatActivity implements View.OnClickListener {

    // init all the inputs fields
    private Button profileImage_button;
    private ImageView profileImage;
    private EditText fullname;
    private String genderType;
    private EditText dateOfBirth;
    private EditText phoneNumber;
    private EditText flatNumber;
    private EditText address;
    private String state;
    private EditText city;
    private EditText postCode;
    private EditText stageOfDiagnosis;
    private Button saveData_button;

    // Firebase refrence
    private DatabaseReference rootRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details_registration_form);


//        profileImage = (ImageView) findViewById(R.id.prifileImage_ID);
        fullname = (EditText) findViewById(R.id.fullName_InputText_ID);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber_InputText_ID);
        dateOfBirth = (EditText) findViewById(R.id.dateOfBirth_InputText_ID);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber_InputText_ID);
        flatNumber = (EditText) findViewById(R.id.flatNumber_InputText_ID);
        address = (EditText) findViewById(R.id.address_InputText_ID);
        city = (EditText) findViewById(R.id.city__InputText_ID);
        postCode = (EditText) findViewById(R.id.PostCode__InputText_ID);
        stageOfDiagnosis = (EditText) findViewById(R.id.StageofDiagnosis__InputText_ID);

        // TODO: profileImage --> leave it for now. Add a click action for the image profile button
        profileImage_button = (Button) findViewById(R.id.prifileImage_ID);
        profileImage_button.setOnClickListener(this);
        saveData_button = (Button) findViewById(R.id.saveData_PatientForm_ID);
        saveData_button.setOnClickListener(this);


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
                String genderSelectedType = parent.getItemAtPosition(position).toString();
                genderType = genderSelectedType;
                Toast.makeText(getApplicationContext(),"You clicked on: "+ genderSelectedType ,Toast.LENGTH_SHORT).show();
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
                String stateSelectedName = parent.getItemAtPosition(position).toString();
                        state = stateSelectedName;
                Toast.makeText(getApplicationContext(),"You clicked on: "+ stateSelectedName ,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void saveUserDataToFirebaseDatabase() {
        Log.w("Hi", "I am prifileImage_ID ;)");

//        profileImage; TODO: leave it for now
        String Patient_full_Name = fullname.getText().toString();
        String Patient_gender = genderType;
        String Patient_phone_Number = phoneNumber.getText().toString();
        String Patient_stage_Diagnosis = stageOfDiagnosis.getText().toString();
        String Patient_flat_Number = flatNumber.getText().toString();
        String Patient_address = address.getText().toString();
        String Patient_city = city.getText().toString();
        String Patient_area = state;
        String Patient_postalCode = postCode.getText().toString();

        writeNewPost(Patient_full_Name, Patient_gender, Patient_phone_Number, Patient_stage_Diagnosis,
                Patient_flat_Number, Patient_address, Patient_city, Patient_area, Patient_postalCode);

    }

    private void writeNewPost(String fullName, String gender, String phoneNumber, String stageDiagnosis, String flatNumber, String address, String city, String area, String postalCode) {

        rootRef = FirebaseDatabase.getInstance().getReference();
        // push() generates a unique key for the new added patient
//        String key = rootRef.child("info").push().getKey();
        String key = rootRef.child("patients").push().getKey();
        Log.w("Hi", "I am working ;)");

        PatientsDetailsRegistrationDataObject newPatient = new PatientsDetailsRegistrationDataObject(fullName, gender, phoneNumber, stageDiagnosis, flatNumber, address, city, area, postalCode);
        Map<String, Object> patientData = newPatient.toMapObject(); // value object


        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates takes in  (key) as the unique key and patientData as the object
        // /patients/ + key is the path you add, patientData into
        childUpdates.put("/patients/" + key, patientData);
        rootRef.updateChildren(childUpdates);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.prifileImage_ID:
                Log.w("Hi", "I am prifileImage_ID ;)");
                break;
            case R.id.saveData_PatientForm_ID:
                saveUserDataToFirebaseDatabase();
                break;
            default:
                break;
        }
    }

}
