package com.example.decluttered_dhaka_metro_rail;

// Data class to represent train data
public class Train {
    private String trainId;
    private double latitude;
    private double longitude;

    // Getters and setters


    public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
