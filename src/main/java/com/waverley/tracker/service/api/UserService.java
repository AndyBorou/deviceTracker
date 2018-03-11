package com.waverley.tracker.service.api;

import com.waverley.tracker.dto.UserDTO;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * Created by Andrey on 11/10/2016.
 */
public interface UserService {


    boolean  checkForEmptyParameters(UserDTO userDTO);

    String createUserDTO(UserDTO userDTO, String emptyFill);

    List<UserDTO> findUserDTO(UserDTO userDTO);

    List<UserDTO> findAllDTOUsers();

    UserDTO findDTOUser(String id);

    ModelAndView findDTOUser(HttpServletRequest request);

    List<UserDTO> findSelectedDTOUsers(String[] ids);

    List<UserDTO> findUsersDTObyNameBySerName(String nameOrSerName);



    String deleteUser(String id, String error);

    void  updateUsers(List<UserDTO> userDTOList);

    String updateUser(UserDTO userDTO, String emptyFill);
}
