package com.example.vettalkmobileapp.Interface;

import java.util.List;

public interface IAllClinicLoadListener {
    void onAllClinicLoadListener (List<String> clinicNameList);
    void onAllClinicLoadFailed (String message);
}
