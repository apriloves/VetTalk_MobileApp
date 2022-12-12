package com.example.vettalkmobileapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class PetProfile extends AppCompatActivity {

    //ImageView pet_image;
    TextView pet_gender, pet_categ, pet_color, pet_breed, pet_age, pet_weight;
    private String strJson, apiUrl = "http://192.168.1.9/mobileapp/petdata.php";

    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;
    private ProgressDialog progressDialog;
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petprofile);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please Wait.....");
        progressDialog.setCanceledOnTouchOutside(false);

        //pet_name = findViewById(R.id.petname);
        pet_gender = findViewById(R.id.petgender);
        pet_categ = findViewById(R.id.petcateg);
        pet_color = findViewById(R.id.petcolor);
        pet_breed = findViewById(R.id.petbreed);
        pet_age = findViewById(R.id.petage);
        pet_weight = findViewById(R.id.petweight);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        progressDialog.show();
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
            String gender = child.getString("PET_GENDER");
            String categ = child.getString("PET_CATEG");
            String color = child.getString("PET_COLOR");
            String breed = child.getString("BREED");
            String age = child.getString("PET_AGE");
            String weight = child.getString("PET_WEIGHT");

            pet_gender.setText(gender);
            pet_categ.setText(categ);
            pet_color.setText(color);
            pet_breed.setText(breed);
            pet_age.setText(age);
            pet_weight.setText(weight);
            progressDialog.hide();
            collapsingToolbarLayout.setTitle(name);

            //Glide.with(this).load(imgUrl).into(pet_image);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
