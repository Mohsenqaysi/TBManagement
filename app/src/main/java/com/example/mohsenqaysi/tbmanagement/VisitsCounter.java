package com.example.mohsenqaysi.tbmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

public class VisitsCounter extends AppCompatActivity {

    private NumberPicker numberPickerDone;
    private NumberPicker numberPickerMissed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits_counter);

        numberPickerDone = (NumberPicker) findViewById(R.id.numberPickerDone_ID);
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
                        result.setText(String.valueOf(newVal));
                    }
                });
            }
        });

        numberPickerMissed = (NumberPicker) findViewById(R.id.numberPickerMissed_ID);
        numberPickerMissed.setMaxValue(10);
        numberPickerMissed.setMinValue(1);
        numberPickerMissed.setWrapSelectorWheel(false);

        numberPickerMissed.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, final int newVal) {
                Log.e("newVal: ", String.valueOf(newVal));

                Button done = (Button) findViewById(R.id.numberPickerDoneButton_ID);
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView result = (TextView) findViewById(R.id.ResultTextView_ID);
                        result.setText(String.valueOf(newVal));
                    }
                });
            }
        });
    }
}
