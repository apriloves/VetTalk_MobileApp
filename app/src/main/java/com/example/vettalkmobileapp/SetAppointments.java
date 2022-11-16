package com.example.vettalkmobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SetAppointments extends AppCompatActivity{

    ImageView notification;
    ImageView menu;
    //LinearLayout setappointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setappointments);

        notification = findViewById(R.id.imageView2);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAppointments.this, Notifications.class);
                startActivity(intent);
            }
        });

        //setappointment = findViewById(R.id.setappointment);

        //setappointment.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
       //         Intent intent = new Intent(SetAppointments.this, SetAppointmentOption.class);
       //         startActivity(intent);
       //     }
        //});

        menu = findViewById(R.id.imageView3);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SetAppointments.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menupage);
                dialog.show();
            }
        });
    }

    public void menuboard(View v) {
        Intent intent = new Intent(SetAppointments.this, Success.class);
        startActivity(intent);
    }

    public void setappointment(View view) {
        Intent intent = new Intent(SetAppointments.this, SetAppointmentOption.class);
        startActivity(intent);
    }

}


