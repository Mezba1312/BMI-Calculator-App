package com.example.bmicalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HeightConverter extends AppCompatActivity {

    private EditText editTextValue;
    private Spinner spinnerFromUnit;
    private Spinner spinnerToUnit;
    private Button buttonConvert;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_height_converter);

        // Initialize UI elements
        editTextValue = findViewById(R.id.editTextValue);
        spinnerFromUnit = findViewById(R.id.spinnerFromUnit);
        spinnerToUnit = findViewById(R.id.spinnerToUnit);
        buttonConvert = findViewById(R.id.buttonConvert);
        textViewResult = findViewById(R.id.textViewResult);

        // Populate spinners with unit options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.unit_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerFromUnit.setAdapter(adapter);
        spinnerToUnit.setAdapter(adapter);

        // Set button click listener for conversion
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performConversion();
            }
        });
    }

    private void performConversion() {
        // Get user input
        String inputValueStr = editTextValue.getText().toString().trim();
        if (inputValueStr.isEmpty()) {
            textViewResult.setText("Please enter a value.");
            return;
        }

        double inputValue = Double.parseDouble(inputValueStr);
        String fromUnit = spinnerFromUnit.getSelectedItem().toString();
        String toUnit = spinnerToUnit.getSelectedItem().toString();

        // Perform unit conversion
        double result = convertUnits(inputValue, fromUnit, toUnit);

        // Display the result
        if (result != -1) {
            String resultText = String.format("%s %s is %.2f %s.", inputValue, fromUnit, result, toUnit);
            textViewResult.setText(resultText);
        } else {
            textViewResult.setText("Conversion failed. Check the units.");
        }
    }

    private double convertUnits(double value, String fromUnit, String toUnit) {
        // Constants for conversion
        final double METER_TO_INCH = 39.3701;
        final double METER_TO_FEET = 3.28084;
        final double METER_TO_CENTIMETER = 100;
        final double METER_TO_MILLIMETER = 1000;
        final double METER_TO_KILOMETER = 0.001;
        final double METER_TO_MILE = 0.000621371;
        final double METER_TO_YARD = 1.09361;
        final double METER_TO_MEGAMETER = 1e-6;
        final double METER_TO_NAUTICAL_MILE = 0.000539957;
        final double METER_TO_LIGHT_YEAR = 1.057e-16;

        // Perform the conversion
        switch (fromUnit) {
            case "Meters":
                switch (toUnit) {
                    case "Inches":
                        return value * METER_TO_INCH;
                    case "Feet":
                        return value * METER_TO_FEET;
                    case "Centimeters":
                        return value * METER_TO_CENTIMETER;
                    case "Millimeters":
                        return value * METER_TO_MILLIMETER;
                    case "Kilometers":
                        return value * METER_TO_KILOMETER;
                    case "Miles":
                        return value * METER_TO_MILE;
                    case "Yards":
                        return value * METER_TO_YARD;
                    case "Megameters":
                        return value * METER_TO_MEGAMETER;
                    case "Nautical Miles":
                        return value * METER_TO_NAUTICAL_MILE;
                    case "Light Years":
                        return value * METER_TO_LIGHT_YEAR;
                    default:
                        break;
                }
                break;


            case "Miles":
                switch (toUnit) {
                    case "Meters":
                        return value / METER_TO_MILE;
                    case "Inches":
                        return value * 63360; // 1 mile = 63,360 inches
                    case "Feet":
                        return value * 5280; // 1 mile = 5,280 feet
                    case "Centimeters":
                        return value * 160934; // 1 mile = 160,934 centimeters
                    case "Millimeters":
                        return value * 1.609e+6; // 1 mile = 1.609e+6 millimeters
                    case "Kilometers":
                        return value * 1.60934; // 1 mile = 1.60934 kilometers
                    case "Yards":
                        return value * 1760; // 1 mile = 1,760 yards
                    case "Megameters":
                        return value / 1.609; // 1 mile = 1.609 megameters
                    case "Nautical Miles":
                        return value / 1.151; // 1 mile = 1.151 nautical miles
                    case "Light Years":
                        return value / 5.879e+12; // 1 mile = 5.879e+12 light years
                    default:
                        break;
                }
                break;

            case "Feet":
                switch (toUnit) {
                    case "Meters":
                        return value / 3.28084; // 1 foot = 0.3048 meters
                    case "Inches":
                        return value * 12; // 1 foot = 12 inches
                    case "Miles":
                        return value / 5280.0; // 1 foot = 1/5280 mile
                    case "Centimeters":
                        return value * 30.48; // 1 foot = 30.48 centimeters
                    case "Millimeters":
                        return value * 304.8; // 1 foot = 304.8 millimeters
                    case "Kilometers":
                        return value / 3280.84; // 1 foot = 0.0003048 kilometers
                    case "Yards":
                        return value / 3.0; // 1 foot = 1/3 yard
                    // Add more cases as needed
                    default:
                        break;
                }
                break;

            case "Inches":
                switch (toUnit) {
                    case "Meters":
                        return value / METER_TO_INCH;
                    case "Feet":
                        return value / 12; // 1 foot = 12 inches
                    case "Centimeters":
                        return value * 2.54; // 1 inch = 2.54 centimeters
                    case "Millimeters":
                        return value * 25.4; // 1 inch = 25.4 millimeters
                    case "Kilometers":
                        return value * 2.54e-5; // 1 inch = 2.54e-5 kilometers
                    case "Miles":
                        return value / 63360; // 1 mile = 63,360 inches
                    case "Yards":
                        return value / 36; // 1 yard = 36 inches
                    case "Megameters":
                        return value * 2.54e-8; // 1 inch = 2.54e-8 megameters
                    case "Nautical Miles":
                        return value / 72913.4; // 1 nautical mile = 72,913.4 inches
                    case "Light Years":
                        return value * 2.54e-17; // 1 inch = 2.54e-17 light years
                    default:
                        break;
                }
                break;

            case "Centimeters":
                switch (toUnit) {
                    case "Meters":
                        return value / METER_TO_CENTIMETER;
                    case "Inches":
                        return value / 2.54; // 1 inch = 2.54 centimeters
                    case "Feet":
                        return value / 30.48; // 1 foot = 30.48 centimeters
                    case "Millimeters":
                        return value * 10; // 1 centimeter = 10 millimeters
                    case "Kilometers":
                        return value / 100000; // 1 kilometer = 100,000 centimeters
                    case "Miles":
                        return value / 160934; // 1 mile = 160,934 centimeters
                    case "Yards":
                        return value / 91.44; // 1 yard = 91.44 centimeters
                    case "Megameters":
                        return value / 1e8; // 1 megameter = 1e8 centimeters
                    case "Nautical Miles":
                        return value / 185200; // 1 nautical mile = 185,200 centimeters
                    case "Light Years":
                        return value / 9.461e+18; // 1 light year = 9.461e+18 centimeters
                    default:
                        break;
                }
                break;


            // Add cases for other "fromUnit" values if needed

            default:
                break;
        }

        return -1; // Indicate failure if no conversion is performed
    }
}
