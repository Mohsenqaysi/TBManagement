package com.example.mohsenqaysi.tbmanagement.PatientsDetails;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.CustomCardView.DrugsInfo;
import com.example.mohsenqaysi.tbmanagement.Helper.RoundedTransformation;
import com.example.mohsenqaysi.tbmanagement.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class PatientDetails extends AppCompatActivity {

    private String image;
    private String fullName;
    private String dataOfBirth;
    private String stage;
    private ImageView patient_Iamge;
    private TextView patient_fullName;
    private TextView patient_DataOfBirht;
    private TextView patient_stage;

    // init fire-base
    private RecyclerView drugsList;
    private DatabaseReference mDatabase;
    private String FIREBASE_URL_PATH_VISITS = "https://tbmanagement-aff8e.firebaseio.com/PatientsVisits";
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_details);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser fristResponder = mAuth.getCurrentUser();
        String ID = fristResponder.getUid();
        Log.e("PatientFragment_ID: ", ID);


        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl(FIREBASE_URL_PATH_VISITS).child(ID).child("visit");
        mDatabase.keepSynced(true);



        drugsList = (RecyclerView) findViewById(R.id.drugList_ID);
        drugsList.setHasFixedSize(true);
        drugsList.setLayoutManager(new LinearLayoutManager(this));

        patient_Iamge = (ImageView) findViewById(R.id.patientsDetails_Image_ID);
        patient_fullName = (TextView) findViewById(R.id.patientDetails_fullName_ID);
        patient_DataOfBirht = (TextView) findViewById(R.id.patientDetails_BirthDay_ID);
        patient_stage = (TextView) findViewById(R.id.patientDetails_Stage_ID);
//        Intent intent = new Intent(getContext(), PatientsDetails.class);
//        intent.putExtra("image",model.getImage());
//        intent.putExtra("fullName", model.getFullName());
//        intent.putExtra("dataOfBirth",model.getDateOfBirth());
//        intent.putExtra("gender", model.getGender());
//        intent.putExtra("phoneNumber",model.getPhoneNumber());
//        intent.putExtra("satge", model.getStageDiagnosis());
//        intent.putExtra("flatNumber", model.getFlatNumber());
//        intent.putExtra("address",model.getAddress());
//        intent.putExtra("city", model.getCity());
//        intent.putExtra("area", model.getArea());
//        intent.putExtra("postCode",model.getPostalCode());
         image =  getIntent().getExtras().getString("image");
        fullName = getIntent().getExtras().getString("fullName");
        dataOfBirth = getIntent().getExtras().getString("dataOfBirth");
        stage = getIntent().getExtras().getString("stage");

        // if the images are not alread saved offline load them
        Picasso.with(getApplicationContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().resize(440,440).
                transform(new RoundedTransformation(300, 40)).placeholder(R.drawable.profileplcaeholder).into(patient_Iamge,
                new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.w("Status: ", "Success!");
                    }
                    @Override
                    public void onError() {
                        Log.w("Error!", "The images are not loaded");
                        Picasso.with(getApplicationContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).centerCrop().resize(440,440).
                                transform(new RoundedTransformation(300, 40)).placeholder(R.drawable.profileplcaeholder).into(patient_Iamge);
                    }
                });

        patient_fullName.setText(fullName);
        patient_DataOfBirht.setText("Date of birth: "+ dataOfBirth);
        patient_stage.setText("Stage: " + stage);

        Log.w("satge: " ,stage);


        // TODO: Read
        // TODO: Update in user info and store into in FireBase




    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<DrugsInfo,DrugInfoViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DrugsInfo, DrugInfoViewHolder>(

                DrugsInfo.class,
                R.layout.drugs_info_row,
                DrugInfoViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(DrugInfoViewHolder viewHolder, DrugsInfo model, int position) {
                viewHolder.setDrugName(model.getDrugname());
                viewHolder.setStartDate(model.getSartdate());
                viewHolder.setSchedule(model.getSchedule());
                viewHolder.setEndDate(model.getEnddate());

            }
        };

        drugsList.setAdapter(firebaseRecyclerAdapter);

    }

    public  static class DrugInfoViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public DrugInfoViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public  void setDrugName(String drungNmae){
            TextView display_drugname = (TextView) mView.findViewById(R.id.drugName_ID);
            display_drugname.setText(drungNmae);
        }
        public  void setStartDate(String startDate){
            TextView display_drugname = (TextView) mView.findViewById(R.id.startDateValue_ID);
            display_drugname.setText(startDate);
        }
        public  void setSchedule(String schedule){
            TextView display_drugname = (TextView) mView.findViewById(R.id.scheduleDateValue_ID);
            display_drugname.setText(schedule);
        }
        public  void setEndDate(String endDate){
            TextView display_drugname = (TextView) mView.findViewById(R.id.endDateValue_ID);
            display_drugname.setText(endDate);
        }
    }
}
