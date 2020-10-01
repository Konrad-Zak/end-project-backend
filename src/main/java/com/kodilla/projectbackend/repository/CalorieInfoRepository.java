package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.CalorieInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CalorieInfoRepository extends CrudRepository<CalorieInfo, Long> {

    Optional<CalorieInfo> findByAppUserId(Long appUserId);

    Boolean existsByAppUser_Id(Long appUserId);

}
