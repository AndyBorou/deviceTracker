package com.waverley.tracker.helper;

import com.waverley.tracker.dto.DeviceDTO;
import com.waverley.tracker.dto.HistoryDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.History;
import com.waverley.tracker.model.User;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrey on 11/11/2016.
 */
@Component
public class Transformer {

    public Transformer(){

    }
    public User userDTOtoUser (UserDTO userDTO){
        User user = new User();

        List list = new ArrayList();
        list.add("TransferDozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);
        mapper.map(userDTO, user);

        return user;
    }

    public UserDTO userToUserDTO (User user){

        UserDTO userDTO = new UserDTO();
        List list = new ArrayList();
        list.add("TransferDozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);
        mapper.map(user, userDTO);

        return userDTO;
    }

    public List<UserDTO> userListToUserDTOlist(List<User> userList){

        Transformer transformer = new Transformer();
        List<UserDTO> userDTOList = new ArrayList<>();

        for (int i=0; i<userList.size();i++){
           userDTOList.add(transformer.userToUserDTO((User)userList.get(i)));
        }

        return userDTOList;
    }

    public List<User> userListDTOtoUserList(List<UserDTO> userDTOList){

        Transformer transformer = new Transformer();
        List<User> userList = new ArrayList<>();

        for (int i=0; i<userDTOList.size();i++){
            UserDTO userDTO = (UserDTO) userDTOList.get(i);
            User user = (User) transformer.userDTOtoUser(userDTO);
            userList.add(user);
           // userList.add(transformer.userDTOtoUser((UserDTO)userDTOList.get(i)));
        }

        return userList;
    }

    public DeviceDTO deviceToDeviceDTO(Device device){
        DeviceDTO deviceDTO = new DeviceDTO();
        List list = new ArrayList();
        list.add("TransferDozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);
        mapper.map(device, deviceDTO);

        return deviceDTO;
    }

    public List<DeviceDTO> deviceListToDeviceDTOlist(List<Device> deviceList){
        Transformer transformer = new Transformer();
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        for (int i = 0; i < deviceList.size(); i++) {
            Device device = (Device) deviceList.get(i);
            DeviceDTO deviceDTO = (DeviceDTO) transformer.deviceToDeviceDTO(device);
            deviceDTOList.add(deviceDTO);
        }
        return deviceDTOList;
    }

    public Device deviceDTOtoDevice(DeviceDTO deviceDTO){
        Device device = new Device();
        List list = new ArrayList();
        list.add("TransferDozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(deviceDTO, device);

        return device;
    }
    // передача параметров объектов листа (List<DeviceDTO>) в параметрам объектов листа (List<Device>)
    public List<Device> deviceDTOListToDeviceList(List<DeviceDTO> deviceDTOList){
        Transformer transformer = new Transformer();
        List<Device> deviceList = new ArrayList<>();
        for (int i = 0; i < deviceDTOList.size(); i++) {
            DeviceDTO deviceDTO = (DeviceDTO) deviceDTOList.get(i);

            Device device = (Device) transformer.deviceDTOtoDevice(deviceDTO);
            deviceList.add(device);
        }
        return deviceList;
    }

    public HistoryDTO historyToHistoryDTO(History history){
        HistoryDTO historyDTO = new HistoryDTO();

        List list = new ArrayList();
        list.add("TransferDozerMapping.xml");
        Mapper mapper = new DozerBeanMapper(list);

        mapper.map(history, historyDTO);

        return historyDTO;
    }

    public List<HistoryDTO> historyListToHistoryDTOList(List<History> historyList){
        Transformer transformer = new Transformer();
        List<HistoryDTO> historyDTOList = new ArrayList<>();
        for (int i = 0; i < historyList.size(); i++) {
            History history = (History) historyList.get(i);

            HistoryDTO historyDTO = transformer.historyToHistoryDTO(history);
            historyDTOList.add(historyDTO);
        }
        return historyDTOList;
    }
}
