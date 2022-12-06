package com.example.vettalkmobileapp.Interface;

import com.example.vettalkmobileapp.Model.Clinic;

import java.util.List;

public interface IClinicLoadListener {
    void onClinicLoadSuccess(List<Clinic> clinicList);
    void onClinicLoadFailed(String message);
}
