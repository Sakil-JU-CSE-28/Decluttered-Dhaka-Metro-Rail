package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Set;

/**
 * Activity class for viewing metro features in the Dhaka Metro Rail application.
 * This activity displays a list of features available in the metro rail network.
 */
public class ViewMetroFeaturesActivity extends AppCompatActivity {

    private String clickedFeature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_metro_features);

        // Get the features from the MetroFeatureExtractor class
        MetroFeatureExtractor featureExtractor = new MetroFeatureExtractor();
        Set<String> features = featureExtractor.getMetros(getApplicationContext(),R.raw.metro);

        // Get the container LinearLayout from the layout
        LinearLayout container = findViewById(R.id.containerFeatures);

        // Iterate through each metro feature
        for (String feature : features) {
            // Create a new CardView
            CardView cardView = new CardView(this);
            // Set layout parameters for the CardView
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            layoutParams.setMargins(16, 16, 16, 16);
            cardView.setLayoutParams(layoutParams);

            // Create a new TextView to display the metro feature
            TextView textView = new TextView(this);
            // Set layout parameters for the TextView
            textView.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            // Set the text of the TextView to the current metro feature
            textView.setText(feature);
            // Add padding to the TextView
            textView.setPadding(16, 16, 16, 16);
            // Center the text within the TextView
            textView.setGravity(Gravity.CENTER);

            // Add the TextView to the CardView
            cardView.addView(textView);
            // Add the CardView to the container LinearLayout
            container.addView(cardView);

            // Set OnClickListener for the CardView
            cardView.setTag(feature); // Set tag as feature
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Retrieve clicked feature from tag
                    clickedFeature = (String) v.getTag();
                    Toast.makeText(ViewMetroFeaturesActivity.this, clickedFeature, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ViewMetroFeaturesActivity.this, AddFeedBackActivity.class);
                    intent.putExtra("feature", clickedFeature);
                    startActivity(intent);
                }
            });

            BottomNavigationView bottomNavigationView = findViewById(R.id.navigationViewMetro);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.action_home) {
                    // Handle home action
                    Intent intent = new Intent(ViewMetroFeaturesActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.action_pre) {
                    // Handle pre action
                    Intent intent = new Intent(ViewMetroFeaturesActivity.this, HomeActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.action_next) {
                    // Handle next action
                    Intent intent = new Intent(ViewMetroFeaturesActivity.this, ViewMetroFeaturesActivity.class);
                    startActivity(intent);
                    return true;
                } else if (itemId == R.id.action_logout) {
                    // Handle logout action
                    Intent intent = new Intent(ViewMetroFeaturesActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else {
                    return false;
                }
            });
        }
    }

    public void addFeatureFeedback(String feedback) {
        MetroFeatureExtractor featureExtractor = new MetroFeatureExtractor();
        featureExtractor.addComments(feedback);
    }
}
