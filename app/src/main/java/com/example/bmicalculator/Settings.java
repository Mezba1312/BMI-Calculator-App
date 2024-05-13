package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Get references to the buttons
        Button calculateAgeButton = findViewById(R.id.calculateAgeButton);
        Button convertHeightButton = findViewById(R.id.convertHeightButton);
        Button logoutButton = findViewById(R.id.logoutButton);

        // Set click listeners for the buttons
        calculateAgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to AgeCalculate page
                Intent intent = new Intent(Settings.this, AgeCalculate.class);
                startActivity(intent);
            }
        });

        convertHeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to HeightConverter page
                Intent intent = new Intent(Settings.this, HeightConverter.class);
                startActivity(intent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to Login page
                Intent intent = new Intent(Settings.this, LoginActivity.class);
                startActivity(intent);
                // Finish the current activity to prevent going back to Settings
                finish();
            }
        });
    }
}
