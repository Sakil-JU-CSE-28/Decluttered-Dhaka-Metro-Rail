package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BuyTicketActivity extends AppCompatActivity {
    private Spinner spinnerTicketType;
    private Spinner spinnerTicketQuantity;
    private Spinner spinnerStartingStation;
    private Spinner spinnerEndingStation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_ticket);
        Intent dataExtractor = getIntent();
        String email = dataExtractor.getStringExtra("email1");
        /**
         * this section is for handling event when purchase button is pressed
         */
        // Initialize the button by finding it's reference in the layout
        Button purchase = findViewById(R.id.btnPurchase);
        // set a listener to handle button pressed
        purchase.setOnClickListener(new View.OnClickListener() {
            /***
             * implementation of Purchase Button
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {

                spinnerTicketType = findViewById(R.id.spinnerTicketType);
                spinnerTicketQuantity = findViewById(R.id.spinnerTicketQuantity);
                spinnerStartingStation = findViewById(R.id.spinnerStartingStation);
                spinnerEndingStation = findViewById(R.id.spinnerEndingStation);
                String selectedTicketType = spinnerTicketType.getSelectedItem().toString();
                int selectedTicketQuantity = Integer.parseInt(spinnerTicketQuantity.getSelectedItem().toString());
                String selectedStartingStation = spinnerStartingStation.getSelectedItem().toString();
                String selectedEndingStation = spinnerEndingStation.getSelectedItem().toString();
                Intent intent = new Intent(BuyTicketActivity.this,PaymentActivity.class);
                intent.putExtra("Type",selectedTicketType);
                intent.putExtra("Quantity",selectedTicketQuantity);
                intent.putExtra("start",selectedStartingStation);
                intent.putExtra("end",selectedEndingStation);
                intent.putExtra("email2",email);
                startActivity(intent);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationViewBuyTckt);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_home) {
                // Handle home action
                Intent intent = new Intent(BuyTicketActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_pre) {
                // Handle pre action
                Intent intent = new Intent(BuyTicketActivity.this, BuyTicketActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_next) {
                // Handle next action
                Intent intent = new Intent(BuyTicketActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_logout) {
                // Handle logout action
                Intent intent = new Intent(BuyTicketActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
}