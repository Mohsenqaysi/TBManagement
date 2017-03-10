package com.example.mohsenqaysi.tbmanagement.PatientsDetails;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mohsenqaysi.tbmanagement.R;


public class DrungsinfoAndDates extends AppCompatActivity {

    private EditText drugname;
    private TextView datepicked;
    private ImageButton startDate;
    private ImageButton endDate;
    private Button saveDate;


    private DatePickerDialog datePickerDialog;
    private int mYear, mMonth, mDay;
    static final int DATE_PICKER_ID = 1111;
    private String todayDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drungsinfo_and_dates);

        final Calendar c = Calendar.getInstance();
        mYear  = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay   = c.get(Calendar.DAY_OF_MONTH);

        drugname = (EditText) findViewById(R.id.drugNameInfo_ID);
        datepicked = (TextView) findViewById(R.id.DatePiched_ID);
        startDate = (ImageButton) findViewById(R.id.pickStartingDate_ID);
        endDate = (ImageButton) findViewById(R.id.pickEndingDate_ID);
        saveDate = (Button) findViewById(R.id.drugInfoSaveButton_ID);
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
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_PICKER_ID:

                // open datepicker dialog.
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

}
