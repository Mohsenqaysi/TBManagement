package com.example.mohsenqaysi.tbmanagement.Helper;

import android.content.DialogInterface;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.example.mohsenqaysi.tbmanagement.R;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

/**
 * Created by mohsenqaysi on 2/4/17.
 */

// show messages to te user to inform them of the status of the action they preform.

public class SnackBarMessages {

    private View parentLayout;
    private int text;

    public SnackBarMessages() {

    }

    public void SnackBarMessages(View layout, int message) {
        this.parentLayout = layout;
        this.text = message;
    }

    //show messages in a SnackBar
    public void showToast() {
        Snackbar.make(parentLayout, text, LENGTH_LONG).show();
    }

    public void googleServicesCheck(View layout, int message){
        AlertDialog.Builder builder = new AlertDialog.Builder(layout.getContext());
        builder.setTitle("Error!");
        builder.setIcon(R.drawable.ic_error_outline_black);
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}
