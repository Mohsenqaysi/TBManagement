package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import com.example.mohsenqaysi.tbmanagement.Helper.RoundedTransformation;
import com.example.mohsenqaysi.tbmanagement.Helper.SnackBarMessages;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static android.os.Build.ID;
import static com.example.mohsenqaysi.tbmanagement.R.array.gender;
import static com.example.mohsenqaysi.tbmanagement.R.array.india_states;

public class PatientDetailsRegistrationForm extends AppCompatActivity implements View.OnClickListener {

    SnackBarMessages snackBarMessages = new SnackBarMessages();
    private ConstraintLayout parentLayout;
    private static final int REQUEST_IMAGE_LOAD = 1; // request code used in the call EXTERNAL_CONTENT_URI
    //Permission code that will be checked in the method onRequestPermissionsResult
    private static final int STORAGE_PERMISSION_CODE = 2;
    private String FIREBASE_URL_PATH = "https://tbmanagement-aff8e.firebaseio.com/FR";

    ProgressDialog mProgressDialog;

    // init all the inputs fields
    private ImageView profileImage;
    private Uri ImageUri;
    private File ImagePath;
    private Uri downladUri;
    private String URL_PATH;
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

    // FireBase reference
    private DatabaseReference rootRef;
    private StorageReference mStorage;
    private FirebaseAuth mAuth;
    private String FR_ID ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details_registration_form);
        parentLayout = (ConstraintLayout) findViewById(R.id.activity_patients_details_registration_form);

        requestionPermission();

        mStorage = FirebaseStorage.getInstance().getReference(); // RootRef
        mProgressDialog = new ProgressDialog(this);

        fullname = (EditText) findViewById(R.id.fullName_InputText_ID);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber_InputText_ID);
        dateOfBirth = (EditText) findViewById(R.id.dateOfBirth_InputText_ID);
        phoneNumber = (EditText) findViewById(R.id.phoneNumber_InputText_ID);
        flatNumber = (EditText) findViewById(R.id.flatNumber_InputText_ID);
        address = (EditText) findViewById(R.id.address_InputText_ID);
        city = (EditText) findViewById(R.id.city__InputText_ID);
        postCode = (EditText) findViewById(R.id.PostCode__InputText_ID);
        stageOfDiagnosis = (EditText) findViewById(R.id.StageofDiagnosis__InputText_ID);

        profileImage = (ImageView) findViewById(R.id.prifileImage_ID);
        profileImage.setOnClickListener(this);
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
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void requestionPermission() {

        int permissionCheck = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE);

        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                        STORAGE_PERMISSION_CODE);

                // STORAGE_PERMISSION_CODE is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }
    }

    private void saveUserDataToFirebaseDatabase() {
        Log.w("Hi", "I am Save Data Button ;)");

        final String Patient_full_Name = fullname.getText().toString();
        final String Patient_dateOfBirth = dateOfBirth.getText().toString();
        final String Patient_gender = genderType;
        final String Patient_phone_Number = phoneNumber.getText().toString();
        final String Patient_stage_Diagnosis = stageOfDiagnosis.getText().toString();
        final String Patient_flat_Number = flatNumber.getText().toString();
        final String Patient_address = address.getText().toString();
        final String Patient_city = city.getText().toString();
        final String Patient_area = state;
        final String Patient_postalCode = postCode.getText().toString();

        if (ImageUri!= null && !Patient_full_Name.isEmpty() && !Patient_dateOfBirth.isEmpty() &&
                !Patient_gender.isEmpty() && !Patient_phone_Number.isEmpty() && !Patient_stage_Diagnosis.isEmpty()
                && !Patient_flat_Number.isEmpty() && !Patient_address.isEmpty() && !Patient_city.isEmpty() &&
                !Patient_area.isEmpty() && !Patient_postalCode.isEmpty()){

            mAuth = FirebaseAuth.getInstance();
            FirebaseUser fristResponder = mAuth.getCurrentUser();
            FR_ID = fristResponder.getUid();
            Log.e("FR_ID: ", FR_ID);

            StorageReference firebase_File_Path = mStorage.child("Patients_Images").child(ImageUri.getLastPathSegment());

            mProgressDialog.setMessage("Uploading image...");
            mProgressDialog.show();
            firebase_File_Path.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // get the download URL for the image back from the fireBase storgae
                     downladUri = taskSnapshot.getDownloadUrl();
                     URL_PATH = downladUri.toString();
                     Log.w("URL: ", downladUri.toString());

                    // Upload the patient profile with the image
                    writeNewPost(URL_PATH , Patient_full_Name,Patient_dateOfBirth, Patient_gender, Patient_phone_Number, Patient_stage_Diagnosis,
                            Patient_flat_Number, Patient_address, Patient_city, Patient_area, Patient_postalCode);
                     mProgressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    snackBarMessages.googleServicesCheck(parentLayout,R.string.Profile_Failed_TO_Create);
                    mProgressDialog.dismiss();

                }
            });
        } else {
            Log.w("Hi", "else :(");
            snackBarMessages.googleServicesCheck(parentLayout, R.string.Complete_The_Form);

        }
    }

    private void writeNewPost(String Patient_Profile_Image, String fullName,String Patient_dateOfBirth, String gender, String phoneNumber,
                               String stageDiagnosis, String flatNumber, String address, String city, String area, String postalCode) {

        rootRef = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH).child(FR_ID);
        rootRef.keepSynced(true);
        /* push() generates a unique key for the new added patient
        String key = rootRef.child("info").push().getKey();
        */
        String key = rootRef.push().getKey();

        Log.w("Hi", "I am working ;)");

        PatientsDetailsRegistrationDataObject newPatient = new PatientsDetailsRegistrationDataObject(Patient_Profile_Image,fullName,Patient_dateOfBirth, gender, phoneNumber,
                stageDiagnosis, flatNumber, address, city, area, postalCode);
        Map<String, Object> patientData = newPatient.toMapObject(); // patientData object

        Map<String, Object> childUpdates = new HashMap<>();
        /* childUpdates takes in  (key) as the unique key and patientData as the object
         "/patients/" + key is the path where  patientData is add into
        */


        childUpdates.put("/patients/" + key, patientData);
        rootRef.updateChildren(childUpdates);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.prifileImage_ID:
                Log.w("Hi", "I am profile Image_ID ;)");
                    loadImage();
                break;
            case R.id.saveData_PatientForm_ID:
                Log.w("Internet Status: ", String.valueOf(isConnected()));
                if (isConnected() != false) {
                    saveUserDataToFirebaseDatabase();
                } else {
                    snackBarMessages.googleServicesCheck(parentLayout,R.string.you_are_NOT_Connected);
                }
                break;
            default:
                break;
        }
    }

    private void loadImage() {
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_LOAD);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        //Checking the request code of our request
        if(requestCode == STORAGE_PERMISSION_CODE){

            //If permission is granted
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                //Displaying a toast
                Toast.makeText(this,"Permission granted now you can read the storage",Toast.LENGTH_LONG).show();
            }else{
                //Displaying another toast if permission is not granted
                Toast.makeText(this,"Oops you just denied the permission",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_LOAD && resultCode == RESULT_OK) {
            mProgressDialog.setMessage("Loading image...");
            mProgressDialog.show();
            ImageUri = data.getData(); // get the picked image

            String[] filePAth = { MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(ImageUri, filePAth,null,null,null);
            cursor.moveToFirst();

            // Get the full image path
            String Path = cursor.getString(cursor.getColumnIndex(filePAth[0]));
            Log.w("data!", Path);
             ImagePath = new File(Path);
            Log.w("ImagePath!", ImagePath.toString());
            cursor.close();
            // pass the imagePath to Picasso as File object
            // "converting the given pathname string into an abstract pathname." <-- source File doc
            Picasso.with(getApplicationContext()).load(ImagePath).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().resize(200, 200).
                    transform(new RoundedTransformation(100, 35)).placeholder(R.drawable.profileplcaeholder).into(profileImage,
                    new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            mProgressDialog.dismiss();
                        }
                        @Override
                        public void onError() {
                            mProgressDialog.show();
                            Picasso.with(getApplicationContext()).load(ImagePath).centerCrop().resize(200, 200).
                                    transform(new RoundedTransformation(100, 35)).placeholder(R.drawable.profileplcaeholder).into(profileImage);
                            Log.w("Error!", ImageUri.getEncodedPath());
                        }
                    });
        }
    }

    // cehck for interent connect.
    public boolean isConnected(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        boolean oline = connectivityManager.getActiveNetworkInfo() != null;
        if(oline) {
            return  true;
        } else {
            return false;
        }
    }


}
