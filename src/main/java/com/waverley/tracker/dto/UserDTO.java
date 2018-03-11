package com.waverley.tracker.dto;

import com.waverley.tracker.model.Device;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 11/10/2016.
 */
public class UserDTO {

    private String id;
    private String name;
    private String sername;
    private String location;
    private String role;
    private String login;
    private String password;
    private String email;
    private List<DeviceDTO> deviceDTOs;

    private Map<String, String> userMAP = new HashMap<>();

    public List<DeviceDTO> getDeviceDTOs() {
        return deviceDTOs;
    }

    public void setDeviceDTOs(List<DeviceDTO> deviceDTOs) {
        this.deviceDTOs = deviceDTOs;
    }

    public Map<String, String> getUserMAP() {
        return userMAP;
    }

    public void setUserMAP(Map<String, String> userMAP) {
        this.userMAP = userMAP;
    }

    public Map<String, String> getUserMAp(){
        return this.userMAP;
    }
//    private Map<String, String>

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        userMAP.put("name", name);
    }

    public String getSername() {
        return sername;
    }

    public void setSername(String sername) {
        this.sername = sername;
        userMAP.put("sername", sername);
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        userMAP.put("location", location);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
        userMAP.put("role", role);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        userMAP.put("login", login);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        userMAP.put("password", password);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        userMAP.put("email", email);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + sername + '\'' +
                ", location='" + location + '\'' +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
