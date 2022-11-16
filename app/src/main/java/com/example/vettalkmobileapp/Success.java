package com.example.vettalkmobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Success extends AppCompatActivity {

    ImageView notification;
    ImageView menu;
    ImageView pfp;
    TextView name;
    LinearLayout dashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        notification = findViewById(R.id.imageView2);
        menu = findViewById(R.id.imageView3);

        pfp = findViewById(R.id.imageView4);
        name = findViewById(R.id.textView2);
        dashboard = findViewById(R.id.linearLayout);

        dashboard.animate().translationY(-290).setDuration(800).setStartDelay(100);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Success.this, Notifications.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Success.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menupage);
                dialog.show();
            }
        });
    };

    public void medicalrecords(View v) {
        Intent intent = new Intent(Success.this, MedicalRecords.class);
        startActivity(intent);
    };

    public void appointments(View v) {
        Intent intent = new Intent(Success.this, SetAppointments.class);
        startActivity(intent);
    };

    public void messages(View v) {
        Intent intent = new Intent(Success.this, Messages.class);
        startActivity(intent);
    };

    public void transactionhistory(View v) {
        Intent intent = new Intent(Success.this, TransactionHistory.class);
        startActivity(intent);
    };

}
