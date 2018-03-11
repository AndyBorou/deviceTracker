package com.waverley.tracker.service.api;

import com.waverley.tracker.dto.DeviceDTO;
import com.waverley.tracker.dto.HistoryDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.History;
import com.waverley.tracker.model.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * CreatedCreated by Andrey on 11/10/2016.
 */
public interface HistoryService {

    List<HistoryDTO> showAllEvant();

    void createUser(User user);

    void createDevice(Device device);

    void updateUser(List<UserDTO> oldUserDTOList, List<UserDTO> newUserDTOList);

    void updateUser(User oldUser, User newUser);

    void updateDevice(Device oldDevice, Device newDevice);

    void deleteUser(List<UserDTO> userDTOList);

    void deleteUser(User user);

    void deleteDevice(Device device);

    void assign(String[] IDselectedDevice, String selectedUserID);

    void unassign(List<DeviceDTO> selectedDeviceDTOList);

    List<HistoryDTO> searchEvent(String typeUserSearch, String typeDeviceSearch, String userInput, String deviceInput, String firstData, String lastData, String event);

    List<HistoryDTO> searchEventUser(List<UserDTO> selectedUserDTOList);

    List<HistoryDTO> searchEventDevice(List<DeviceDTO> selectedDeviceDTOList);
}
