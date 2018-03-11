package com.waverley.tracker.dto;



import java.util.List;

/**
 * Created by Andrey on 11/11/2016.
 */
public class   HistoryDTO {

    private int id;
    private String date;
    private String event;
    private int historyUserID;
    private int historyDeviceID;
    private String description;
    private String userInfo;
    private String deviceInfo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public int getHistoryUserID() {
        return historyUserID;
    }

    public void setHistoryUserID(int historyUserID) {
        this.historyUserID = historyUserID;
    }

    public int getHistoryDeviceID() {
        return historyDeviceID;
    }

    public void setHistoryDeviceID(int historyDeviceID) {
        this.historyDeviceID = historyDeviceID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }


    @Override
    public String toString() {
        return  date +" "+ event +" "+historyUserID +" "+ historyDeviceID +" "+description +" "+userInfo+" "+deviceInfo;
    }
}
