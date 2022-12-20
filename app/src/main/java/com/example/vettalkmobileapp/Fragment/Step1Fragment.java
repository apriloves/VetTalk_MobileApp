package com.example.vettalkmobileapp.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class Step1Fragment extends Fragment implements IAllClinicLoadListener, IClinicLoadListener, AdapterView.OnItemSelectedListener, MaterialSpinner.OnItemSelectedListener {

    //Variables
    //CollectionReference clinics;

    IAllClinicLoadListener iAllClinicLoadListener;
    IClinicLoadListener iClinicLoadListener;

    AlertDialog alertDialog;

    @BindView(R.id.clinicspinner)
    public
    MaterialSpinner clinicspinner;
    @BindView(R.id.recycler_clinic)
    public
    RecyclerView recycler_clinic;
   // @BindView(R.id.clinicbutton)
   // public
    //CardView clinicbutton;

    Unbinder unbinder;
    ProgressBar progressBar;
    AlertDialog dialog;

    TextView clinic;

    int positionOfSelectedDataFromSpinner;

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
        iClinicLoadListener = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View itemView = inflater.inflate(R.layout.fragment_step1,container,false);
        unbinder = ButterKnife.bind(this, itemView);

        initView();
        loadAllClinic();
        return itemView;
    }

    private void initView() {
        recycler_clinic.setHasFixedSize(true);
        recycler_clinic.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recycler_clinic.addItemDecoration(new SpacesItemDecoration(4));
    }

    private void loadAllClinic() {
        List<String> cliniclist = new ArrayList<String>();
        cliniclist.add("Veterinary Clinic");
        cliniclist.add("Laboratory Clinic");

        ArrayAdapter<String> dataAdapter;
        //dataAdapter = new ClinicAdapter(cliniclist);
        dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, cliniclist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        clinicspinner.setAdapter(dataAdapter);
        clinicspinner.setOnItemSelectedListener(this);
        /*clinicspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("")){
                    clinic = "";

                }else {
                    clinic= parent.getItemAtPosition(position).toString();
                }
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(!exchangeRateInput.isEmpty() && !currencyName.isEmpty());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selected = parent.getItemAtPosition(position).toString();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void loadClinic(String clinicName) {

    }

    @Override
    public void onAllClinicLoadListener(List<String> clinicNameList) {
        clinicspinner.setItems(clinicNameList);
        clinicspinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
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
        recycler_clinic.setAdapter(adapter);

        //dialog.dismiss();
    }

    @Override
    public void onClinicLoadFailed(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

    }
}