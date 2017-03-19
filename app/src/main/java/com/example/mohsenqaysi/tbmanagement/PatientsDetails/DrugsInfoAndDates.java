package com.example.mohsenqaysi.tbmanagement.PatientsDetails;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects.PatientDrugInfoObject;
import com.example.mohsenqaysi.tbmanagement.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import static com.example.mohsenqaysi.tbmanagement.R.array.schedule;


public class DrugsInfoAndDates extends AppCompatActivity {

    private EditText drugname;
    private TextView datepicked;
    private ImageButton startDate;
    private ImageButton endDate;
    private Button saveData;

    private ProgressDialog progressDialog;
    private String currentChild;
    private int mYear, mMonth, mDay;
    static final int DATE_PICKER_ID = 1111;
    private String todayDate = "No date available";
    private String scheule = "No date available";

    private DatabaseReference mDatabase;
    private String FIREBASE_URL_PATH_VISITS = "https://tbmanagement-aff8e.firebaseio.com/PatientsVisits";
    private String FIREBASE_ROOT_PATH = "https://tbmanagement-aff8e.firebaseio.com/";

    private FirebaseAuth mAuth;

    PatientDetails patientDetails = new PatientDetails();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drungsinfo_and_dates);

        currentChild = getIntent().getExtras().getString("currentChild");

        progressDialog = new ProgressDialog(this);

        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);

        drugname = (EditText) findViewById(R.id.drugNameInfo_ID);
        datepicked = (TextView) findViewById(R.id.DatePiched_ID);
        startDate = (ImageButton) findViewById(R.id.pickStartingDate_ID);
        endDate = (ImageButton) findViewById(R.id.pickEndingDate_ID);
        saveData = (Button) findViewById(R.id.drugInfoSaveButton_ID);
        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_PICKER_ID);
            }
        });


        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Saving Dating...");
                progressDialog.show();
                saveData();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        progressDialog.dismiss();
                        onBackPressed();
                    }
                }, 1000);
            }
        });



        // Get the type of the gender from the spinner
        Spinner genders_spinner = (Spinner) findViewById(R.id.schedule_spinner_ID);
        String[] genders = getResources().getStringArray(schedule);

        ArrayAdapter<String> arrayAdapter_genders =  new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_dropdown_item,genders);
        arrayAdapter_genders.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        genders_spinner.setAdapter(arrayAdapter_genders);
        genders_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedScheule = parent.getItemAtPosition(position).toString();
                scheule = selectedScheule;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:
                // open date-picker dialog.
                // set date picker for current date
                // add pickerListener listner to date picker
                return new DatePickerDialog(this, pickerListener, mYear, mMonth,mDay);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener pickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, the below method will be called.
        @Override
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {

            mYear  = selectedYear;
            mMonth = selectedMonth;
            mDay   = selectedDay;

            todayDate = mDay +"/"+ (mMonth +1) +"/"+ mYear;
            datepicked.setText(todayDate);

        }
    };

    private void saveData() {

        // PatientDrugInfoObject
        String name = drugname.getText().toString();
        if (name.equals("")){
            name =  "No date available";
            scheule = "No date available";
            writeNewPost(name, todayDate, scheule, todayDate);
        } else {
            writeNewPost(name, todayDate, scheule, todayDate);
        }
    }


    private void writeNewPost(String drugName, String startDate, String schedule,String endDate){
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fristResponder = mAuth.getCurrentUser();
        String ID = fristResponder.getUid();
        Log.e("PatientFragment_ID: ", ID);

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH_VISITS).child(ID).child(currentChild);

        PatientDrugInfoObject patientDrugInfoObject = new PatientDrugInfoObject(drugName,startDate,schedule,endDate);
        Map<String, Object> drugData = patientDrugInfoObject.toMapObject(); // Prepare patientData object

        Map<String, Object> childUpdates = new HashMap<>(); // Push this object to fire-base

        childUpdates.put("/drug/" , drugData);
        mDatabase.updateChildren(childUpdates);

        // Pass a list of all patients info onto patientsList so AdminActivity can read them all
        // Path: "https://tbmanagement-aff8e.firebaseio.com/patientsList"
        DatabaseReference patientsList = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_ROOT_PATH).child("patientsListDrugs").child(currentChild);
        Log.e("patientsListDrugs : ", patientsList.toString());
        Map<String, Object> patientsDrugsChildUpdates = new HashMap<>(); // Create a new object and  Push it tp fire-base
        patientsDrugsChildUpdates.put("/drug/", patientDrugInfoObject);
        patientsList.updateChildren(patientsDrugsChildUpdates);


    }



}