package com.example.vettalkmobileapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.CollationElementIterator;
import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.myViewHolder> {

    Context context;
    List<Clinic> clinicList;

    public ClinicAdapter(Context context, List<Clinic> clinicList) {
        this.context = context;
        this.clinicList = clinicList;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.layout_clinic, parent, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        myViewHolder.clinic_name.setText(clinicList.get(position).getName());
        myViewHolder.clinic_address.setText(clinicList.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return clinicList.size();
    }

    public static class myViewHolder extends  RecyclerView.ViewHolder {
        static TextView clinic_name, clinic_address;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            clinic_address = (TextView)itemView.findViewById(R.id.clinic_address);
            clinic_name = (TextView)itemView.findViewById(R.id.clinic_name);
        }
    }

}
