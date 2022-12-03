package com.example.vettalkmobileapp;

import java.util.List;

public interface IClinicLoadListener {
    void onClinicLoadSuccess(List<Clinic> clinicList);
    void onClinicLoadFailed(String message);
}
