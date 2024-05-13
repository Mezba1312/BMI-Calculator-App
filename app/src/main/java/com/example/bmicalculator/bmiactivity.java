package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bmiactivity extends AppCompatActivity {

    TextView mbmidisplay, magedisplay, mweightdisplay, mheightdisplay, mbmicategory, mgender;
    Button mgotomain, mSaveBmi;
    Intent intent;

    ImageView mimageview;
    String mbmi;
    String cateogory;
    float intbmi;

    String height, weight, age, gender, username;

    float intheight, intweight;

    RelativeLayout mbackground;
    DatabaseReference databaseReference;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmiactivity);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("BMIRecords");

        // Retrieve the username from LoginActivity
        intent = getIntent();
        username = intent.getStringExtra("username");

        mbmidisplay = findViewById(R.id.bmidisplay);
        mbmicategory = findViewById(R.id.bmicategorydispaly);
        mgotomain = findViewById(R.id.gotomain);
        mimageview = findViewById(R.id.imageview);
        mbackground = findViewById(R.id.contentlayout);
        mSaveBmi = findViewById(R.id.saveBmi);

        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");
        age = intent.getStringExtra("age");
        gender = intent.getStringExtra("gender");

        intheight = Float.parseFloat(height);
        intweight = Float.parseFloat(weight);

        intheight = intheight / 100;
        intbmi = intweight / (intheight * intheight);

        mbmi = Float.toString(intbmi);

        if (intbmi < 16) {
            mbmicategory.setText("Severe Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
        } else if (intbmi < 16.9 && intbmi > 16) {
            mbmicategory.setText("Moderate Thinness");
            mbackground.setBackgroundColor(R.color.purple);
            mimageview.setImageResource(R.drawable.warning);
        } else if (intbmi < 18.4 && intbmi > 17) {
            mbmicategory.setText("Mild Thinness");
            mbackground.setBackgroundColor(R.color.blue);
            mimageview.setImageResource(R.drawable.warning);
        } else if (intbmi < 24.9 && intbmi > 18.5) {
            mbmicategory.setText("Perfect");
            mimageview.setImageResource(R.drawable.ok);
            mbackground.setBackgroundColor(Color.GREEN);
        } else if (intbmi < 29.9 && intbmi > 25) {
            mbmicategory.setText("Overweight");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        } else if (intbmi < 34.9 && intbmi > 30) {
            mbmicategory.setText("Obese Class I");
            mbackground.setBackgroundColor(R.color.red);
            mimageview.setImageResource(R.drawable.warning);
        } else {
            mbmicategory.setText("Obese Class II");
            mbackground.setBackgroundColor(R.color.red);
            mimageview.setImageResource(R.drawable.crosss);
        }

        mgender = findViewById(R.id.genderdisplay);
        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);

        mSaveBmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Use the username retrieved from LoginActivity
                saveToFirebase(username);
            }
        });

        mgotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
            }
        });
    }

    private void saveToFirebase(String username) {
        if (username != null) {
            try {
                DatabaseReference userReference = FirebaseDatabase.getInstance().getReference().child("users").child(username);
                DatabaseReference bmiInfoReference = userReference.child("bmi_info").push();
                BMIRecord bmiRecord = new BMIRecord(mbmi, age, weight, gender);
                bmiInfoReference.setValue(bmiRecord);

                Toast.makeText(bmiactivity.this, "BMI result saved successfully", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.v("log", String.valueOf(e));
            }
        } else {
            Toast.makeText(bmiactivity.this, "Username not defined", Toast.LENGTH_SHORT).show();
        }
    }
}