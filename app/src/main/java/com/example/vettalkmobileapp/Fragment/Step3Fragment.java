package com.example.vettalkmobileapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.vettalkmobileapp.Adapter.ClinicAdapter;
import com.example.vettalkmobileapp.Interface.IAllClinicLoadListener;
import com.example.vettalkmobileapp.Interface.IClinicLoadListener;
import com.example.vettalkmobileapp.Model.Clinic;
import com.example.vettalkmobileapp.R;
import com.example.vettalkmobileapp.SpacesItemDecoration;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class Step3Fragment extends Fragment implements IAllClinicLoadListener, IClinicLoadListener, AdapterView.OnItemSelectedListener, MaterialSpinner.OnItemSelectedListener {

    IAllClinicLoadListener iAllClinicLoadListener;
    IClinicLoadListener iClinicLoadListener;

    @BindView(R.id.servicespinner)
    public
    MaterialSpinner servicespinner;
    @BindView(R.id.recycler_service)
    public
    RecyclerView recycler_service;

    Unbinder unbinder;

    static Step3Fragment instance;

    public static Step3Fragment getInstance() {
        if(instance == null)
            instance = new Step3Fragment();
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        iAllClinicLoadListener = this;
        iClinicLoadListener = this;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_step3,container,false);
        unbinder = ButterKnife.bind(this, itemView);

        initView();
        loadAllServices();
        return itemView;
    }

    private void initView() {
        recycler_service.setHasFixedSize(true);
        recycler_service.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycler_service.addItemDecoration(new SpacesItemDecoration(4));
    }

    private void loadAllServices() {
        List<String> servicelist = new ArrayList<String>();
        servicelist.add("Medical Consultation");
        servicelist.add("Deworming");
        servicelist.add("Vaccination");
        servicelist.add("Spay");
        servicelist.add("Neuter");
        servicelist.add("Home-Service");

        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, servicelist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        servicespinner.setOnItemSelectedListener(this);
        servicespinner.setAdapter(dataAdapter);
    }

    private void loadService(String clinicName) {

    }

    @Override
    public void onAllClinicLoadListener(List<String> clinicNameList) {
        servicespinner.setItems(clinicNameList);
        servicespinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if(position > 0){
                    //
                }
            }
        });
    }


    @Override
    public void onAllClinicLoadFailed(String message) {
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClinicLoadSuccess(List<Clinic> clinicList) {
        ClinicAdapter adapter = new ClinicAdapter(getActivity(), clinicList);
        recycler_service.setAdapter(adapter);

        //dialog.dismiss();
    }

    @Override
    public void onClinicLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), "OnItemSelectedListener : "
                + parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

    }


}