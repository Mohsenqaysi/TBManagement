package com.example.mohsenqaysi.tbmanagement;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by mohsenqaysi on 2/4/17.
 */

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
        Snackbar.make(parentLayout, text, Snackbar.LENGTH_LONG).show();
    }
}
