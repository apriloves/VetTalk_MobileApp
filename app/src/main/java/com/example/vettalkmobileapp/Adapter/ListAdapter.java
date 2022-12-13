package com.example.vettalkmobileapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.vettalkmobileapp.PetDetails;
import com.example.vettalkmobileapp.R;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<PetDetails> {

    public ListAdapter (Context context, ArrayList<PetDetails> userArrayList){

        super (context, R.layout.petdetailview, userArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PetDetails petDetails = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.petdetailview, parent, false);
        }

        //ImageView imageView = convertView.findViewById(R.id.profileimage);
        TextView petname = convertView.findViewById(R.id.pet_name_title);

        return super.getView(position, convertView, parent);
    }
}
