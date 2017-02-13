package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mohsenqaysi.tbmanagement.FirebaseDataObjects.PatientsDetailsRegistrationDataObject;
import com.example.mohsenqaysi.tbmanagement.Helper.SnackBarMessages;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.mohsenqaysi.tbmanagement.R.array.gender;
import static com.example.mohsenqaysi.tbmanagement.R.array.india_states;

public class PatientDetailsRegistrationForm extends AppCompatActivity implements View.OnClickListener {

    SnackBarMessages snackBarMessages = new SnackBarMessages();
    private ConstraintLayout parentLayout;
    private static final int REQUEST_IMAGE_LOAD = 1; // request code used in the call EXTERNAL_CONTENT_URI
    ProgressDialog mProgressDialog;

    // init all the inputs fields
    private CircleImageView profileImage;
    private Uri ImageUri;
    private File ImagePath;
    private Uri downladUri;
    private  String URL_PATH;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details_registration_form);
        parentLayout = (ConstraintLayout) findViewById(R.id.activity_patients_details_registration_form);

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

        // TODO: profileImage --> leave it for now. Add a click action for the image profile button
        profileImage = (CircleImageView) findViewById(R.id.prifileImage_ID);
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
//                Toast.makeText(getApplicationContext(),"You clicked on: "+ genderSelectedType ,Toast.LENGTH_SHORT).show();
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
//                Toast.makeText(getApplicationContext(),"You clicked on: "+ stateSelectedName ,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        if (ImageUri!= null && !Patient_full_Name.isEmpty() && !Patient_dateOfBirth.isEmpty() && !Patient_gender.isEmpty() && !Patient_phone_Number.isEmpty() && !Patient_stage_Diagnosis.isEmpty()
                && !Patient_flat_Number.isEmpty() && !Patient_address.isEmpty() && !Patient_city.isEmpty() && !Patient_area.isEmpty() && !Patient_postalCode.isEmpty()){

//           String id =  mAuth.getCurrentUser().getUid();
//            Log.w("ImagePath ID: ", id);

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
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    snackBarMessages.googleServicesCheck(parentLayout,R.string.Profile_Failed_TO_Create);
                }
            });
        } else {
            Log.w("Hi", "else :(");
            snackBarMessages.googleServicesCheck(parentLayout, R.string.Complete_The_Form);

        }
    }

    private void writeNewPost(String Patient_Profile_Image, String fullName,String Patient_dateOfBirth, String gender, String phoneNumber,
                               String stageDiagnosis, String flatNumber, String address, String city, String area, String postalCode) {

        rootRef = FirebaseDatabase.getInstance().getReference();
        /* push() generates a unique key for the new added patient
        String key = rootRef.child("info").push().getKey();
        */
        String key = rootRef.child("patients").push().getKey();
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
                //TODO: get the image from the user and upload it to firebase and pass its URL to the PatientsDetailsRegistrationDataObject
                Log.w("Hi", "I am profile Image_ID ;)");
                loadImage();
                break;
            case R.id.saveData_PatientForm_ID:
                saveUserDataToFirebaseDatabase();
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
            Picasso.with(this).load(ImagePath).placeholder(R.drawable.profileplcaeholder).into(profileImage,
                    new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            mProgressDialog.dismiss();
                        }
                        @Override
                        public void onError() {
                            mProgressDialog.dismiss();
                            Log.w("Error!", ImageUri.getEncodedPath());
                        }
                    });
        }
    }
}
