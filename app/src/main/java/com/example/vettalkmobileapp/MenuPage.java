package com.example.vettalkmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MenuPage extends AppCompatActivity {

    //ImageView edit;
    // ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupage);

       //edit = findViewById(R.id.profile);
       // profile = findViewById(R.id.profile);

       //edit.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         Intent intent = new Intent(MenuPage.this, EditPage.class);
       //         startActivity(intent);
       //     }
       // });
       // profile.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View v) {
       //         Intent intent = new Intent(MenuPage.this, EditPage.class);
       //         startActivity(intent);
       //     }
       // });
    }

    public void settings(View v) {
        Intent intent = new Intent(this, MedicalRecords.class);
        startActivity(intent);
    };

    public void helpfeedback(View v) {
        Intent intent = new Intent(this, Messages.class);
        startActivity(intent);
    };

    public void logout(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    };

    public void editprofile(View v) {
        Intent intent = new Intent(this, EditPage.class);
        startActivity(intent);
    };

    public void petprofile(View v) {
        Intent intent = new Intent(this, PetProfile.class);
        startActivity(intent);
    };
}
