package com.example.mohsenqaysi.tbmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mohsenqaysi.tbmanagement.Authatications.Login;
import com.example.mohsenqaysi.tbmanagement.TB_General_Info.GeneralInfoAboutTBList;
import com.google.firebase.auth.FirebaseAuth;

public class AdminActivity extends AppCompatActivity {

    Login login = new Login();
    private Toolbar toolbar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }


    // Action menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu ... adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.signOut_ID) {
            //Sign Out the user
            signOut();
            return true;
        } else if (id == R.id.about_ID) {
            startActivity(new Intent(this, GeneralInfoAboutTBList.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signOut() {
        login.sigOutUSer();
        startActivity(new Intent(this, Login.class));
        finish();
    }

}
