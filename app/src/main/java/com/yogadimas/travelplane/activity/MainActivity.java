package com.yogadimas.travelplane.activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.content.res.ResourcesCompat;

import com.muddzdev.styleabletoast.StyleableToast;
import com.yogadimas.travelplane.R;
import com.yogadimas.travelplane.model.Passenger;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private EditText edName;
    private TextView tvFrom, tvTo, tvAirlines, tvCabinClass, tvDialogAirport,
            tvDeparture, tvStateRoundTrip, tvReturn, tvPaymentMethod;
    private ImageButton btnCloseDateReturn;
    private ToggleButton btnMoreMenu;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private Switch swRoundTrip;
    private Dialog dialogAirport, dialogAirlines, dialogCabin, dialogPayment;
    private String[] airportList, airlineList, cabinList, paymentList;
    private ListView lvDialogAirport;
    private ArrayAdapter<String> arrayAdapterListView;
    private int cdYear, cdMonth, cdDay, crYear, crMonth, crDay;
    private Calendar calendarDeparture, calendarReturn;
    private LinearLayout linearReturn;
    private Typeface mBold;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogAirport = new Dialog(MainActivity.this);
        dialogAirlines = new Dialog(MainActivity.this);
        dialogCabin = new Dialog(MainActivity.this);
        dialogPayment = new Dialog(MainActivity.this);
        btnMoreMenu = findViewById(R.id.btn_more_menu);
        edName = findViewById(R.id.edt_name);
        tvFrom = findViewById(R.id.tv_edt_from);
        tvTo = findViewById(R.id.tv_edt_to);
        tvAirlines = findViewById(R.id.tv_edt_airlines);
        tvCabinClass = findViewById(R.id.tv_edt_cabin_class);
        tvDeparture = findViewById(R.id.tv_edt_depart);
        tvReturn = findViewById(R.id.tv_edt_return);
        tvPaymentMethod = findViewById(R.id.tv_edt_pay_method);
        swRoundTrip = findViewById(R.id.sw_round_trip);
        tvStateRoundTrip = findViewById(R.id.tv_state_round_trip);
        btnCloseDateReturn = findViewById(R.id.btn_close_date_return);
        linearReturn = findViewById(R.id.linear_return);
        Button btnBuyTicket = findViewById(R.id.btn_buy_ticket);
        mBold = ResourcesCompat.getFont(MainActivity.this, R.font.mbold);
        airportList = getResources().getStringArray(R.array.airport);
        airlineList = getResources().getStringArray(R.array.airlines);
        cabinList = getResources().getStringArray(R.array.cabin);
        paymentList = getResources().getStringArray(R.array.payment);
        btnMoreMenu.setOnClickListener(this);
        tvFrom.setOnClickListener(this);
        tvTo.setOnClickListener(this);
        tvAirlines.setOnClickListener(this);
        tvCabinClass.setOnClickListener(this);
        tvDeparture.setOnClickListener(this);
        tvReturn.setOnClickListener(this);
        btnCloseDateReturn.setOnClickListener(this);
        tvPaymentMethod.setOnClickListener(this);
        btnBuyTicket.setOnClickListener(this);
        swRoundTrip.setOnCheckedChangeListener(this);
        tvStateRoundTrip.setText(R.string.state_switch_off);
        linearReturn.setVisibility(View.GONE);
        tvReturn.setEnabled(false);
        tvReturn.setText("");
        btnCloseDateReturn.setVisibility(View.GONE);
        btnCloseDateReturn.setEnabled(false);
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_more_menu) {
            showMenu();
        } else if (view.getId() == R.id.tv_edt_from) {
            dialogAirport();
            from();
        } else if (view.getId() == R.id.tv_edt_to) {
            dialogAirport();
            to();
        } else if (view.getId() == R.id.tv_edt_airlines) {
            dialogAirlines();
        } else if (view.getId() == R.id.tv_edt_cabin_class) {
            dialogCabin();
        } else if (view.getId() == R.id.tv_edt_depart) {
            getDeparture();
            getCurrentDateDeparture();
        } else if (view.getId() == R.id.tv_edt_return) {
            getReturn();
            getCurrentDateReturn();
        } else if (view.getId() == R.id.btn_close_date_return) {
            tvReturn.setText("");
            btnCloseDateReturn.setVisibility(View.GONE);
            btnCloseDateReturn.setEnabled(false);
        } else if (view.getId() == R.id.tv_edt_pay_method) {
            dialogPayment();
        } else if (view.getId() == R.id.btn_buy_ticket) {
            process();
        }
    }

    private void getCurrentDateDeparture() {
        if (cdYear == 0 || cdMonth == 0 || cdDay == 0) {
            calendarDeparture = Calendar.getInstance();
            cdYear = calendarDeparture.get(Calendar.YEAR);
            cdMonth = calendarDeparture.get(Calendar.MONTH);
            cdDay = calendarDeparture.get(Calendar.DAY_OF_MONTH);
        }
    }

    private void getCurrentDateReturn() {
        if (crYear == 0 || crMonth == 0 || crDay == 0) {
            calendarReturn = Calendar.getInstance();
            crYear = calendarReturn.get(Calendar.YEAR);
            crMonth = calendarReturn.get(Calendar.MONTH);
            crDay = calendarReturn.get(Calendar.DAY_OF_MONTH);
        }
    }

    private String formatDate(Date date) {

        String s = getString(R.string.format_date);

        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(s);

        return simpleDateFormat.format(date);
    }

    private Calendar getMaxDate() {
        Calendar maxDate = Calendar.getInstance();
        maxDate.add(Calendar.MONTH, 3);
        return maxDate;
    }

    private long getMaxDateReturn() {
        return calendarDeparture.getTimeInMillis() + 604800000L;
    }

    private Calendar getMinDate() {
        return calendarDeparture;
    }

    private void getDeparture() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, (datePicker, year, month, day) -> {
            cdYear = datePicker.getYear();
            cdMonth = datePicker.getMonth();
            cdDay = datePicker.getDayOfMonth();
            calendarDeparture.set(cdYear, cdMonth, cdDay);
            tvDeparture.setText(formatDate(calendarDeparture.getTime()));
        }, cdYear, cdMonth, cdDay);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.getDatePicker().setMaxDate(getMaxDate().getTimeInMillis());
        datePickerDialog.show();
    }

    private void getReturn() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this, (datePicker, year, month, day) -> {
            crYear = datePicker.getYear();
            crMonth = datePicker.getMonth();
            crDay = datePicker.getDayOfMonth();
            calendarReturn.set(crYear, crMonth, crDay);
            tvReturn.setText(formatDate(calendarReturn.getTime()));
            btnCloseDateReturn.setVisibility(View.VISIBLE);
            btnCloseDateReturn.setEnabled(true);
        }, crYear, crMonth, crDay);
        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.cancel), (dialog, which) -> {
            if (which == DialogInterface.BUTTON_NEGATIVE) {
                if (tvReturn.getText().toString().isEmpty() || tvReturn.getText().toString().equals("")) {
                    btnCloseDateReturn.setVisibility(View.GONE);
                    btnCloseDateReturn.setEnabled(false);
                }
            }
        });
        datePickerDialog.getDatePicker().setMinDate(getMinDate().getTimeInMillis());
        datePickerDialog.getDatePicker().setMaxDate(getMaxDateReturn());
        datePickerDialog.show();
    }

    private void showDialog(Dialog dialog, int layoutResID) {
        dialog.setContentView(layoutResID);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void dialogAirport() {
        showDialog(dialogAirport, R.layout.dialog_airport);
        tvDialogAirport = dialogAirport.findViewById(R.id.tv_dialog_airport);
        lvDialogAirport = dialogAirport.findViewById(R.id.lv_dialog_airport);
        arrayAdapterListView = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, airportList);
        lvDialogAirport.setAdapter(arrayAdapterListView);
    }

    private void from() {
        tvDialogAirport.setText(R.string.hint_select_departure_airport);
        tvDialogAirport.setTypeface(mBold, Typeface.BOLD);
        lvDialogAirport.setOnItemClickListener((adapterView, view, i, l) -> {
            tvFrom.setText(arrayAdapterListView.getItem(i));
            dialogAirport.dismiss();
        });
    }

    private void to() {
        tvDialogAirport.setText(R.string.hint_select_destination_airport);
        tvDialogAirport.setTypeface(mBold, Typeface.BOLD);
        lvDialogAirport.setOnItemClickListener((adapterView, view, i, l) -> {
            tvTo.setText(arrayAdapterListView.getItem(i));
            dialogAirport.dismiss();
        });
    }

    private void dialogAirlines() {
        showDialog(dialogAirlines, R.layout.dialog_airlines);

        ListView lvDialogAirline = dialogAirlines.findViewById(R.id.lv_dialog_airlines);

        arrayAdapterListView = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, airlineList);

        lvDialogAirline.setAdapter(arrayAdapterListView);

        lvDialogAirline.setOnItemClickListener((adapterView, view, i, l) -> {
            tvAirlines.setText(arrayAdapterListView.getItem(i));
            dialogAirlines.dismiss();
        });
    }

    private void dialogCabin() {
        showDialog(dialogCabin, R.layout.dialog_cabin);


        ListView lvDialogCabin = dialogCabin.findViewById(R.id.lv_dialog_cabin);

        arrayAdapterListView = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, cabinList);


        lvDialogCabin.setAdapter(arrayAdapterListView);

        lvDialogCabin.setOnItemClickListener((adapterView, view, i, l) -> {
            tvCabinClass.setText(arrayAdapterListView.getItem(i));
            dialogCabin.dismiss();
        });
    }

    private void dialogPayment() {
        showDialog(dialogPayment, R.layout.dialog_payment);

        ListView lvDialogPayment = dialogPayment.findViewById(R.id.lv_dialog_payment);

        arrayAdapterListView = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, paymentList);

        lvDialogPayment.setAdapter(arrayAdapterListView);

        lvDialogPayment.setOnItemClickListener((adapterView, view, i, l) -> {
            tvPaymentMethod.setText(arrayAdapterListView.getItem(i));
            dialogPayment.dismiss();
        });
    }

    private void process() {

        String sName = edName.getText().toString();

        String sFrom = tvFrom.getText().toString();

        String sTo = tvTo.getText().toString();

        String sAirline = tvAirlines.getText().toString();

        String sCabin = tvCabinClass.getText().toString();

        String sDateDeparture = tvDeparture.getText().toString();

        String sDateReturn = tvReturn.getText().toString();

        String sPaymentMethod = tvPaymentMethod.getText().toString();

        String sTangerang = "Soekarno-Hatta (TANGERANG)";
        String sJakarta = "Halim Perdanakusuma (JAKARTA)";
        String sSemarang = "Ahmad Yani (SEMARANG)";
        String sSidoarjo = "Juanda (SIDOARJO)";

        String sTimeDeparture;
        String sTimeTravel;
        String sTimeReturn;


        if (sName.isEmpty() || sFrom.isEmpty() || sAirline.isEmpty() ||
                sCabin.isEmpty() || sDateDeparture.isEmpty() || sPaymentMethod.isEmpty()) {
            StyleableToast.makeText(MainActivity.this, getString(R.string.toast_empty_input),
                    Toast.LENGTH_SHORT, R.style.ToastTheme).show();
        } else {
            if (sFrom.equals(sTangerang)) {
                sTimeDeparture = "06:00 WIB";
                sTimeReturn = "20.00 WIB";
                if (sTo.equals(sJakarta)) {
                    sTimeTravel = getString(R.string.time_travel_15_minutes);
                } else if (sTo.equals(sSemarang)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour_10_minutes);
                } else if (sTo.equals(sSidoarjo)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour_30_minutes);
                } else {
                    sTimeTravel = "--";
                }
            } else if (sFrom.equals(sJakarta)) {
                sTimeDeparture = "07:00 WIB";
                sTimeReturn = "21.00 WIB";
                if (sTo.equals(sTangerang)) {
                    sTimeTravel = getString(R.string.time_travel_15_minutes);
                } else if (sTo.equals(sSemarang)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour);
                } else if (sTo.equals(sSidoarjo)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour_25_minutes);
                } else {
                    sTimeTravel = "--";
                }
            } else if (sFrom.equals(sSemarang)) {
                sTimeDeparture = "08:00 WIB";
                sTimeReturn = "22.00 WIB";
                if (sTo.equals(sSidoarjo)) {
                    sTimeTravel = getString(R.string.time_travel_30_minutes);
                } else if (sTo.equals(sJakarta)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour);
                } else if (sTo.equals(sTangerang)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour_10_minutes);
                } else {
                    sTimeTravel = "--";
                }
            } else {
                sTimeDeparture = "09:00 WIB";
                sTimeReturn = "23.00 WIB";
                if (sTo.equals(sSemarang)) {
                    sTimeTravel = getString(R.string.time_travel_30_minutes);
                } else if (sTo.equals(sJakarta)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour_25_minutes);
                } else if (sTo.equals(sTangerang)) {
                    sTimeTravel = getString(R.string.time_travel_1_hour_30_minutes);
                } else {
                    sTimeTravel = "--";
                }
            }
            double ddPriceAirline = 0;
            switch (sAirline) {
                case "Pelita Air":
                    if (getString(R.string.cabin_class_economy).equals(sCabin)) {
                        ddPriceAirline = 2000000;
                    } else if (getString(R.string.cabin_class_economy_premium).equals(sCabin)) {
                        ddPriceAirline = 4000000;
                    } else if (getString(R.string.cabin_class_business).equals(sCabin)) {
                        ddPriceAirline = 6000000;
                    } else if (getString(R.string.cabin_class_first).equals(sCabin)) {
                        ddPriceAirline = 8000000;
                    }
                    break;
                case "Batik Air":
                    if (getString(R.string.cabin_class_economy).equals(sCabin)) {
                        ddPriceAirline = 1500000;
                    } else if (getString(R.string.cabin_class_economy_premium).equals(sCabin)) {
                        ddPriceAirline = 3500000;
                    } else if (getString(R.string.cabin_class_business).equals(sCabin)) {
                        ddPriceAirline = 5500000;
                    } else if (getString(R.string.cabin_class_first).equals(sCabin)) {
                        ddPriceAirline = 7500000;
                    }
                    break;
                case "Citilink":
                    if (getString(R.string.cabin_class_economy).equals(sCabin)) {
                        ddPriceAirline = 1000000;
                    } else if (getString(R.string.cabin_class_economy_premium).equals(sCabin)) {
                        ddPriceAirline = 3000000;
                    } else if (getString(R.string.cabin_class_business).equals(sCabin)) {
                        ddPriceAirline = 5000000;
                    } else if (getString(R.string.cabin_class_first).equals(sCabin)) {
                        ddPriceAirline = 7000000;
                    }
                    break;
                case "Lion Air":
                    if (getString(R.string.cabin_class_economy).equals(sCabin)) {
                        ddPriceAirline = 500000;
                    } else if (getString(R.string.cabin_class_economy_premium).equals(sCabin)) {
                        ddPriceAirline = 2500000;
                    } else if (getString(R.string.cabin_class_business).equals(sCabin)) {
                        ddPriceAirline = 4500000;
                    } else if (getString(R.string.cabin_class_first).equals(sCabin)) {
                        ddPriceAirline = 6500000;
                    }
                    break;
                default:
                    ddPriceAirline = 0;
                    break;
            }
            String sAccountNumber;
            switch (sPaymentMethod) {
                case "Bank BCA":
                    sAccountNumber = "070 345 657 3";
                    break;
                case "Bank Mandiri":
                    sAccountNumber = "126 657 435 8";
                    break;
                case "Bank BNI":
                    sAccountNumber = "221 675 892 1";
                    break;
                default:
                    sAccountNumber = "008 301 287 4";
                    break;
            }
            double drPriceAirline, totalPriceAirline;
            String sDiscount;

            if (sDateReturn.equals("") || sDateReturn.isEmpty()) {
                sDiscount = "0%";
                totalPriceAirline = ddPriceAirline;
            } else {
                sDiscount = "5%";
                drPriceAirline = ddPriceAirline * 2;
                totalPriceAirline = drPriceAirline - (drPriceAirline * 0.05);
            }
            Locale localeID = new Locale("in", "ID");
            NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
            String sPriceAirline = formatRupiah.format(ddPriceAirline);
            String sTotal = formatRupiah.format(totalPriceAirline);
            Passenger passenger = new Passenger();
            passenger.setNamePassenger(sName);
            passenger.setAirportDeparture(sFrom);
            passenger.setAirportReturn(sTo);
            passenger.setAirlineName(sAirline);
            passenger.setCabinClass(sCabin);
            passenger.setDateDeparture(sDateDeparture);
            passenger.setTimeDeparture(sTimeDeparture);
            passenger.setTimeTravel(sTimeTravel);
            passenger.setDateReturn(sDateReturn);
            passenger.setTimeReturn(sTimeReturn);
            passenger.setPaymentMethod(sPaymentMethod);
            passenger.setAccountNumber(sAccountNumber);
            passenger.setDiscount(sDiscount);
            passenger.setAirlinePrice(sPriceAirline);
            passenger.setTotal(sTotal);
            Intent goToInvoice = new Intent(MainActivity.this, InvoiceActivity.class);
            goToInvoice.putExtra(InvoiceActivity.EXTRA_PASSENGER, passenger);
            startActivity(goToInvoice);
        }
    }

    private void showMenu() {
        if (btnMoreMenu.isChecked()) {
            inflate();
        }
    }

    @SuppressLint("NonConstantResourceId")
    private void inflate() {
        PopupMenu popup = new PopupMenu(MainActivity.this, btnMoreMenu);
        popup.getMenuInflater().inflate(R.menu.menu_option, popup.getMenu());
        setTheme(R.style.MenuTheme);
        popup.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.item_team_us:
                    Intent goToTimeUs = new Intent(MainActivity.this, TeamUsActivity.class);
                    startActivity(goToTimeUs);
                    break;
                case R.id.item_language:
                    Intent goToLanguage = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                    startActivity(goToLanguage);
                    break;
                case R.id.item_information_app:
                    Intent goToInformation = new Intent(MainActivity.this, InformationActivity.class);
                    startActivity(goToInformation);
                    break;
            }
            return true;
        });
        popup.show();
        btnMoreMenu.setChecked(true);
        popup.setOnDismissListener(menu -> btnMoreMenu.setChecked(false));


    }
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (tvDeparture.getText().toString().isEmpty() || tvDeparture.getText().toString().equals("")) {
            swRoundTrip.setChecked(false);
            StyleableToast.makeText(MainActivity.this, getString(R.string.toast_empty_departure_date),
                    Toast.LENGTH_SHORT, R.style.ToastTheme).show();
        } else {
            if (compoundButton.isChecked()) {
                swRoundTrip.setChecked(true);
                tvStateRoundTrip.setText(R.string.state_switch_on);
                linearReturn.setVisibility(View.VISIBLE);
                tvReturn.setEnabled(true);
                btnCloseDateReturn.setVisibility(View.GONE);
                btnCloseDateReturn.setEnabled(false);
            } else {
                swRoundTrip.setChecked(false);
                tvStateRoundTrip.setText(R.string.state_switch_off);
                linearReturn.setVisibility(View.GONE);
                tvReturn.setEnabled(false);
            }
            tvReturn.setText("");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    protected void onRestart() {
        recreate();
        super.onRestart();
    }

    @Override
    protected void onPause() {
        swRoundTrip.setChecked(false);
        super.onPause();
    }
}