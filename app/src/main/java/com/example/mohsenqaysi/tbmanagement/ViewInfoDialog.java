package com.example.mohsenqaysi.tbmanagement;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class ViewInfoDialog extends AppCompatActivity {
    //    private ImageView patient_Iamge;
    private String URL;
    private ImageButton call_button;

    public void showDialog(Activity activity, final String image, String fullNmae, String phoneNumber, String fullAddress) {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_view_info_dialog);
        dialog.getWindow().setLayout((int) (getScreenWidth(activity) * .9), ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);

        TextView name = (TextView) dialog.findViewById(R.id.dialogInfoName_ID);
        name.setText(fullNmae);
        TextView address = (TextView) dialog.findViewById(R.id.dialogInfoAdress_ID);
        address.setText(fullAddress);

        TextView phone = (TextView) dialog.findViewById(R.id.dialogInfoPhoneNumber_ID);
        phone.setText(phoneNumber);

        final ImageView profileImage = (ImageView) dialog.findViewById(R.id.dialogProfileImage_ID);
//        Picasso.with(dialog.getContext()).load(image).into(profileImage);

        Picasso.with(dialog.getContext()).load(image).networkPolicy(NetworkPolicy.OFFLINE).placeholder(R.drawable.profileplcaeholder).into(profileImage,
                new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError() {
                        Picasso.with(getApplicationContext()).load(image).placeholder(R.drawable.profileplcaeholder).into(profileImage);
                    }
                });

        dialog.show();

    }

    public static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }
}
