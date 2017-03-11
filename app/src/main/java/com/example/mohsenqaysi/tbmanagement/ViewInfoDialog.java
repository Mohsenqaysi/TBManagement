package com.example.mohsenqaysi.tbmanagement;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

public class ViewInfoDialog  extends AppCompatActivity {
//    private ImageView patient_Iamge;
    private String URL;

    public void showDialog(Activity activity, String image, String fullNmae, String phoneNumber , String fullAddress ){
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_view_info_dialog);
        dialog.getWindow().setLayout((int) (getScreenWidth(activity) * .8), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        TextView name = (TextView) dialog.findViewById(R.id.dialogInfoName_ID);
        name.setText(fullNmae);
        TextView address = (TextView) dialog.findViewById(R.id.dialogInfoAdress_ID);
        address.setText(fullAddress);

        TextView phone = (TextView) dialog.findViewById(R.id.dialogInfoPhoneNumber_ID);
        phone.setText(phoneNumber);

        dialog.show();

    }

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }
}
