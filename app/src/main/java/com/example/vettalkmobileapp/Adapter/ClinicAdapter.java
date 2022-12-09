package com.example.vettalkmobileapp.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vettalkmobileapp.Interface.IRecyclerItemSelectedListener;
import com.example.vettalkmobileapp.Model.Clinic;
import com.example.vettalkmobileapp.R;

import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.List;

public class ClinicAdapter extends RecyclerView.Adapter<ClinicAdapter.myViewHolder> {


    Context context;
    List<Clinic> clinicList;
    List<CardView> cardViewList;

    public ClinicAdapter(Context context, List<Clinic> clinicList) {
        this.context = context;
        this.clinicList = clinicList;
        cardViewList = new ArrayList<>();
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
        if(!cardViewList.contains(myViewHolder.clinicbutton))
            cardViewList.add(myViewHolder.clinicbutton);

        myViewHolder.setiRecyclerItemSelectedListener(new IRecyclerItemSelectedListener() {
            @Override
            public void onItemSelectedListener(View view, int pos) {
                for (CardView cardView:cardViewList)
                    cardView.setCardBackgroundColor(context.getResources().getColor(android.R.color.white));
            }
        });
    }

    @Override
    public int getItemCount() {
        return clinicList.size();
    }

    public static class myViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener {
        static TextView clinic_name, clinic_address;
        static CardView clinicbutton;
        private static myViewHolder setiRecyclerItemSelectedListener;

        IRecyclerItemSelectedListener iRecyclerItemSelectedListener;

        public static void setiRecyclerItemSelectedListener(IRecyclerItemSelectedListener iRecyclerItemSelectedListener) {
            myViewHolder.setiRecyclerItemSelectedListener.iRecyclerItemSelectedListener = iRecyclerItemSelectedListener;
        }

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            clinicbutton = (CardView)itemView.findViewById(R.id.clinicbutton);
            clinic_address = (TextView)itemView.findViewById(R.id.clinic_address);
            clinic_name = (TextView)itemView.findViewById(R.id.clinic_name);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
