package com.example.vettalkmobileapp;

import android.content.Context;

import com.example.vettalkmobileapp.Fragment.Step1Fragment;
import com.example.vettalkmobileapp.Model.Clinic;

import java.io.File;

public class Common {
    public static final String KEY_ENABLE_BUTTON_NEXT = "ENABLE_BUTTON_NEXT";
    public static final String KEY_CLINIC_STORE = "CLINIC_SAVE";
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
}
