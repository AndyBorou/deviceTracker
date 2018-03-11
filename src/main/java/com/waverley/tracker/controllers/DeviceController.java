package com.waverley.tracker.controllers;

import com.waverley.tracker.dto.DeviceDTO;
import com.waverley.tracker.dto.HistoryDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.User;
import com.waverley.tracker.service.api.DeviceService;
import com.waverley.tracker.service.api.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 1/6/2017.
 */

@Controller
public class DeviceController {

    @Autowired
    DeviceService deviceService;


    @RequestMapping(value = "/searchDeviceAjax", method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceDTO> findUserAJAX(String name) {

        List<DeviceDTO> deviceDTOList = deviceService.findDevice(name);
        return deviceDTOList;
    }

    @RequestMapping(value = "/detailDeviceInformations", method = RequestMethod.GET)
    public ModelAndView detailUserInformation(HttpServletRequest request) {

        ModelAndView modelAndView = deviceService.findetailDeviceInformations(request);

        return modelAndView;
    }

    @RequestMapping(value = "/findAllDevicesAJAX", method = RequestMethod.GET)
    @ResponseBody
    public List<DeviceDTO> findAlldeviceListAJAX() {

        List<DeviceDTO> deviceDTOList = deviceService.findAllDevice();

        return deviceDTOList;
    }

}


