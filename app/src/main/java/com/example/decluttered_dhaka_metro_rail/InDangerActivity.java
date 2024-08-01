package com.example.decluttered_dhaka_metro_rail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.Manifest;

public class InDangerActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;
    private String securityGuardPhoneNumber = "01870119563";
    private String policePhoneNumber = "999";
    Button callSecurity,callPolice;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_danger);
        callPolice = findViewById(R.id.button_call_police);
        callSecurity = findViewById(R.id.button_contact_security);
        callSecurity.setOnClickListener(this::makeCallToSecurity);
        callPolice.setOnClickListener(this::makeCallToPolice);
        BottomNavigationView bottomNavigationView = findViewById(R.id.navigationViewIndanger);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.action_home) {
                // Handle home action
                Intent intent = new Intent(InDangerActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_pre) {
                // Handle pre action
                Intent intent = new Intent(InDangerActivity.this, HomeActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_next) {
                // Handle next action
                Intent intent = new Intent(InDangerActivity.this, InDangerActivity.class);
                startActivity(intent);
                return true;
            } else if (itemId == R.id.action_logout) {
                // Handle logout action
                Intent intent = new Intent(InDangerActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }
    private void makeCallToSecurity(View view) {
        makeCall(securityGuardPhoneNumber);
    }

    private void makeCallToPolice(View view) {
        makeCall(policePhoneNumber);
    }

    private void makeCall(String phoneNumber) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    REQUEST_CALL_PERMISSION);
        } else {
            startCall(phoneNumber);
        }
    }

    private void startCall(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, initiate call to the last phone number used
                startCall(securityGuardPhoneNumber); // You might need to keep track of which number was used
            } else {
                Toast.makeText(this, "Permission denied to make calls", Toast.LENGTH_SHORT).show();
            }
        }
    }
}