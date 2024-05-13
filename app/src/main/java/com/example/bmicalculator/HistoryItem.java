package com.example.bmicalculator;

public class HistoryItem {
    private String bmi;
    private String age;
    private String weight;
    private String gender;

    // Empty constructor needed for Firebase
    public HistoryItem() {
    }

    public HistoryItem(String bmi, String age, String weight, String gender) {
        this.bmi = bmi;
        this.age = age;
        this.weight = weight;
        this.gender = gender;
    }

    public String getBmi() {
        return bmi;
    }

    public void setBmi(String bmi) {
        this.bmi = bmi;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    // Add getTitle() and getDescription() methods
    public String getTitle() {
        return "Title";  // Provide an appropriate implementation
    }

    public String getDescription() {
        return "Description";  // Provide an appropriate implementation
    }
}
