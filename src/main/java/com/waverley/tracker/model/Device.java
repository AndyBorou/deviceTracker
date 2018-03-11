package com.waverley.tracker.model;

import javax.persistence.*;

/**
 * Created by Andrey on 11/11/2016.
 */
@Entity
@Table (name = "device")
public class Device {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deviceID;

    @Column(name = "model_name")
    private String modelName;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "type_os")
    private String typeOS;

    @Column(name = "os_version")
    private String oSVersion;

    @Column(name = "skreen_resolution")
    private String skreenResolution;

    @Column(name = "screen_diagonal")
    private String screenDiagonal;

    @Column(name = "description")
    private String description;

    @Column(name = "ram")
    private String rAM;

    @Column(name = "rom")
    private String rOM;

    @Column(name = "location")
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id",referencedColumnName = "id"  )
     private User user;

    @Column(name = "shared")
    private String shared;

    public Device(){};

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDeviceID() {
        return this.deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = new Integer(deviceID);
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTypeOS() {
        return this.typeOS;
    }

    public void setTypeOS(String typeOS) {
        this.typeOS = typeOS;
    }

    public String getoSVersion() {
        return this.oSVersion;
    }

    public void setoSVersion(String oSVersion) {
        this.oSVersion = oSVersion;
    }

    public String getSkreenResolution() {
        return this.skreenResolution;
    }

    public void setSkreenResolution(String skreenResolution) {
        this.skreenResolution = skreenResolution;
    }

    public String getScreenDiagonal() {
        return this.screenDiagonal;
    }

    public void setScreenDiagonal(String screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getrAM() {
        return this.rAM;
    }

    public void setrAM(String rAM) {
        this.rAM = rAM;
    }

    public String getrOM() {
        return this.rOM;
    }

    public void setrOM(String rOM) {
        this.rOM = rOM;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;

    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    @Override
    public String toString() {
        return "Device " +
                "deviceID='" + deviceID + '\'' +
                ", modelName='" + modelName + '\'' +
                ", serialNumber='" + serialNumber + '\'' +
                ", typeOS='" + typeOS + '\'' +
                ", oSVersion='" + oSVersion + '\'' +
                ", skreenResolution='" + skreenResolution + '\'' +
                ", screenDiagonal='" + screenDiagonal + '\'' +
                ", description='" + description + '\'' +
                ", rAM='" + rAM + '\'' +
                ", rOM='" + rOM + '\'' +
                ", location='" + location +'\'' +
                ", shared='"+shared;
    }


}
