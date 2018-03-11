package com.waverley.tracker.service.impl;


import com.waverley.tracker.dao.api.DeviceDAO;
import com.waverley.tracker.dto.DeviceDTO;
import com.waverley.tracker.dto.HistoryDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.mapper.BasicMapper;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.User;
import com.waverley.tracker.service.api.DeviceService;
import com.waverley.tracker.service.api.HistoryService;
import com.waverley.tracker.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Andrey on 11/20/2016.
 */
@Transactional
@Component
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    DeviceDAO deviceDAO;

    @Autowired
    BasicMapper mapper;

    @Autowired
    UserService userService;

    @Autowired
    HistoryService historyService;

    public DeviceServiceImpl() {
    }


    public List<DeviceDTO> sortDevices(List<DeviceDTO> deviceDTOList) {

        List<DeviceDTO> deviceDTOListSort = new LinkedList<>();

        int a = 0;
        for (DeviceDTO deviceDTO : deviceDTOList) {
            UserDTO userDTO = deviceDTO.getUserDTO();
            if (userDTO != null) {
                deviceDTOListSort.add(a, deviceDTO);
                a++;
            } else {
                deviceDTOListSort.add(deviceDTO);
            }
        }
        return deviceDTOListSort;

    }

    public List<Device> sortBySharing(List<Device> deviceList) {

        Collection<? extends GrantedAuthority> role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String thisRole = role.toString();
        if(!thisRole.equals("[ROLE_ADMIN]")) {
            List<Device> deviceListSort = new ArrayList<>();
            for (int i = 0; i < deviceList.size(); i++) {
                String shared = deviceList.get(i).getShared();
                if (shared != null && shared.equals("user")) {
                    deviceListSort.add(deviceList.get(i));
                }
            }
            deviceList = deviceListSort;
        }
        return deviceList;
    }

    @Override
    public boolean checkForEmptyParameters(DeviceDTO deviceDTO) {

        Iterator entries = deviceDTO.getDeviceParamMap().entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();

            if (thisEntry.getValue().equals("")) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String createDevice(DeviceDTO deviceDTO, String emptyFill) {


        boolean result = checkForEmptyParameters(deviceDTO);

        if (result) {
            emptyFill = "Please fill all device's parameters";
            return emptyFill;
        }

        Device newDevice = mapper.singleMapper(deviceDTO, Device.class);

        deviceDAO.save(newDevice);
        historyService.createDevice(newDevice);

        emptyFill = "";
        return emptyFill;
    }

    @Override
    public List<DeviceDTO> findDevice(String name) {

        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setModelName(name);

        Device device = mapper.singleMapper(deviceDTO, Device.class);
        // Device device = transformer.deviceDTOtoDevice(deviceDTO);

        List<Device> deviceList = deviceDAO.findDeviceByDeviceIDOrModelNameOrSerialNumberOrTypeOSOrOSVersionOrSkreenResolutionOrScreenDiagonalOrDescriptionOrRAMOrROMOrLocation(device.getDeviceID(), device.getModelName(), device.getSerialNumber(), device.getTypeOS(), device.getoSVersion(), device.getSkreenResolution(), device.getScreenDiagonal(), device.getDescription(), device.getrAM(), device.getrOM(), device.getLocation());

        deviceList = deviceDAO.findByModelNameContaining(name);

        deviceList = sortBySharing(deviceList);

        deviceDTOList = mapper.listMapToList(deviceList, DeviceDTO.class);
        deviceDTOList = sortDevices(deviceDTOList);
        return deviceDTOList;

    }

    @Override
    public ModelAndView findetailDeviceInformations(HttpServletRequest request) {

        String deviceID = request.getParameter("param");
        Device device = deviceDAO.findOne(new Integer(deviceID));

        DeviceDTO deviceDTO = mapper.singleMapper(device, DeviceDTO.class);

        //защита от рекурсии
        /*
        UserDTO user = deviceDTO.getUserDTO();
        if (user != null) {
            user.setDeviceDTOs(null);
            deviceDTO.setUserDTO(user);
        }
*/
        ModelAndView modelAndView = new ModelAndView("/device/detail_info.jsp");
        modelAndView.addObject("device", deviceDTO);
        return modelAndView;
    }

    @Override
    public List<DeviceDTO> findAllDevice() {

        List<Device> deviceList = (List<Device>) deviceDAO.findAll();

        deviceList = sortBySharing(deviceList);

        List<DeviceDTO> deviceDTOList = mapper.listMapToList(deviceList, DeviceDTO.class);

        deviceDTOList = sortDevices(deviceDTOList);

        return deviceDTOList;
    }

    @Override
    public List<DeviceDTO> findSelectedDevices(String[] ids) {

        List<Integer> id = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            id.add(new Integer(ids[i]));

        }

        List<Device> deviceList = (List<Device>) deviceDAO.findAll(id);
        List<DeviceDTO> deviceDTOList = mapper.listMapToList(deviceList, DeviceDTO.class);

        return deviceDTOList;
    }

    @Override
    public DeviceDTO findDeviceDTO(String id) {

        Device device = deviceDAO.findOne(new Integer(id));

        DeviceDTO deviceDTO = mapper.singleMapper(device, DeviceDTO.class);


        return deviceDTO;
    }

    @Override
    public void updateDevice(List<DeviceDTO> deviceDTOList) {
        List<Device> deviceList = mapper.listMapToList(deviceDTOList, Device.class);

        deviceDAO.save(deviceList);
    }

    @Override
    public String updateOneDevice(DeviceDTO deviceDTO, String emptyFill) {

        boolean result = checkForEmptyParameters(deviceDTO);

        if (result) {
            emptyFill = "Please fill all device's parameters";
            return emptyFill;
        }

        DeviceDTO oldDeviceDTO = findDeviceDTO(deviceDTO.getDeviceID());
        //we add to changed debive a user from old device;
        deviceDTO.setUserDTO(oldDeviceDTO.getUserDTO());

        Device device = mapper.singleMapper(deviceDTO, Device.class);

        Device oldDevice = mapper.singleMapper(oldDeviceDTO, Device.class);

        deviceDAO.save(device);
        historyService.updateDevice(oldDevice, device);
        emptyFill = "";
        return emptyFill;
    }

    @Override
    public String deleteDevice(String id, String error) {

        DeviceDTO deviceDTO = findDeviceDTO(id);

        if (deviceDTO.getUserDTO() != null) {
            error = "YOU CAN'T DELETE THIS DEVICE: " + deviceDTO.toString() + ", THE DEVICE  ASSIGNED AT USER [ " + deviceDTO.getUserDTO().toString() + " ]";

            return error;
        }
        Device device = mapper.singleMapper(deviceDTO, Device.class);

        deviceDAO.delete(device);

        historyService.deleteDevice(device);
        return error;

    }

    @Override
    public void unAssignAssignProcess(HttpServletRequest request) {
        String action = request.getParameter("button");

        String[] numbersOfDeviceIDforAnAssign = request.getParameterValues("ch");
        String[] numbersOfDeviceIDforAssign = request.getParameterValues("che");

        if (action.equals("Unassign") && numbersOfDeviceIDforAnAssign != null) {

            List<DeviceDTO> selectedDeviceDTOList = findSelectedDevices(numbersOfDeviceIDforAnAssign);

            unAssignDevice(selectedDeviceDTOList);

            historyService.unassign(selectedDeviceDTOList);

        } else if (action.equals("Assign") && numbersOfDeviceIDforAssign != null) {

            String checkedUser = request.getParameter("users");

            assignDevice(numbersOfDeviceIDforAssign, checkedUser);

            historyService.assign(numbersOfDeviceIDforAssign, checkedUser);
        }
    }

    @Override
    public void unAssignDevice(List<DeviceDTO> selectedDeviceDTOList) {

        List<Device> selectedDeviceList = mapper.listMapToList(selectedDeviceDTOList, Device.class);

        List<Device> selectedDeviceListwWithoutUser = new ArrayList<>();
        for (int i = 0; i < selectedDeviceList.size(); i++) {
            Device device = selectedDeviceList.get(i);
            device.setUser(null);
            selectedDeviceListwWithoutUser.add(device);
        }

        deviceDAO.save(selectedDeviceListwWithoutUser);

    }

    @Override
    public void assignDevice(String[] deviceID, String userID) {

        List<Integer> iD = new ArrayList<>();
        for (int i = 0; i < deviceID.length; i++) {
            iD.add(new Integer(deviceID[i]));
        }

        int chUserID = new Integer(userID);

        List<Device> deviceList = (List<Device>) deviceDAO.findAll(iD);
        UserDTO userDTO = userService.findDTOUser(userID);
        User user = mapper.singleMapper(userDTO, User.class);

        for (Device device : deviceList) {
            device.setUser(user);
        }
        deviceDAO.save(deviceList);
    }


    @Override
    public List<Object> displayWorkSpace() {

        List<UserDTO> userDTOList = userService.findAllDTOUsers();

        Set<User> userIdSet = deviceDAO.findUserId();
        List<UserDTO> userDTOs = new ArrayList<>();mapper.listMapToList(userIdSet, UserDTO.class);

        List<Device> freeDevicesList = deviceDAO.getFreeDevices();
        List<Device> assignedDevicesList = deviceDAO.getAssignedDevices();

        List<DeviceDTO> assignedDeviceDTOList = mapper.listMapToList(assignedDevicesList, DeviceDTO.class);
        List<DeviceDTO> freeDevicesDTOList = mapper.listMapToList(freeDevicesList, DeviceDTO.class);

        List<Object> data = new ArrayList<>();

        for (User user : userIdSet) {
            UserDTO userDTO = mapper.singleMapper(user, UserDTO.class);
            List<DeviceDTO> deviceDTOs = mapper.listMapToList(user.getDevices(), DeviceDTO.class);
            userDTO.setDeviceDTOs(deviceDTOs);
            userDTOs.add(userDTO);
        }

        data.add(userDTOs);
        data.add(assignedDeviceDTOList);
        data.add(freeDevicesDTOList);
        data.add(userDTOList);
        return data;

    }


}
