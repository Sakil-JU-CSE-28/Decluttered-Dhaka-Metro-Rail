package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PaymentActivity extends AppCompatActivity {

    private BuyTicketDbController db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Intent dataExtractor = getIntent();
        String email = dataExtractor.getStringExtra("email2");

        String selectedTicketType = dataExtractor.getStringExtra("Type");
        int selectedTicketQuantity = dataExtractor.getIntExtra("Quantity",0);
        String selectedStartingStation = dataExtractor.getStringExtra("start");
        String selectedEndingStation = dataExtractor.getStringExtra("end");

        Context context = getApplicationContext();
        db = new BuyTicketDbController(context);


        Button bkash = findViewById(R.id.buttonSelectBkash);
        Button nagad = findViewById(R.id.buttonSelectNagad);
        Button rocket = findViewById(R.id.buttonSelectRocket);

        bkash.setOnClickListener(view -> {

           long sign = buyTicket(selectedTicketType,selectedTicketQuantity,selectedStartingStation,selectedEndingStation,email);
            if(sign != -1){
                Toast.makeText(context, "Purchase successful!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this,BuyTicketActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(context, "Error!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this,BuyTicketActivity.class);
                startActivity(intent);
            }

        });

        rocket.setOnClickListener(view -> {
            long sign = buyTicket(selectedTicketType,selectedTicketQuantity,selectedStartingStation,selectedEndingStation,email);
            if(sign != -1){
                Toast.makeText(getApplicationContext(), "Purchase successful!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this,BuyTicketActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this,BuyTicketActivity.class);
                startActivity(intent);
            }

        });

        nagad.setOnClickListener(view -> {
            long sign = buyTicket(selectedTicketType,selectedTicketQuantity,selectedStartingStation,selectedEndingStation,email);
            if(sign != -1){
                Toast.makeText(getApplicationContext(), "Purchase successful!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this,BuyTicketActivity.class);
                startActivity(intent);
            }
            else{
                Toast.makeText(getApplicationContext(), "Error!!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PaymentActivity.this,BuyTicketActivity.class);
                startActivity(intent);
            }

        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationViewPay);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_home) {
                // Handle home action
                Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_pre) {
                // Handle pre action
                Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_next) {
                // Handle next action
                Intent intent = new Intent(PaymentActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_logout) {
                // Handle logout action
                Intent intent = new Intent(PaymentActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }

    public long buyTicket(String selectedTicketType,int selectedTicketQuantity,String selectedStartingStation,String selectedEndingStation,String user){
        long  sign = db.addTicket(selectedTicketType,selectedTicketQuantity,selectedStartingStation,selectedEndingStation,user);
        return sign;
    }
}