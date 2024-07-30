package com.example.decluttered_dhaka_metro_rail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {
    EditText email,password,confirmPass;
    Button submit;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        submit = findViewById(R.id.submitButton);
        databaseHelper = new DatabaseHelper(this);
        submit.setOnClickListener(view -> {
            email = findViewById(R.id.emailEditText);
            password = findViewById(R.id.passwordEditText);
            confirmPass = findViewById(R.id.confirmPasswordEditText);

            if(password.getText().toString().equals(confirmPass.getText().toString())){
                boolean isValid = databaseHelper.checkEmail(email.getText().toString());
                if(isValid){
                    Toast.makeText(RegisterActivity.this, "This email is registered. Try with another", Toast.LENGTH_SHORT).show();
                }
                else{
                    boolean isInserted = databaseHelper.addUser(email.getText().toString(), password.getText().toString());
                    if (isInserted) {
                        Toast.makeText(RegisterActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "Fill every field and Keep password same", Toast.LENGTH_SHORT).show();
            }
        });
    }
}