package com.nihal.reserveabus;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Journey {
    private String stationName, busNumber;
    private String stationKey;
    private String key;
    private int capacity;
    private List<String> students;
    private Date date;
    private int size;
    private Date arrivalDate;

    public Journey() {
    }


    public Journey(String stationName, String busNumber) {
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.students = new ArrayList<>();
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

    public String getStationKey() {
        return stationKey;
    }

    public void setStationKey(String stationKey) {
        this.stationKey = stationKey;
    }

    public Journey(String stationName, String busNumber, String stationKey) {
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.stationKey = stationKey;
        this.students = new ArrayList<>();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getStudents() {
        return students;
    }

    public void setStudents(List<String> students) {
        this.students = students;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Journey(String stationName, String busNumber, String stationKey, int capacity, Date date) {
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.stationKey = stationKey;
        this.capacity = capacity;
        this.date = date;
        this.students = new ArrayList<>();
    }

    public Journey(String stationName, String busNumber, String stationKey, int capacity, Date date, Date arrivalDate) {
        this.stationName = stationName;
        this.busNumber = busNumber;
        this.stationKey = stationKey;
        this.capacity = capacity;
        this.date = date;
        this.students = new ArrayList<>();
        this.arrivalDate = arrivalDate;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void AddStudent (String id){
        this.students.add(id);
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}