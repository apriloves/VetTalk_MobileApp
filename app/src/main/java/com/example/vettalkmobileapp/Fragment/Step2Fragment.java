package com.example.vettalkmobileapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.vettalkmobileapp.R;

import butterknife.BindView;


public class Step2Fragment extends Fragment {

    //@BindView(R.id.calendarView)
    //CalendarView calendarView;
    //@BindView(R.id.selectedDay)
    //TextView selected_day;

    static Step2Fragment instance;

    public static Step2Fragment getInstance() {
        if(instance == null)
            instance = new Step2Fragment();
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
}