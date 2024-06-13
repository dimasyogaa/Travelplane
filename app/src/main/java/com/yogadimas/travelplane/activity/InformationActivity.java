package com.yogadimas.travelplane.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.yogadimas.travelplane.R;

public class InformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        Toolbar invoiceToolbar = findViewById(R.id.information_toolbar);
        invoiceToolbar.setNavigationIcon(R.drawable.ic_back);
        invoiceToolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();


    }
}