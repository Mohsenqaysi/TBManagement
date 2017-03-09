package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.mohsenqaysi.tbmanagement.PatientsDetails.PatientDetails;

public class VisitsGraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visits_graph);

        // BottomNavigationView Buttons
        final BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation_ID);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                Toast.makeText(getApplicationContext(),"action_home",Toast.LENGTH_LONG).show();

                                startActivity(new Intent(getApplicationContext(), PatientDetails.class));
                                finish();

                                return  true;
                            case R.id.action_graph:
                                bottomNavigationView.getMenu().getItem(1).setChecked(false);

                                return  true;
                        }
                        return true;
                    }
                });
    }
}
