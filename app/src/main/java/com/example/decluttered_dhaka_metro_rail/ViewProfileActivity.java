package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ViewProfileActivity extends AppCompatActivity {

    private ListView listViewTickets;
    private BuyTicketDbController dbController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        Intent dataExtractor = getIntent();
        String user = dataExtractor.getStringExtra("email");

        listViewTickets = findViewById(R.id.listViewTickets);
        dbController = new BuyTicketDbController(this);

        List<Ticket> tickets = dbController.getTicketsByUser(user);

        List<String> ticketDescriptions = new ArrayList<>();
        for (Ticket ticket : tickets) {
            ticketDescriptions.add(ticket.toString()); // Or format the string as you like
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.simple_ticket_item,
                R.id.textViewTicket, // Default ID for the TextView in a simple list item
                ticketDescriptions
        );

        listViewTickets.setAdapter(adapter);

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationViewProfile);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_home) {
                // Handle home action
                Intent intent = new Intent(ViewProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_pre) {
                // Handle pre action
                Intent intent = new Intent(ViewProfileActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_next) {
                // Handle next action
                Intent intent = new Intent(ViewProfileActivity.this, ViewProfileActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_logout) {
                // Handle logout action
                Intent intent = new Intent(ViewProfileActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
}