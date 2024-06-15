package com.example.pharmaexpress;

public class Pharmacie {

    String name;
    double longitude, latitude;

    public Pharmacie(String name, double longitude, double latitude){

        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getName(){
        return name;
    }

    public double getLongitude(){
        return longitude;
    }
    public double getLatitude(){
        return latitude;
    }

    @Override
    public String toString() {
        return name;
    }
}
