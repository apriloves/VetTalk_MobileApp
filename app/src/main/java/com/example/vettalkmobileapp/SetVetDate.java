package com.example.vettalkmobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shuhart.stepview.StepView;

public class SetVetDate extends AppCompatActivity {

    ImageView notification;
    ImageView menu;
    StepView stepView;
    ViewPager viewPager;
    Button btnprev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setvetdate);

        notification = findViewById(R.id.imageView2);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetVetDate.this, Notifications.class);
                startActivity(intent);
            }
        });

        menu = findViewById(R.id.imageView3);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SetVetDate.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menupage);
                dialog.show();
            }
        });

    }

    public void menuboard(View v) {
        Intent intent = new Intent(this, SetAppointments.class);
        startActivity(intent);
    }
}
