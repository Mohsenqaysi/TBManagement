package com.example.mohsenqaysi.tbmanagement.Helper;

import android.support.design.widget.Snackbar;
import android.view.View;

import static android.support.design.widget.Snackbar.LENGTH_LONG;

/**
 * Created by mohsenqaysi on 2/4/17.
 */

// show messages to te user to inform them of the status of the action they preform.

public class SnackBarMessages {

    private View parentLayout;
    private String text;

    public SnackBarMessages() {

    }

    public void SnackBarMessages(View layout, String meeage) {
        this.parentLayout = layout;
        this.text = meeage;
    }

    //show messages in a SnackBar
    public void showToast() {
        Snackbar.make(parentLayout, text, LENGTH_LONG).show();
    }
}
