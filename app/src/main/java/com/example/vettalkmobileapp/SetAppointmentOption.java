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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class SetAppointmentOption extends AppCompatActivity {

    ImageView notification;
    ImageView menu;
    StepView stepView;
    ViewPager viewPager;
    TextView nextstep;
    TextView stepback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setappointmentoption);

        notification = findViewById(R.id.imageView2);
        stepback = findViewById(R.id.stepback);
        nextstep = findViewById(R.id.nextstep);
        stepView = findViewById(R.id.stepview);
        viewPager = findViewById(R.id.viewpager);

        ButterKnife.bind(SetAppointmentOption.this);
        setupStepView();
        setColorButton();

        viewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 0)
                    stepback.setEnabled(false);
                else
                    stepback.setEnabled(true);

                setColorButton();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetAppointmentOption.this, Notifications.class);
                startActivity(intent);
            }
        });

        menu = findViewById(R.id.imageView3);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(SetAppointmentOption.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menupage);
                dialog.show();
            }
        });

    }

    private void setColorButton() {
        if(stepback.isEnabled()){
            stepback.setBackgroundResource(R.color.stepcolor);
        }
        else{
            stepback.setBackgroundResource(android.R.color.darker_gray);
        }

        if(nextstep.isEnabled()){
            nextstep.setBackgroundResource(R.color.stepcolor);
        }
        else{
            nextstep.setBackgroundResource(android.R.color.darker_gray);
        }
    }

    private void setupStepView() {
        List<String> stepList = new ArrayList<>();
        stepList.add("Clinic");
        stepList.add("Schedule");
        stepList.add("Service");
        stepList.add("Confirm");
        stepView.setSteps(stepList);
    }

    public void menuboard(View v) {
        Intent intent = new Intent(this, SetAppointments.class);
        startActivity(intent);
    }
}
