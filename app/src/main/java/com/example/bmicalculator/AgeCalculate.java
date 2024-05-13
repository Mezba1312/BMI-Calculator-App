// src/main/java/com/example/bmicalculator/AgeCalculate.java

package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class AgeCalculate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age_calculate);

        // Find views by their respective IDs
        TextView titleTextView = findViewById(R.id.titleTextView);
        EditText dayEditText = findViewById(R.id.dayEditText);
        EditText monthEditText = findViewById(R.id.monthEditText);
        EditText yearEditText = findViewById(R.id.yearEditText);
        Button calculateButton = findViewById(R.id.calculateButton);
        TextView ageTextView = findViewById(R.id.ageTextView);

        // Set a click listener for the Calculate button
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the entered day, month, and year from the EditTexts
                String dayStr = dayEditText.getText().toString();
                String monthStr = monthEditText.getText().toString();
                String yearStr = yearEditText.getText().toString();

                // Check if the inputs are not empty
                if (!dayStr.isEmpty() && !monthStr.isEmpty() && !yearStr.isEmpty()) {
                    try {
                        // Convert the strings to integers
                        int day = Integer.parseInt(dayStr);
                        int month = Integer.parseInt(monthStr);
                        int year = Integer.parseInt(yearStr);

                        // Validate the input ranges
                        if (isValidDate(day, month, year)) {
                            // Get the current date
                            Calendar calendar = Calendar.getInstance();
                            int currentYear = calendar.get(Calendar.YEAR);
                            int currentMonth = calendar.get(Calendar.MONTH) + 1; // Months are 0-based
                            int currentDay = calendar.get(Calendar.DAY_OF_MONTH);

                            // Calculate age
                            int ageYears = currentYear - year;
                            int ageMonths = currentMonth - month;
                            int ageDays = currentDay - day;

                            // Adjust ageDays and ageMonths if negative
                            if (ageDays < 0) {
                                ageMonths--;
                                ageDays += daysInMonth(currentMonth - 1, currentYear);
                            }
                            if (ageMonths < 0) {
                                ageYears--;
                                ageMonths += 12;
                            }

                            // Adjust ageMonths if the birthday hasn't occurred yet this year
                            if (currentMonth < month || (currentMonth == month && currentDay < day)) {
                                ageMonths--;
                            }

                            // Display the calculated age in the TextView
                            ageTextView.setText("Your age is: " + ageYears + " years, " + ageMonths + " months, and " + ageDays + " days");
                        } else {
                            // Display an error message for invalid date ranges
                            Toast.makeText(AgeCalculate.this, "Please enter a valid date", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException e) {
                        // Handle the case where the input is not a valid number
                        Toast.makeText(AgeCalculate.this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // If any input is empty, show an error message
                    Toast.makeText(AgeCalculate.this, "Please enter valid day, month, and year", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Function to validate the input date
    private boolean isValidDate(int day, int month, int year) {
        // Add your validation logic here
        // For example, you can check if the day, month, and year are within valid ranges
        // This depends on your specific requirements
        return (day >= 1 && day <= 31) && (month >= 1 && month <= 12) && (year > 0);
    }

    // Function to get the number of days in a month
    private int daysInMonth(int month, int year) {
        int[] daysInMonthArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month >= 1 && month <= 12) {
            // Adjust for leap years
            if (month == 2 && (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
                return 29;
            } else {
                return daysInMonthArray[month - 1];
            }
        } else {
            return 0;
        }
    }
}
