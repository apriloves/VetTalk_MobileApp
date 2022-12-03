package com.example.vettalkmobileapp;

import java.util.List;

public interface IAllClinicLoadListener {
    void onAllClinicLoadListener (List<String> clinicNameList);
    void onAllClinicLoadFailed (String message);
}
