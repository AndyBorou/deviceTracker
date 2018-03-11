package com.waverley.tracker.service.impl;


import com.waverley.tracker.dao.api.HistoryDAO;
import com.waverley.tracker.dto.DeviceDTO;
import com.waverley.tracker.dto.HistoryDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.helper.CompareClassList;
import com.waverley.tracker.mapper.BasicMapper;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.History;
import com.waverley.tracker.model.User;
import com.waverley.tracker.service.api.DeviceService;
import com.waverley.tracker.service.api.HistoryService;
import com.waverley.tracker.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Andrey on 12/6/2016.
 */
@Transactional
@Component
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    HistoryDAO historyDAO;

    @Autowired
    CompareClassList<History> compareClassList;

    @Autowired
    DeviceService deviceService;
    @Autowired
    UserService userService;
    @Autowired
    BasicMapper mapper;

    public static String enterTime() {

        Date dateNow = new Date();

        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(dateNow);
        System.out.println(date1);
        return date1;
    }

    private List<HistoryDTO> editData(List<HistoryDTO> eventListDTO){

        Collection<? extends GrantedAuthority> role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        String aa = role.toString();
        List<HistoryDTO> eventListDTO2 = new ArrayList<>();
        if (aa.equals("[ROLE_ADMIN]")) {
            return eventListDTO;
        } else if (aa.equals("[ROLE_ANONYMOUS]")) {

            //filter for shared devices
            for (int i = 0; i < eventListDTO.size(); i++) {

                HistoryDTO historyDTO = eventListDTO.get(i);
                String deviceInfo2 = historyDTO.getDeviceInfo();
                if(!(deviceInfo2.contains("shared='-"))){
                    eventListDTO2.add( historyDTO);
                }

            }
            eventListDTO = eventListDTO2;
            for (int i = 0; i < eventListDTO.size(); i++) {

                HistoryDTO historyDTO = eventListDTO.get(i);
                String userInfo = historyDTO.getUserInfo();

                historyDTO.setDeviceInfo(historyDTO.getDeviceInfo().replaceAll("'", ""));

                String newUserInfo = removeData("password", userInfo);
                newUserInfo = removeData("login", newUserInfo);
                newUserInfo = removeData("role", newUserInfo);
                newUserInfo = removeData("id", newUserInfo);

                if (newUserInfo.contains("password") || newUserInfo.contains("login")) {
                    newUserInfo = removeData("password", newUserInfo);
                    newUserInfo = removeData("login", newUserInfo);
                }

                newUserInfo = newUserInfo.replaceAll("\\{", " ");
                newUserInfo = newUserInfo.replaceAll("}", " ");
                newUserInfo = newUserInfo.replaceAll("'", "");

                historyDTO.setUserInfo(newUserInfo);


                eventListDTO.set(i, historyDTO);

            }

            return eventListDTO;
        }
        return eventListDTO;
    }


    private String removeData(String value, String userInfo) {
        if (userInfo.contains(value)) {
            int startIndex = userInfo.indexOf(value);
            int comma = userInfo.indexOf(",", startIndex);
            userInfo = userInfo.substring(0, startIndex) + userInfo.substring(comma + 2, userInfo.length());
        }
        return userInfo;
    }

    @Override
    public List<HistoryDTO> showAllEvant() {

        List<History> historyList = (List<History>) historyDAO.findAll();
        List<HistoryDTO> eventListDTO = mapper.listMapToList(historyList, HistoryDTO.class);
        eventListDTO = editData(eventListDTO);
        return eventListDTO;
    }

    @Override
    public void createUser(User user) {

        History history = new History();
        history.setDate(enterTime());
        history.setEvent("CreateUser");
        history.setUserInfo(user.toString());
        history.setHistoryUserID(user.getId());
        history.setDeviceInfo("---");
        historyDAO.save(history);
    }

    @Override
    public void createDevice(Device device) {


        History history = new History();
        history.setDate(enterTime());
        history.setEvent("CreateDevice");
        history.setDeviceInfo(device.toString());
        history.setHistoryDeviceID(device.getDeviceID());
        history.setUserInfo("---");
        historyDAO.save(history);
    }

    @Override
    public void updateUser(List<UserDTO> oldUserDTOList, List<UserDTO> newUserDTOList) {

        List<User> oldUserList = mapper.listMapToList(oldUserDTOList, User.class);
        List<User> newUserList = mapper.listMapToList(newUserDTOList, User.class);


        List<History> historyList = new ArrayList<>();
        for (int i = 0; i < newUserList.size(); i++) {
            History history = new History();
            User newUser = newUserList.get(i);
            User oldUser = oldUserList.get(i);
            history.setDate(enterTime());
            history.setEvent("UpdateUser");
            history.setUserInfo("The old: " + oldUser.toString() + "The new: " + newUser.toString());
            history.setHistoryUserID(newUser.getId());
            history.setDeviceInfo("---");
            historyList.add(history);
            //    historyServiceDAOwithCRUD.save(history);
        }
        historyDAO.save(historyList);
    }

    @Override
    public void updateUser(User oldUser, User newUser) {

        History history = new History();
        history.setDate(enterTime());
        history.setEvent("UpdateUser");
        history.setUserInfo("The old: " + oldUser.toString() + "The new: " + newUser.toString());
        history.setHistoryUserID(newUser.getId());
        history.setDeviceInfo("---");
        historyDAO.save(history);
    }


    @Override
    public void updateDevice(Device oldDevice, Device newDevice) {
        History history = new History();

        history.setDate(enterTime());
        history.setEvent("UpdateDevice");
        history.setDeviceInfo("The old: " + oldDevice.toString() + "The new: " + newDevice.toString());
        history.setHistoryUserID(newDevice.getDeviceID());
        history.setUserInfo("---");

        historyDAO.save(history);
    }

    @Override
    public void deleteUser(List<UserDTO> userDTOList) {
        List<User> userList = mapper.listMapToList(userDTOList, User.class);

        List<History> historyList = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            History history = new History();

            User user = userList.get(i);
            history.setDate(enterTime());
            history.setEvent("DeleteUser");
            history.setUserInfo(user.toString());
            history.setHistoryUserID(user.getId());
            history.setDeviceInfo("---");
            historyList.add(history);
        }
        historyDAO.save(historyList);
    }

    @Override
    public void deleteUser(User user) {

        History history = new History();

        history.setDate(enterTime());
        history.setEvent("DeleteUser");
        history.setUserInfo(user.toString());
        history.setHistoryUserID(user.getId());
        history.setDeviceInfo("---");

        historyDAO.save(history);
    }

    @Override
    public void deleteDevice(Device device) {

        History history = new History();
        history.setDate(enterTime());
        history.setEvent("DeleteDevice");
        history.setDeviceInfo(device.toString());
        history.setHistoryDeviceID(new Integer(device.getDeviceID()));
        history.setUserInfo("---");

        historyDAO.save(history);
    }

    @Override
    public void assign(String[] IDselectedDevice, String selectedUserID) {

        List<DeviceDTO> selectedDeviceDTOList = deviceService.findSelectedDevices(IDselectedDevice);
        UserDTO userDTO = userService.findDTOUser(selectedUserID);

        List<Device> deviceList = mapper.listMapToList(selectedDeviceDTOList, Device.class);

        User user = mapper.singleMapper(userDTO, User.class);

        List<History> historyList = new ArrayList<>();

        for (int i = 0; i < deviceList.size(); i++) {
            History history = new History();
            Device device = deviceList.get(i);
            history.setDate(enterTime());
            history.setEvent("Assign");
            history.setDeviceInfo(device.toString());
            history.setUserInfo(user.toString());
            history.setHistoryDeviceID(device.getDeviceID());
            history.setHistoryUserID(user.getId());
            historyList.add(history);
        }
        historyDAO.save(historyList);

    }

    @Override
    public void unassign(List<DeviceDTO> selectedDeviceDTOList) {
        List<Device> deviceList = mapper.listMapToList(selectedDeviceDTOList, Device.class);


        Set<Integer> userDTOset = new HashSet<>();
        for (int i = 0; i < deviceList.size(); i++) {
            Integer id = (deviceList.get(i)).getUser().getId();
            userDTOset.add(id);
        }

        List<History> historyList = new ArrayList<>();
        List<Integer> userIdList = new ArrayList<>(userDTOset);

        for (int i = 0; i < userIdList.size(); i++) {
            for (int y = 0; y < deviceList.size(); y++) {
                if (userIdList.get(i).equals(deviceList.get(y).getUser().getId())) {
                    History history = new History();
                    Device device = deviceList.get(y);
                    String s = "" + userIdList.get(i) + "";
                    User user = mapper.singleMapper(userService.findDTOUser(s), User.class);

                    System.out.println(enterTime().toString());
                    history.setDate(enterTime());
                    history.setEvent("UnAssign");
                    history.setDeviceInfo(device.toString());
                    history.setUserInfo(user.toString());
                    history.setHistoryDeviceID(device.getDeviceID());
                    history.setHistoryUserID(user.getId());
                    historyList.add(history);
                }
            }
        }
        historyDAO.save(historyList);

    }

    @Override
    public List<HistoryDTO> searchEvent(String typeUserSearch, String typeDeviceSearch, String userInput, String deviceInput, String firstData, String lastData, String event) {

        userInput = userInput.trim();
        deviceInput = deviceInput.trim();

        Map<String, String> param = new HashMap<>();

        //Gathering a parameter for search
        if (!typeUserSearch.equals("") && !userInput.equals("")) {
            switch (typeUserSearch) {
                case "name":
                    param.put("firstName", userInput);
                    break;
                case "sername":
                    param.put("lastName", userInput);
                    break;
                case "id":
                    param.put("userID", userInput);
                    break;
            }
        }

        if (!typeDeviceSearch.equals("") && !deviceInput.equals("")) {
            switch (typeDeviceSearch) {
                case "model":
                    param.put("modelName", deviceInput);
                    break;
                case "serialNumber":
                    param.put("serialNumber", deviceInput);
                    break;
                case "devId":
                    param.put("deviceID", deviceInput);
                    break;
            }
        }

      // if (firstData != "" && !firstData.equals("yy-mm-dd")) {
        if (firstData != "" && !firstData.equals("mm/dd/yyyy") && !firstData.equals("yy-mm-dd")) {
            param.put("firstDate", firstData);
            System.out.println("F " + param.get("firstDate"));
        }

        if (lastData != "" && !lastData.equals("mm/dd/yyyy") && !lastData.equals("yy-mm-dd")) {
            param.put("lastDate", lastData);
            System.out.println("L " + param.get("lastDate"));
        }

        if (event != "") {
            param.put("event", event);
            System.out.println(event);
        }

//Finding devices by different parametters and gathering found devices in list
        List<HistoryDTO> historyDTOList;
        List<History> historyList = new ArrayList<>();
        List<History> historyList2;

        if (param.containsKey("firstDate") && param.containsKey("lastDate")) {
            historyList = (List<History>) historyDAO.findByDates(param.get("firstDate"), param.get("lastDate"));
        } else if (param.containsKey("firstDate")) {
            historyList = (List<History>) historyDAO.findByDate(param.get("firstDate"));
        } else if (param.containsKey("lastDate")) {
            historyList = (List<History>) historyDAO.findByDate(param.get("lastDate"));
        }

        if (param.containsKey("event")) {
            if ((historyList.size()) == 0) {
                historyList = historyDAO.findByEvent(param.get("event"));
            } else {

                historyList2 = historyDAO.findByEvent(param.get("event"));

                historyList = compareClassList.compare(historyList, historyList2);
            }
        }
//        historyList2 = null;
//        System.out.println("historyList + event " + historyList.size());

        if (param.containsKey("firstName") && param.containsKey("lastName")) {//for future
            historyList2 = historyDAO.findByUserInfoContaining("%name='" + param.get("firstName") + "%sername='" + param.get("lastName") + "%");
            historyList = compareClassList.compare(historyList, historyList2);
        } else if (param.containsKey("firstName")) {
            historyList2 = historyDAO.findByUserInfoContaining("%, name='" + param.get("firstName") + "%");
            historyList = compareClassList.compare(historyList, historyList2);
        } else if (param.containsKey("lastName")) {
            historyList2 = historyDAO.findByUserInfoContaining("%, surname='" + param.get("lastName") + "%");
            historyList = compareClassList.compare(historyList, historyList2);
        }

        if (param.containsKey("modelName") && param.containsKey("serialNumber")) { //for future
            historyList2 = historyDAO.findByDeviceInfoContaining("%" + param.get("modelName") + "', serialNumber='" + param.get("serialNumber") + "%");
            historyList = compareClassList.compare(historyList, historyList2);
        } else if (param.containsKey("modelName")) {
            historyList2 = historyDAO.findByDeviceInfoContaining("%modelName='" + param.get("modelName") + "%");
            historyList = compareClassList.compare(historyList, historyList2);
        } else if (param.containsKey("serialNumber")) {
            historyList2 = historyDAO.findByDeviceInfoContaining("%serialNumber='" + param.get("serialNumber") + "%");
            historyList = compareClassList.compare(historyList, historyList2);
        }

        if (param.containsKey("userID")) {
            historyList2 = historyDAO.findByHistoryUserID(new Integer(param.get("userID")));
            historyList = compareClassList.compare(historyList, historyList2);
        }



        if (param.containsKey("deviceID")) {
            historyList2 = historyDAO.findByHistoryDeviceID(new Integer(param.get("deviceID")));
            historyList = compareClassList.compare(historyList, historyList2);
        }

        if(historyList.size()==0){
            HistoryDTO historyDTO =new HistoryDTO();
            historyDTOList = new ArrayList<>();
            historyDTOList.add(historyDTO);
            return historyDTOList;
        }

        historyDTOList = mapper.listMapToList(historyList, HistoryDTO.class);
        historyDTOList = editData(historyDTOList);
        return historyDTOList;
    }

    @Override
    public List<HistoryDTO> searchEventUser(List<UserDTO> selectedUserDTOList) {
        List<User> userList = mapper.listMapToList(selectedUserDTOList, User.class);


        List<History> historyList = new ArrayList<>();

        for (int i = 0; i < userList.size(); i++) {
            Integer userId = (userList.get(i)).getId();
            List<History> historyList2 = historyDAO.findByHistoryUserID(userId);
            historyList.addAll(historyList2);
        }
        List<HistoryDTO> historyDTOList = mapper.listMapToList(historyList, HistoryDTO.class);

        return historyDTOList;
    }

    @Override
    public List<HistoryDTO> searchEventDevice(List<DeviceDTO> selectedDeviceDTOList) {

        List<Device> deviceList = mapper.listMapToList(selectedDeviceDTOList, Device.class);

        List<History> historyList = new ArrayList<>();

        for (int i = 0; i < deviceList.size(); i++) {
            Integer deviceId = (deviceList.get(i)).getDeviceID();
            List<History> historyList2 = historyDAO.findByHistoryDeviceID(deviceId);
            historyList.addAll(historyList2);
        }
        List<HistoryDTO> historyDTOList = mapper.listMapToList(historyList, HistoryDTO.class);

        return historyDTOList;
    }

}
