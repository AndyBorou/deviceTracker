package com.waverley.tracker.dao.api;

import com.waverley.tracker.model.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andrey on 1/19/2017.
 */

public interface HistoryDAO extends CrudRepository<History,Integer> {

    @Query(value = "SELECT * FROM history  WHERE (date BETWEEN ?1 and ?2) or (date BETWEEN ?2 and ?1)", nativeQuery = true)
    List<History> findByDates(String fDate, String lDate);

    List<History> findByDate(String date);

    List<History> findByEvent (String event);

    List<History> findByUserInfoContaining(String userInfo);

    List<History> findByDeviceInfoContaining(String deviceInfo);

    List<History> findByHistoryUserID(Integer historyUserID);

    List<History> findByHistoryDeviceID(Integer historyDeviceID);



}
