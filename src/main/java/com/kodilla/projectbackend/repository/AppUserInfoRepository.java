package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.AppUserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AppUserInfoRepository extends CrudRepository<AppUserInfo, Long> {

    Optional<AppUserInfo> findByAppUserId(Long appUserId);
}
