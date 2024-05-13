package com.example.bmicalculator;

public class BMIRecord {
    private String bmi;
    private String age;
    private String gender;
    private String weight;

    // Required for Firebase serialization
    public BMIRecord() {
        // Default constructor required for calls to DataSnapshot.getValue(BMIRecord.class)
    }

    public BMIRecord(String bmi, String age, String weight, String gender) {
        this.bmi = bmi;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }

    public String getBmi() {
        return bmi;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getWeight() {
        return weight;
    }
}