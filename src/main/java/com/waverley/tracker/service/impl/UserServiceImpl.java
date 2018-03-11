package com.waverley.tracker.service.impl;


import com.waverley.tracker.dao.api.UserDAO;

import com.waverley.tracker.dto.DeviceDTO;
import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.mapper.BasicMapper;

import com.waverley.tracker.mapper.BasicMapper;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.User;
import com.waverley.tracker.service.api.HistoryService;
import com.waverley.tracker.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Andrey on 11/12/2016.
 */
@Transactional
@Component
public class UserServiceImpl implements UserService {

    @Resource
    UserDAO userDAO;
    @Autowired
    BasicMapper mapper;
    @Autowired
    HistoryService historyService;



    public List<UserDTO> mappingAssignDevicesAndSort(List<User> userList) {

        List<UserDTO> userDTOListSort = new LinkedList<>();

        int a = 0;
        for (User user : userList) {
            UserDTO userDTO = mapper.singleMapper(user, UserDTO.class);
            List<DeviceDTO> deviceDTOs = mapper.listMapToList(user.getDevices(), DeviceDTO.class);
            userDTO.setDeviceDTOs(deviceDTOs);
            if(deviceDTOs.size() != 0){
                deviceDTOs = sortBySharing(deviceDTOs);
                userDTO.setDeviceDTOs(deviceDTOs);
                userDTOListSort.add(a, userDTO);
                a++;
            } else {
                userDTOListSort.add(userDTO);
            }
        }
        return userDTOListSort;
    }

    public List<DeviceDTO> sortBySharing(List<DeviceDTO> deviceDTOlist) {

        Collection<? extends GrantedAuthority> role = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        String thisRole = role.toString();
        if(!thisRole.equals("[ROLE_ADMIN]")) {
            List<DeviceDTO> deviceListSort = new ArrayList<>();
            for (int i = 0; i < deviceDTOlist.size(); i++) {
                String shared = deviceDTOlist.get(i).getShared();
                if (shared != null && shared.equals("user")) {
                    deviceListSort.add(deviceDTOlist.get(i));
                }
            }
            deviceDTOlist = deviceListSort;
        }
        return deviceDTOlist;
    }

    @Override
    public boolean checkForEmptyParameters(UserDTO userDTO) {

        Iterator entries = userDTO.getUserMAp().entrySet().iterator();

        while (entries.hasNext()) {
            Map.Entry thisEntry = (Map.Entry) entries.next();
            if (thisEntry.getValue().equals("")) {
                return true;
            }
        }

        return false;

    }

    @Override
    public String createUserDTO(UserDTO userDTO, String emptyFill) {

        boolean result = checkForEmptyParameters(userDTO);

        if (result) {
            emptyFill = "Please fill all user's parameters";
            return emptyFill;
        }

        User user = mapper.singleMapper(userDTO, User.class);
        userDAO.save(user);

        //checking that the new user was created
        List<User> userList = userDAO.findUserByIdOrNameOrSernameOrLocationOrRoleOrLoginOrEmail(user.getId(), user.getName(), user.getSername(), user.getLocation(), user.getRole(), user.getLogin(), user.getEmail());
        historyService.createUser(userList.get(0));

        emptyFill = "";
        return emptyFill;

    }

    @Override
    public List<UserDTO> findUserDTO(UserDTO userDTO) {

        User user = mapper.singleMapper(userDTO, User.class);

        List<User> userList = userDAO.findUserByIdOrNameOrSernameOrLocationOrRoleOrLoginOrEmail(user.getId(), user.getName(), user.getSername(), user.getLocation(), user.getRole(), user.getLogin(), user.getEmail());

        List<UserDTO> userDTOList = mapper.listMapToList(userList, UserDTO.class);

        return userDTOList;
    }


    @Override
    public List<UserDTO> findAllDTOUsers() {

        List<User> userList = (List<User>) userDAO.findAll();


        List<UserDTO> userDTOList = mappingAssignDevicesAndSort(userList);

        return userDTOList;
    }

    @Override
    public UserDTO findDTOUser(String id) {
        User user = userDAO.findOne(new Integer(id));
        UserDTO userDTO = mapper.singleMapper(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public ModelAndView findDTOUser(HttpServletRequest request) {
        String userId = request.getParameter("param");
        UserDTO userDTO = (UserDTO) findDTOUser(userId);

        ModelAndView modelAndView = new ModelAndView("/user/detail_info.jsp");
        modelAndView.addObject("userDTO", userDTO);

        return modelAndView;
    }

    @Override
    public List<UserDTO> findSelectedDTOUsers(String[] ids) {

        List<Integer> id = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            id.add(new Integer(ids[i]));
        }

        List<User> userList = (List<User>) userDAO.findAll(id);
        List<UserDTO> userDTOList = mapper.listMapToList(userList, UserDTO.class);


        return userDTOList;
    }

    @Override
    public List<UserDTO> findUsersDTObyNameBySerName(String nameOrSerName) {

        Set<User> findUserSet = userDAO.findUserByName(nameOrSerName);
        findUserSet.addAll(userDAO.findUserBySername(nameOrSerName));

        List<User> userList = new ArrayList<>();
        userList.addAll(findUserSet);

        List<UserDTO> userDTOList = mappingAssignDevicesAndSort(userList);

        return userDTOList;
    }

    @Override
    public String deleteUser(String id, String error) {
        User user = userDAO.findOne(new Integer(id));
        if (user.getDevices() != null && user.getDevices().size() != 0) {
            UserDTO userDTO = mapper.singleMapper(user, UserDTO.class);
            List<DeviceDTO> deviceDTOs = mapper.listMapToList(user.getDevices(), DeviceDTO.class);
            error = "YOU CAN'T DELETE THIS USER: " + userDTO.toString() + ", DEVICE(S) ASSIGNED AT HIM [ " + deviceDTOs.toString() + " ]";
        }else {
            historyService.deleteUser(user);
            userDAO.delete(user);
        }

        return error;
    }

    @Override
    public void updateUsers(List<UserDTO> userDTOList) {

        List<User> userList = mapper.listMapToList(userDTOList, User.class);

        userDAO.save(userList);
    }

    @Override
    public String updateUser(UserDTO userDTO, String emptyFill) {

        boolean result = checkForEmptyParameters(userDTO);

        if (result) {
            emptyFill = "Please fill all user's parameters";
            return emptyFill;
        }

        //    receiving old user
        UserDTO oldUserDTO = findDTOUser(userDTO.getId());


        User oldUser = mapper.singleMapper(oldUserDTO, User.class);

        User user = mapper.singleMapper(userDTO, User.class);

        userDAO.save(user);
        historyService.updateUser(oldUser, user);

        emptyFill = "";
        return emptyFill;
    }

}
