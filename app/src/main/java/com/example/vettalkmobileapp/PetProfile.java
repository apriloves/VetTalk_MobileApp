package com.example.vettalkmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PetProfile extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petprofile);
    }

    public void menuboard(View v) {
        Intent intent = new Intent(PetProfile.this, Success.class);
        startActivity(intent);
    }
}
