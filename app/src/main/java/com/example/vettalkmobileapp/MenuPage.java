package com.example.vettalkmobileapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MenuPage extends AppCompatActivity {

    ImageView profile;
    TextView username;

    private String strJson, apiUrl = "http://192.168.1.6/mobileapp/userdata.php";
    //apiUrl1 = "http://192.168.1.9/mobileapp/userdata.php";

    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menupage);

        //pet_name = findViewById(R.id.petname1);
        username = findViewById(R.id.usernameview);

        client = new OkHttpClient();
        new GetUserDataRequest().execute();

       profile = findViewById(R.id.profile);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuPage.this, EditPage.class);
                startActivity(intent);
            }
        });
    }

    public class GetUserDataRequest extends AsyncTask<Void,Void,Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            request = new Request.Builder().url(apiUrl).build();
            //request2 = new Request.Builder().url(apiUrl1).build();

            try {
                response = client.newCall(request).execute();
                //response2 = client.newCall(request2).execute();
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
                //strJson2 = response2.body().string();
                updateUserData(strJson);
                //updateUserData(strJson2);
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
            //String petname = child.getString("PET_NAME");
            String user_name = child.getString("FIRST_NAME");

            //pet_name.setText(petname);
            username.setText(user_name);

            //Glide.with(this).load(imgUrl).into(pet_image);

        } catch (JSONException e) {
            e.printStackTrace();
        }

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

    //public void editprofile(View v) {
    //    Intent intent = new Intent(this, EditPage.class);
    //    startActivity(intent);
    //};

    public void petprofile(View v) {
        Intent intent = new Intent(this, PetProfile.class);
        startActivity(intent);
    };
}
