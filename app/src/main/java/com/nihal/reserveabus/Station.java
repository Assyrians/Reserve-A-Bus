package com.nihal.reserveabus;


import java.util.List;

public class Station {
    private String stationName, journeysNumber;
    private String key;
    private List<String> journeys;

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

    public Station(String stationName, String journeysNumber, String key, List<String> journeys) {
        this.stationName = stationName;
        this.journeysNumber = journeysNumber;
        this.key = key;
        this.journeys = journeys;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<String> getJourneys() {
        return journeys;
    }

    public void setJourneys(List<String> journeys) {
        this.journeys = journeys;
    }
}