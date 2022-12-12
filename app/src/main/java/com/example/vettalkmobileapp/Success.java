package com.example.vettalkmobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class Success extends AppCompatActivity {

    ImageView notification;
    ImageView menu;
    ImageView pfp;
    TextView username;
    LinearLayout dashboard;

    private String strJson, apiUrl = "http://192.168.1.9/mobileapp/userdata.php";

    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success);

        notification = findViewById(R.id.imageView2);
        menu = findViewById(R.id.imageView3);

        pfp = findViewById(R.id.imageView4);
        username = findViewById(R.id.user_name);
        dashboard = findViewById(R.id.linearLayout);

        dashboard.animate().translationY(-290).setDuration(800).setStartDelay(100);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Success.this, Notifications.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(Success.this);
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
            String name = child.getString("FIRST_NAME");
            username.setText(name);

            //Glide.with(this).load(imgUrl).into(pet_image);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void medicalrecords(View v) {
        Intent intent = new Intent(Success.this, MedicalRecords.class);
        startActivity(intent);
    };

    public void appointments(View v) {
        Intent intent = new Intent(Success.this, SetAppointments.class);
        startActivity(intent);
    };

    public void messages(View v) {
        Intent intent = new Intent(Success.this, Messages.class);
        startActivity(intent);
    };

    public void transactionhistory(View v) {
        Intent intent = new Intent(Success.this, TransactionHistory.class);
        startActivity(intent);
    };

}
