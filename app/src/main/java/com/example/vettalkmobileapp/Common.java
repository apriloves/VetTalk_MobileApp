package com.example.vettalkmobileapp;

import android.content.Context;

import com.example.vettalkmobileapp.Fragment.Step1Fragment;
import com.example.vettalkmobileapp.Model.Clinic;

import java.io.File;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_CLINIC_STORE = "CLINIC_SAVE";
    public static final String KEY_DISPLAY_TIME_SLOT = "DISPLAY_TIME_SLOT";
    public static final String KEY_STEP = "STEP";
    public static final int TIME_SLOT_TOTAL = 6;
    public static Clinic currentClinic;
    public static int step = 0;

    public static String getAppPath(Context context)
    {
        File dir = new File(android.os.Environment.getExternalStorageDirectory()
                + File.separator
                + context.getResources().getString(R.string.app_name)
                + File.separator
        );

        if(!dir.exists())
            dir.mkdir();
        return dir.getPath() + File.separator;
    }

    public static String convertTimeSlotToString(int slot) {
        switch (slot){
            case 0:
                return "9:00AM - 10:00AM";
            case 1:
                return "10:00AM - 11:00AM";
            case 2:
                return "11:00AM - 12:00PM";
            case 3:
                return "1:00PM - 2:00PM";
            case 4:
                return "2:00PM - 3:00PM";
            case 5:
                return "4:00PM - 5:00PM";
            default:
                return "Closed";
        }
    }
}
