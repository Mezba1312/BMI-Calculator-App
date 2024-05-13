
        package com.example.bmicalculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
// HistoryAdapter.java

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<BMIRecord> historyItemList;

    public HistoryAdapter(List<BMIRecord> historyItemList) {
        this.historyItemList = historyItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BMIRecord bmiRecord = historyItemList.get(position);

        holder.textViewBmi.setText("BMI: " + bmiRecord.getBmi());
        holder.textViewAge.setText("Age: " + bmiRecord.getAge());
        holder.textViewWeight.setText("Weight: " + bmiRecord.getWeight());
        holder.textViewGender.setText("Gender: " + bmiRecord.getGender());
    }

    @Override
    public int getItemCount() {
        return historyItemList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewBmi;
        public TextView textViewAge;
        public TextView textViewWeight;
        public TextView textViewGender;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBmi = itemView.findViewById(R.id.textViewBmi);
            textViewAge = itemView.findViewById(R.id.textViewAge);
            textViewWeight = itemView.findViewById(R.id.textViewWeight);
            textViewGender = itemView.findViewById(R.id.textViewGender);
        }
    }
}