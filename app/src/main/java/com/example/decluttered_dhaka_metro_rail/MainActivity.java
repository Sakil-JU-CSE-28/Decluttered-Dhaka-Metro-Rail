package com.example.decluttered_dhaka_metro_rail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button reg,login,recoverBtn;
    private EditText email,pass;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.loginButton);
        reg = findViewById(R.id.registerButton);
        email = findViewById(R.id.usernameEditText);
        pass = findViewById(R.id.passwordEditText);
        recoverBtn = findViewById(R.id.recoverPasswordButton);
        databaseHelper = new DatabaseHelper(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String eml = email.getText().toString();
               String pss = pass.getText().toString();
                if (eml.isEmpty() || pss.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {

                    boolean isValid = databaseHelper.checkUser(email.getText().toString(), pass.getText().toString());
                    if (isValid) {
                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        // Proceed to the next activity or the main application
                        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        recoverBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecoverPassword();
            }
        });
    }

    public void RecoverPassword() {
        String eml = email.getText().toString().trim();

        if (eml.isEmpty()) {
            Toast.makeText(MainActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
        } else {
            // Implement password recovery logic here
            // For example, send an email to the user with password recovery instructions
            Toast.makeText(MainActivity.this, "Password recovery email sent", Toast.LENGTH_SHORT).show();
        }
    }
}