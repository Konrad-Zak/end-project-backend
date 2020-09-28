package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.repository.AppUserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserInfoDbService {

    private AppUserInfoRepository appUserInfoRepository;

    public List<AppUserInfo> getAllAppUserInfo(){
        return appUserInfoRepository.findAll();
    }

    public void saveAppUserInfo(AppUserInfo appUserInfo){
        appUserInfoRepository.save(appUserInfo);
    }

    public Optional<AppUserInfo> getAppUserInfoByAppUserId(Long appUserId) {
        return appUserInfoRepository.findByAppUserId(appUserId);
    }

    public Boolean checkAppUserInfoExist(Long appUserInfoId) {
        return appUserInfoRepository.existsById(appUserInfoId);
    }

    public Boolean deleteByAppUserId(Long appUserId) {
        return appUserInfoRepository.deleteByAppUser_Id(appUserId);
    }

}
