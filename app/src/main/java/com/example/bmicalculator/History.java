package com.example.bmicalculator;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class History extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryAdapter historyAdapter;
    private List<BMIRecord> historyItemList;
    private DatabaseReference userReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // Log all extras in the Intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            for (String key : extras.keySet()) {
                Log.d("IntentExtra", key + ": " + extras.get(key));
            }
        }

        recyclerView = findViewById(R.id.recyclerViewHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        historyItemList = new ArrayList<>();
        historyAdapter = new HistoryAdapter(historyItemList);
        recyclerView.setAdapter(historyAdapter);

        String username = getIntent().getStringExtra("username");
        if (username != null) {
            Log.d("History", "Username: " + username);

            // Updated the DatabaseReference to point directly to the "history" node
            userReference = FirebaseDatabase.getInstance().getReference().child("users").child(username).child("bmi_info");

            loadHistoryData();
        } else {
            Toast.makeText(this, "Username not defined", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadHistoryData() {
        userReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                historyItemList.clear(); // Clear historyItemList before adding data

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BMIRecord bmiRecord = snapshot.getValue(BMIRecord.class);
                    if (bmiRecord != null) {
                        historyItemList.add(bmiRecord);
                        Log.d("BMIRecord", "BMI: " + bmiRecord.getBmi());
                        Log.d("BMIRecord", "Age: " + bmiRecord.getAge());
                        Log.d("BMIRecord", "Weight: " + bmiRecord.getWeight());
                        Log.d("BMIRecord", "Gender: " + bmiRecord.getGender());
                    }
                }

                // Notify the adapter that the data has changed
                historyAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(History.this, "Error loading history data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
