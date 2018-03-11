package com.waverley.tracker.dao.api;

import com.waverley.tracker.dto.UserDTO;
import com.waverley.tracker.model.Device;
import com.waverley.tracker.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by Andrey on 1/6/2017.
 */

public interface DeviceDAO extends CrudRepository <Device,Integer> {

    List<Device> findDeviceByDeviceIDOrModelNameOrSerialNumberOrTypeOSOrOSVersionOrSkreenResolutionOrScreenDiagonalOrDescriptionOrRAMOrROMOrLocation(int deviceID, String modelName, String serialNumber, String typeOS, String oSVersion, String skreenResolution, String screenDiagonal, String description, String rAM, String rOM, String location);


    @Modifying
    @Query("UPDATE Device d  SET d.user.id = NULL WHERE d.id = ?1")
    void deleteDeviceIDColumn ( Integer id);

    @Query("SELECT d FROM Device d  WHERE  d.user.id is null")
    List<Device> getFreeDevices();

    @Query("SELECT d FROM Device d  WHERE  d.user.id is NOT null ")
    List<Device> getAssignedDevices();

    @Query("SELECT d.user FROM Device d")
    Set<User> findUserId();

    List<Device> findByModelNameContaining(String modelName);


}
