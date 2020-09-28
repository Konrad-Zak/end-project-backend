package com.kodilla.projectbackend.repository;

import com.kodilla.projectbackend.domian.AppUserInfo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserInfoRepository extends CrudRepository<AppUserInfo, Long> {

    @Override
    List<AppUserInfo>findAll();

    Optional<AppUserInfo> findByAppUserId(Long appUserId);

    boolean existsById (Long appUserInfoId);

    Boolean deleteByAppUser_Id(Long appUserId);
}
