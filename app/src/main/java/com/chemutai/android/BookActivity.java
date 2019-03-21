package com.chemutai.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {
    
    private CardView cvMakeBooking, cvPayment, cvPrint, cvHistory;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        if (!SharedPrefManager.getInstance(this).isLogin()){//if user is not logged in
                finish();
                startActivity(new Intent(this, LoginActivity.class));
        }
        
        cvMakeBooking = findViewById(R.id.bookingCardView);
        cvPayment = findViewById(R.id.paymentCardView);
        cvPrint = findViewById(R.id.printReceiptCardView);
        cvHistory = findViewById(R.id.historyCardView);
        /*cvSeatNo = findViewById(R.id.seatNoCardView);
        cvLocation = findViewById(R.id.locationCardView);*/
        
        cvMakeBooking.setOnClickListener(this);
        cvPayment.setOnClickListener(this);
        cvPrint.setOnClickListener(this);
        cvHistory.setOnClickListener(this);
        /*cvSeatNo.setOnClickListener(this);
        cvLocation.setOnClickListener(this);*/
        
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
    }

    //logout menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        /*return super.onCreateOptionsMenu(menu); inflate menu item*/
        getMenuInflater().inflate(R.menu.menu, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*return super.onOptionsItemSelected(item);*/
        switch (item.getItemId()){
            case R.id.menuLogout:
                SharedPrefManager.getInstance(this).logout();
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        if (view == cvMakeBooking){
            makeBooking();
        }
        if (view == cvPayment){
            makePayment();
        }
        if (view == cvPrint){
            paymentReceipt();
        }
        if (view == cvHistory){
            paymentHistory();
        }

    }


    private void makeBooking() {
        Intent intent = new Intent(this, MakeBookingActivity.class);
        startActivity(intent);
    }

    private void paymentHistory() {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    private void paymentReceipt() {
        Intent intent = new Intent(this, ReceiptActivity.class);
        startActivity(intent);
    }

    private void makePayment() {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}
