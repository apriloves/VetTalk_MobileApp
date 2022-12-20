package com.example.vettalkmobileapp.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.vettalkmobileapp.Common;
import com.example.vettalkmobileapp.Interface.ITimeSlotLoadListener;
import com.example.vettalkmobileapp.Model.TimeSlot;
import com.example.vettalkmobileapp.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;
import devs.mulham.horizontalcalendar.HorizontalCalendarView;
import dmax.dialog.SpotsDialog;


public class Step2Fragment extends Fragment implements ITimeSlotLoadListener {

    //@BindView(R.id.calendarView)
    //CalendarView calendarView;
    //@BindView(R.id.selectedDay)
    //TextView selected_day;

    ITimeSlotLoadListener iTimeSlotLoadListener;
    AlertDialog dialog;

    Unbinder unbinder;
    LocalBroadcastManager localBroadcastManager;
    Calendar selected_date;

    @BindView(R.id.recycler_time_slot)
    RecyclerView recycler_time_slot;
    @BindView(R.id.calendarView)
    HorizontalCalendarView calendarView;
    SimpleDateFormat simpleDateFormat;

    BroadcastReceiver displayTimeSlot = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Calendar date = Calendar.getInstance();
            date.add(Calendar.DATE, 0);
            loadAvailableTimeSlotofClinic(Common.currentClinic.getClinicId(), simpleDateFormat.format(date.getTime()));
        }
    };

    private void loadAvailableTimeSlotofClinic(String clinicId, String date) {

    }

    static Step2Fragment instance;

    public static Step2Fragment getInstance() {
        if(instance == null)
            instance = new Step2Fragment();
        return instance;
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iTimeSlotLoadListener = this;

        localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        localBroadcastManager.registerReceiver(displayTimeSlot, new IntentFilter(Common.KEY_DISPLAY_TIME_SLOT));

        simpleDateFormat = new SimpleDateFormat("dd_MM_yyyy");

        dialog = new SpotsDialog.Builder().setContext(getContext().setCancelable(false).build());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        //calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
        //    @Override
        //    public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
        //        String todayDate = (month + 1) + "/" + dayOfMonth + "/" + year;
        //        Log.d("date", todayDate);
        //        selected_day.setText(todayDate);
        //    }
        //});

        return inflater.inflate(R.layout.fragment_step2,container,false);
    }

    @Override
    public void onTimeSlotLoadSuccess(List<TimeSlot> timeSlotList) {

    }

    @Override
    public void onTimeSlotLoadFailed(String message) {

    }

    @Override
    public void onTimeSlotLoadEmpty() {

    }
}