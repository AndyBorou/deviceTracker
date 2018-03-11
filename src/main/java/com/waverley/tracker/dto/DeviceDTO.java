package com.waverley.tracker.dto;

import com.waverley.tracker.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Andrey on 11/11/2016.
 */
public class DeviceDTO {

    private String deviceID;
    private String modelName;
    private String serialNumber;
    private String typeOS;
    private String oSVersion;
    private String skreenResolution;
    private String screenDiagonal;
    private String description;
    private String rAM;
    private String rOM;
    private String location;
    private Map<String, String> deviceParamMap = new HashMap<>();
    private UserDTO userDTO;
    private String shared;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public DeviceDTO() {
    }

    public String getDeviceID() {
        return this.deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
        deviceParamMap.put("modelName", modelName);
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        deviceParamMap.put("serialNumber", serialNumber);
    }

    public String getTypeOS() {
        return this.typeOS;
    }

    public void setTypeOS(String typeOS) {
        this.typeOS = typeOS;
        deviceParamMap.put("typeOS", typeOS);
    }

    public String getoSVersion() {
        return this.oSVersion;
    }

    public void setoSVersion(String oSVersion) {
        this.oSVersion = oSVersion;
        deviceParamMap.put("oSVersion", oSVersion);
    }

    public String getSkreenResolution() {
        return this.skreenResolution;
    }

    public void setSkreenResolution(String skreenResolution) {
        this.skreenResolution = skreenResolution;
        deviceParamMap.put("skreenResolution", skreenResolution);
    }

    public String getScreenDiagonal() {
        return this.screenDiagonal;
    }

    public void setScreenDiagonal(String screenDiagonal) {
        this.screenDiagonal = screenDiagonal;
        deviceParamMap.put("screenDiagonal", screenDiagonal);
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
        deviceParamMap.put("description", description);
    }

    public String getrAM() {
        return this.rAM;
    }

    public void setrAM(String rAM) {
        this.rAM = rAM;
        deviceParamMap.put("rAM", rAM);
    }

    public String getrOM() {
        return this.rOM;
    }

    public void setrOM(String rOM) {
        this.rOM = rOM;
        deviceParamMap.put("rOM", rOM);
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
        deviceParamMap.put("location", location);
    }

    public Map<String, String> getDeviceParamMap() {
        return deviceParamMap;
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
