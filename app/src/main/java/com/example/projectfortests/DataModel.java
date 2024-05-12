package com.example.projectfortests;

public class DataModel {
    String firstData;
    String secondData;
    String thirdData;

    public DataModel(String firstData, String secondData, String thirdData) {
        this.firstData = firstData;
        this.secondData = secondData;
        this.thirdData = thirdData;
    }

    public String getFirstData() {
        return firstData;
    }

    public void setFirstData(String firstData) {
        this.firstData = firstData;
    }

    public String getSecondData() {
        return secondData;
    }

    public void setSecondData(String secondData) {
        this.secondData = secondData;
    }

    public String getThirdData() {
        return thirdData;
    }

    public void setThirdData(String thirdData) {
        this.thirdData = thirdData;
    }
}