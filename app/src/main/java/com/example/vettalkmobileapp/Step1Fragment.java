package com.example.vettalkmobileapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Step1Fragment extends Fragment implements IAllClinicLoadListener {

    //Variables
    //CollectionReference clinics;

    IAllClinicLoadListener iAllClinicLoadListener;

    @BindView(R.id.clinicspinner)
    MaterialSpinner clinicspinner;
    @BindView(R.id.recycler_clinic)
    RecyclerView recycler_clinic;

    Unbinder unbinder;
    IClinicLoadListener iClinicLoadListener;

    static Step1Fragment instance;



    public static Step1Fragment getInstance() {
        if(instance == null)
            instance = new Step1Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iAllClinicLoadListener = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_step1,container,false);
        unbinder = ButterKnife.bind(this, itemView);

        loadAllClinic();
        return itemView;
    }

    private void loadAllClinic() {

    }

    @Override
    public void onAllClinicLoadListener(List<String> clinicNameList) {

    }

    @Override
    public void onAllClinicLoadFailed(String message) {

    }
}