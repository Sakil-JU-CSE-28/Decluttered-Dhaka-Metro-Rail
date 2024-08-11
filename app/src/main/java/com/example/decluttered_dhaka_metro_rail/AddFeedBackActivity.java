package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AddFeedBackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_feed_back);
        Intent inten = getIntent();
        String clickedItem = inten.getStringExtra("click");
        EditText editText = findViewById(R.id.editTextFeedback);
        String txt = editText.toString();
        Button btn = findViewById(R.id.buttonSubmit);
        btn.setOnClickListener(view -> {
            StationExtractor stationExtractor = new StationExtractor(getResources());
            stationExtractor.addFeedBack(txt,clickedItem);
            Toast.makeText(AddFeedBackActivity.this, "Your FeedBack Recorded!! Thank you for your Response", Toast.LENGTH_SHORT).show();
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationViewFeed);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_home) {
                // Handle home action
                Intent intent = new Intent(AddFeedBackActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_pre) {
                // Handle pre action
                Intent intent = new Intent(AddFeedBackActivity.this, ViewStationsActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_next) {
                // Handle next action
                Intent intent = new Intent(AddFeedBackActivity.this, AddFeedBackActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_logout) {
                // Handle logout action
                Intent intent = new Intent(AddFeedBackActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
}