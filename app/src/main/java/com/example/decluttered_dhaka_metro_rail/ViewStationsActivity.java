package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Set;

/**
 * Activity class for viewing stations in the Dhaka Metro Rail application.
 * This activity displays a list of stations available in the metro rail network.
 */

public class ViewStationsActivity extends AppCompatActivity {

    /**
     * Called when the activity is starting. This is where most initialization
     * should go: calling setContentView(int) to inflate the activity's UI,
     * using findViewById(int) to programmatically interact with widgets in the UI,
     * and binding data to lists or other widgets.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     *     being shut down then this Bundle contains the data it most recently supplied in
     *     onSaveInstanceState(Bundle). Note: Otherwise it is null.
     */
    public String clickedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_stations);
        // Get the stations from the StationExtractor class
        StationExtractor stationExtractor = new StationExtractor(getResources());
        Set<String> stations = stationExtractor.getStations();
        // Get the container LinearLayout from the layout
        LinearLayout container = findViewById(R.id.containerStation);

        // Iterate through each metro item
        for (String item1 : stations) {
            // Create a new CardView
            CardView cardView = new CardView(this);
            // Set layout parameters for the CardView
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(16, 16, 16, 16);
            cardView.setLayoutParams(layoutParams);

            // Create a new TextView to display the metro item
            TextView textView = new TextView(this);
            // Set layout parameters for the TextView
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            // Set the text of the TextView to the current metro item
            textView.setText(item1);
            // Add padding to the TextView
            textView.setPadding(16, 16, 16, 16);
            // Center the text within the TextView
            textView.setGravity(Gravity.CENTER);

            // Add the TextView to the CardView
            cardView.addView(textView);
            // Add the CardView to the container LinearLayout
            container.addView(cardView);

            // Set OnClickListener for the CardView
            cardView.setTag(item1); // Set tag as item
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieve clicked item from tag
                    clickedItem = (String) v.getTag();
                    Toast.makeText(ViewStationsActivity.this, clickedItem, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewStationsActivity.this, AddFeedBackActivity.class);
                    intent.putExtra("click",clickedItem);
                    startActivity(intent);
                }
            });

            BottomNavigationView bottomNavigationView = findViewById(R.id.navigationViewStation);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle home action
                    Intent intent = new Intent(ViewStationsActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.action_pre) {
                    // Handle pre action
                    Intent intent = new Intent(ViewStationsActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.action_next) {
                    // Handle next action
                    Intent intent = new Intent(ViewStationsActivity.this, ViewStationsActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.action_logout) {
                    // Handle logout action
                    Intent intent = new Intent(ViewStationsActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            });
        }
    }
    public void addFeedBack(String txt){
        StationExtractor stationExtractor = new StationExtractor(getResources());
        stationExtractor.addFeedBack(txt,clickedItem);
    }
}