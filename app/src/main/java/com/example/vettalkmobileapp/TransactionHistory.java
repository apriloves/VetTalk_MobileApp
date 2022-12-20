package com.example.vettalkmobileapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TransactionHistory extends AppCompatActivity{

    ImageView notification;
    ImageView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transactionhistory);

        notification = findViewById(R.id.imageView2);
        menu = findViewById(R.id.imageView3);

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TransactionHistory.this, Notifications.class);
                startActivity(intent);
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(TransactionHistory.this);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menupage);
                dialog.show();
            }
        });
    }

    public void menuboard(View v) {
        Intent intent = new Intent(TransactionHistory.this, Success.class);
        startActivity(intent);
    }

    public void transact3(View v) {
        Intent intent = new Intent(TransactionHistory.this, Receipt.class);
        startActivity(intent);
    };

    public void transact2(View v) {
        Intent intent = new Intent(TransactionHistory.this, Receipt.class);
        startActivity(intent);
    };

    public void transact1(View v) {
        Intent intent = new Intent(TransactionHistory.this, Receipt.class);
        startActivity(intent);
    };
}



