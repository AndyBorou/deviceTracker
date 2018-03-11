package com.waverley.tracker.controllers;


import com.waverley.tracker.dto.HistoryDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.service.api.DeviceService;
import com.waverley.tracker.service.api.HistoryService;
import com.waverley.tracker.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Andrey on 1/6/2017.
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    HistoryService historyService;
    @Autowired
    DeviceService deviceService;

    @RequestMapping(value = "/findAllUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> findAlluserListAJAX() {

        List<UserDTO> userDTOList = userService.findAllDTOUsers();

        return userDTOList;
    }

    @RequestMapping(value = "/searchUser2", method = RequestMethod.GET)
    @ResponseBody
    public List<UserDTO> findUserAJAX(String name) {

        List<UserDTO> userDTOList = userService.findUsersDTObyNameBySerName(name);

        return userDTOList;
    }


    @RequestMapping(value = "/detailUserInformations", method = RequestMethod.GET)
    public ModelAndView detailUserInformation(HttpServletRequest request) {
        String userId = request.getParameter("param");

        ModelAndView modelAndView = userService.findDTOUser(request);

        return modelAndView;
    }
}