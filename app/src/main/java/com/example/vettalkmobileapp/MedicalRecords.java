package com.example.vettalkmobileapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MedicalRecords extends AppCompatActivity{

    ImageView notification;
    ImageView menu;

    TextView pet_name;

    private String strJson, apiUrl = "http://192.168.1.11/mobileapp/petdata.php";

    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.medicalrecords);

        notification = findViewById(R.id.imageView2);
        pet_name = findViewById(R.id.pet_name_title);


        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MedicalRecords.this, Notifications.class);
                startActivity(intent);
            }
        });

        menu = findViewById(R.id.imageView3);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(MedicalRecords.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menupage);
                dialog.show();
            }
        });

        client = new OkHttpClient();
        new GetUserDataRequest().execute();
    }

    public class GetUserDataRequest extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            request = new Request.Builder().url(apiUrl).build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);

            try {
                strJson = response.body().string();
                updateUserData(strJson);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateUserData(String strJson) {
        try {
            JSONArray parent = new JSONArray(strJson);
            JSONObject child = parent.getJSONObject(0);
            //String imgUrl = child.getString("imageLink");
            String name = child.getString("PET_NAME");
            pet_name.setText(name);

            //Glide.with(this).load(imgUrl).into(pet_image);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void menuboard(View v) {
        Intent intent = new Intent(MedicalRecords.this, Success.class);
        startActivity(intent);
    }

    public void petprofile(View v) {
        Intent intent = new Intent(MedicalRecords.this, PetProfile.class);
        startActivity(intent);
    };

    public void consultation(View v) {
        Intent intent = new Intent(MedicalRecords.this, Consultation.class);
        startActivity(intent);
    };

    public void allergies(View v) {
        Intent intent = new Intent(MedicalRecords.this, Allergies.class);
        startActivity(intent);
    };

    public void laboratoryrec(View v) {
        Intent intent = new Intent(MedicalRecords.this, LaboratoryRec.class);
        startActivity(intent);
    };

    public void vaccinations(View v) {
        Intent intent = new Intent(MedicalRecords.this, Vaccinations.class);
        startActivity(intent);
    };
}

