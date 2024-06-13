package com.yogadimas.travelplane.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.yogadimas.travelplane.R;

public class TeamUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_us);

        Toolbar invoiceToolbar = findViewById(R.id.team_us_toolbar);
        invoiceToolbar.setNavigationIcon(R.drawable.ic_back);
        invoiceToolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}