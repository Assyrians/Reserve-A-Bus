package com.nihal.reserveabus;


public class Station {
    private String stationName, journeysNumber;

    public Station() {
    }

    public Station(String stationName, String journeysNumber) {
        this.stationName = stationName;
        this.journeysNumber = journeysNumber;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getJourneysNumber() {
        return journeysNumber;
    }

    public void setJourneysNumber(String journeysNumber) {
        this.journeysNumber = journeysNumber;
    }
}