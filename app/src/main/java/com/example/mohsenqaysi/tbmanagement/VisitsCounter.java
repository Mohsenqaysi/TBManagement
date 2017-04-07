package com.example.mohsenqaysi.tbmanagement;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.PatientsDetails.PatientDetails;

public class VisitsCounter extends AppCompatActivity {

    private NumberPicker numberPickerDone;
    private NumberPicker numberPickerMissed;
    private ProgressDialog progressDialog;
    private String numberOfVisits;
    private String visitType;


        public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_Done_ID:
                if (checked)
                   Log.e("Done: ", "Done is selected");
                visitType = "Done";
                    break;
            case R.id.radio_missed_ID:
                if (checked)
                    Log.e("Missed: ", "Missed is selected");
                visitType = "Missed";
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits_counter);
        progressDialog = new ProgressDialog(this);


        numberPickerDone = (NumberPicker) findViewById(R.id.numberPicker_ID);
        numberPickerDone.setMaxValue(10);
        numberPickerDone.setMinValue(1);
        numberPickerDone.setWrapSelectorWheel(false);

        numberPickerDone.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, final int newVal) {
                Log.e("newVal: ", String.valueOf(newVal));

                Button done = (Button) findViewById(R.id.numberPickerDoneButton_ID);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView result = (TextView) findViewById(R.id.ResultTextView_ID);
                        // newVal is the picked number
                        numberOfVisits = Integer.toString(newVal); // pass the selected number
                        result.setText(String.valueOf(newVal));
//                        saveData();
                    }
                });
            }
        });

        numberPickerDone.setOnClickListener(new View.OnClickListener() {
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

    }

    private void saveData() {
            Intent intent = new Intent(this, PatientDetails.class);
            intent.putExtra("visitType",visitType);
            intent.putExtra("numberOfVisits",numberOfVisits);
            startActivity(intent);
    }
}
