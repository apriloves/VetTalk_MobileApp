package com.example.vettalkmobileapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import com.example.vettalkmobileapp.Adapter.ViewPageAdapter;
import com.example.vettalkmobileapp.Fragment.Step1Fragment;
import com.shuhart.stepview.StepView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetAppointmentOption extends AppCompatActivity {

    LocalBroadcastManager localBroadcastManager;
    AlertDialog dialog;

    ImageView notification;
    ImageView menu;
    StepView stepView;
    NonSwipeViewPager viewPager;
    TextView nextstep;
    TextView stepback;


    //Intent i = new Intent(this, Step1Fragment.class);
    @OnClick(R.id.stepback)
    void previousStep(){
        if(Common.step == 3 || Common.step > 0){
            Common.step--;
            viewPager.setCurrentItem(Common.step);
        }
    }

    @OnClick(R.id.nextstep)
    void nextClick(){
        if (Common.step < 3 || Common.step == 0){
            Common.step++;
            if(Common.step == 4){

            }
            viewPager.setCurrentItem(Common.step);
        }
    }

    private void loadScheduleByClinic(String clinicId) {
    }

    private BroadcastReceiver buttonNextReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Common.currentClinic = intent.getParcelableExtra(Common.KEY_CLINIC_STORE);
            nextstep.setEnabled(true);
            setColorButton();
        }
    };

    @Override
    protected void onDestroy(){
        localBroadcastManager.unregisterReceiver(buttonNextReceiver);
        super.onDestroy();
    }

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

        //dialog = new SpotsDialog.Builder().setContext(this).build();

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
        localBroadcastManager.registerReceiver(buttonNextReceiver, new IntentFilter(Common.KEY_ENABLE_BUTTON_NEXT));

        setupStepView();
        setColorButton();

        viewPager.setAdapter(new ViewPageAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(4);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < 3) {
                    nextstep.setEnabled(true);
                    nextstep.setText("Next");
                }
                else if (position == 3){
                    nextstep.setEnabled(true);
                    nextstep.setText("Confirm");
                }
                setColorButton();
            }

            @Override
            public void onPageSelected(int position) {

                stepView.go(position,true);
                if(position == 0) {
                    stepback.setEnabled(false);
                }
                else {
                    stepback.setEnabled(true);
                }

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
