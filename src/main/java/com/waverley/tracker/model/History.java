package com.waverley.tracker.model;

import javax.persistence.*;

/**
 * Created by Andrey on 11/11/2016.
 */
@Entity
@Table(name = "history")
public class History {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name="date")
    private String date;
    @Column (name="event")
    private String event;
    @Column (name="history_user_id", nullable = true)
    private Integer historyUserID;
    @Column (name="history_device_id", nullable = true)
    private Integer historyDeviceID;
    @Column (name="description", length = 1000)
    private String description;
    @Column (name="user_info", length = 500)
    private String userInfo;
    @Column (name="device_info", length = 500)
    private String deviceInfo;

    public History(){}

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

    public Integer getHistoryUserID() {
        return historyUserID;
    }

    public void setHistoryUserID(int historyUserID) {
        this.historyUserID = historyUserID;
    }

    public Integer getHistoryDeviceID() {
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
        return id + date +" "+ event +" "+historyUserID +" "+ historyDeviceID +" "+description +" "+userInfo+" "+deviceInfo;
    }


}
