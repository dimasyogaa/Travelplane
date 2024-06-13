package com.yogadimas.travelplane.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.yogadimas.travelplane.R;
import com.yogadimas.travelplane.model.Passenger;

public class InvoiceActivity extends AppCompatActivity {
    public static final String EXTRA_PASSENGER = "extra_passenger";
    private ImageView ivInvoiceLogoAirline;
    private TextView tvInvoiceName, tvInvoiceFrom, tvInvoiceTo, tvInvoiceAirline, tvInvoiceCabin,
            tvInvoiceDateDeparture, tvInvoiceTimeDeparture, tvInvoiceTimeTravel, tvInvoiceDateReturn,
            tvInvoiceTimeReturn, tvInvoicePriceAirline,
            tvInvoiceDiscount, tvInvoiceTotal, tvPaymentMethod, tvAccountNumber, tvInvoiceRoundTrip;
    private ToggleButton btnInvoiceDetail;
    private LinearLayout linearTimeTravel, linearInvoiceDetail;
    private SharedPreferences saveState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        Toolbar invoiceToolbar = findViewById(R.id.invoice_toolbar);
        invoiceToolbar.setNavigationIcon(R.drawable.ic_back);
        invoiceToolbar.setNavigationOnClickListener(v -> finish());
        tvInvoiceName = findViewById(R.id.tv_invoice_name);
        tvInvoiceFrom = findViewById(R.id.tv_invoice_airport_departure);
        tvInvoiceTo = findViewById(R.id.tv_invoice_airport_return);
        ivInvoiceLogoAirline = findViewById(R.id.iv_invoice_logo_airline);
        tvInvoiceAirline = findViewById(R.id.tv_invoice_airline_name);
        tvInvoiceCabin = findViewById(R.id.tv_invoice_airline_cabin);
        tvInvoiceDateDeparture = findViewById(R.id.tv_invoice_date_departure);
        tvInvoiceTimeDeparture = findViewById(R.id.tv_invoice_time_departure);
        tvInvoiceTimeTravel = findViewById(R.id.tv_invoice_time_travel);
        tvInvoiceDateReturn = findViewById(R.id.tv_invoice_date_return);
        tvInvoiceTimeReturn = findViewById(R.id.tv_invoice_time_return);
        tvInvoicePriceAirline = findViewById(R.id.tv_invoice_airline_price);
        tvInvoiceDiscount = findViewById(R.id.tv_invoice_discount);
        tvInvoiceTotal = findViewById(R.id.tv_invoice_total);
        tvPaymentMethod = findViewById(R.id.tv_invoice_payment_method);
        tvAccountNumber = findViewById(R.id.tv_invoice_account_number);
        tvInvoiceRoundTrip = findViewById(R.id.tv_invoice_round_trip);
        btnInvoiceDetail = findViewById(R.id.btn_invoice_detail);
        linearTimeTravel = findViewById(R.id.linear_time_travel);
        linearInvoiceDetail = findViewById(R.id.linear_invoice_detail);
        tvInvoiceRoundTrip.setVisibility(View.GONE);
        saveState = getSharedPreferences("saveToggleButton", MODE_PRIVATE);

        if (saveState.getString("KEY", null) != null) {
            linearTimeTravel.setVisibility(View.VISIBLE);
            linearInvoiceDetail.setVisibility(View.VISIBLE);
            btnInvoiceDetail.setChecked(true);
        } else {
            linearTimeTravel.setVisibility(View.GONE);
            linearInvoiceDetail.setVisibility(View.GONE);
            btnInvoiceDetail.setChecked(false);
        }

        Passenger passenger = getIntent().getParcelableExtra(EXTRA_PASSENGER);
        if (passenger != null) {
            setInvoice(passenger);
        }

        btnInvoiceDetail.setOnClickListener(view -> {
            if (btnInvoiceDetail.isChecked()) {
                SharedPreferences.Editor editorSaveToggle = saveState.edit();
                editorSaveToggle.putString("KEY", "VALUE");
                editorSaveToggle.apply();
                linearTimeTravel.setVisibility(View.VISIBLE);
                linearInvoiceDetail.setVisibility(View.VISIBLE);
            } else {
                SharedPreferences.Editor editorSaveToggle = saveState.edit();
                editorSaveToggle.remove("KEY");
                editorSaveToggle.apply();
                linearTimeTravel.setVisibility(View.GONE);
                linearInvoiceDetail.setVisibility(View.GONE);
            }
        });
    }

    private void setInvoice(Passenger passenger) {
        tvInvoiceName.setText(passenger.getNamePassenger());
        tvInvoiceFrom.setText(passenger.getAirportDeparture());
        tvInvoiceTo.setText(passenger.getAirportReturn());
        tvInvoiceAirline.setText(passenger.getAirlineName());

        switch (passenger.getAirlineName()) {
            case "Pelita Air":
                ivInvoiceLogoAirline.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_pelita_air));
                break;
            case "Batik Air":
                ivInvoiceLogoAirline.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_batik_air));
                break;
            case "Citilink":
                ivInvoiceLogoAirline.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_citilink));
                break;
            default:
                ivInvoiceLogoAirline.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_lion_air));
                break;
        }
        tvInvoiceCabin.setText(passenger.getCabinClass());
        tvInvoiceDateDeparture.setText(passenger.getDateDeparture());
        tvInvoiceTimeTravel.setText(passenger.getTimeTravel());
        tvInvoiceTimeDeparture.setText(passenger.getTimeDeparture());


        if (passenger.getDateReturn().isEmpty() || passenger.getDateReturn().equals("")) {
            tvInvoiceRoundTrip.setVisibility(View.GONE);
            tvInvoiceDateReturn.setText("--");
            tvInvoiceTimeReturn.setText("--");
        } else {
            tvInvoiceRoundTrip.setVisibility(View.VISIBLE);
            tvInvoiceDateReturn.setText(passenger.getDateReturn());
            tvInvoiceTimeReturn.setText(passenger.getTimeReturn());
        }
        tvInvoicePriceAirline.setText(passenger.getAirlinePrice());
        tvInvoiceDiscount.setText(passenger.getDiscount());
        tvInvoiceTotal.setText(passenger.getTotal());
        tvPaymentMethod.setText(passenger.getPaymentMethod());
        tvAccountNumber.setText(passenger.getAccountNumber());
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}