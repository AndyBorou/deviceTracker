package com.waverley.tracker.service.api;

import com.waverley.tracker.dto.DeviceDTO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey on 11/10/2016.
 */

public interface DeviceService {


    boolean checkForEmptyParameters(DeviceDTO deviceDTO);

    String createDevice(DeviceDTO deviceDTO, String emptyFill);

    List<DeviceDTO> findDevice(String deviceName);

    ModelAndView findetailDeviceInformations(HttpServletRequest request);

    List<DeviceDTO> findAllDevice();

    List<DeviceDTO> findSelectedDevices(String[] ids);

    DeviceDTO findDeviceDTO(String id);

    void updateDevice(List<DeviceDTO> deviceDTOList);

    String updateOneDevice(DeviceDTO deviceDTO, String emptyFill);

    String deleteDevice(String id, String error);

    List<Object> displayWorkSpace();

    void unAssignAssignProcess(HttpServletRequest request);

    void unAssignDevice(List<DeviceDTO> selectedDeviceDTOList);

    void assignDevice (String [] DeviceID, String userID);


}
