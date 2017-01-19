package com.nihal.reserveabus;


public class Journey {
    private String stationName, busNumber;

    public Journey() {
    }

    public Journey(String stationName, String busNumber) {
        this.stationName = stationName;
        this.busNumber = busNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getBusNumber() {
        return busNumber;}

    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }
}