package com.waverley.tracker.controllers;

import com.waverley.tracker.dto.DeviceDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.helper.Transformer;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.User;
import com.waverley.tracker.service.api.DeviceService;
import com.waverley.tracker.service.api.HistoryService;
import com.waverley.tracker.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Andrey on 3/31/2017.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private DeviceService deviceService;

    @RequestMapping( value = "/createNewUserAJAX", method = RequestMethod.GET)
    @ResponseBody
    public String createNewUser(@Valid UserDTO userDTO, String emptyFill) {
        emptyFill = userService.createUserDTO(userDTO, emptyFill);
        return emptyFill;
    }

    @RequestMapping(value = "/updateUserAJAX", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(@Valid UserDTO userDTO, String emptyFill) {

        emptyFill = userService.updateUser(userDTO, emptyFill);

        return emptyFill;
    }

    @RequestMapping(value = "/deleteUserAJAX", method = RequestMethod.GET)
    @ResponseBody
    public String deleteUserAJAX(String id, String error) {

        error = userService.deleteUser(id, error);

        return error;
    }

    @RequestMapping(value = "/createDevicesAJAX", method = RequestMethod.POST)
    @ResponseBody
    public String createNewDevice(@Valid DeviceDTO deviceDTO, String emptyFill, BindingResult result) {

        emptyFill = deviceService.createDevice(deviceDTO, emptyFill);

        return emptyFill;
    }

    @RequestMapping(value = "/updateDevicesAJAX", method = RequestMethod.POST)
    @ResponseBody
    public String updateDevicesAJAX(@Valid DeviceDTO deviceDTO, String emptyFill, BindingResult result) {

        emptyFill = deviceService.updateOneDevice(deviceDTO, emptyFill);

        return emptyFill;
    }

    @RequestMapping(value = "/deleteDeviceAJAX", method = RequestMethod.GET)
    @ResponseBody
    public String deleteDeviceAJAX(String id, String error) {

        error = deviceService.deleteDevice(id, error);

        return error;
    }

    @RequestMapping(value = "/workAjax", method = RequestMethod.GET)
    @ResponseBody
    public List<Object> workAjax() {

        List<Object> data = deviceService.displayWorkSpace();

        return data;
    }

    @RequestMapping(value = "/unAssignAssignAjax", method = RequestMethod.GET)
    public String assignUnAssignAjax(HttpServletRequest request, HttpServletResponse response) {

        deviceService.unAssignAssignProcess(request);

        return "redirect:/resources/jsp/home.jsp?param=workSpace";
    }
}
