package com.waverley.tracker.dao.api;

import com.waverley.tracker.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrey on 1/10/2017.
 */

public interface UserDAO extends CrudRepository<User, Integer> {

    List<User> findUserByIdOrNameOrSernameOrLocationOrRoleOrLoginOrEmail(int id, String name, String sername, String location, String role, String login, String email);

    Set<User> findUserByName(String name);

    Set<User> findUserBySername(String sername);

}
