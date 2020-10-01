package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.repository.AppUserInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public AppUserInfo getAppUserInfoByAppUserId(Long appUserId) {
        return appUserInfoRepository.findByAppUserId(appUserId).orElseThrow(UserNotFoundException::new);
    }

    public Boolean checkAppUserInfoExist(Long appUserInfoId) {
        return appUserInfoRepository.existsById(appUserInfoId);
    }

    public Boolean deleteByAppUserId(Long appUserInfoId) {
        appUserInfoRepository.deleteById(appUserInfoId);
        return appUserInfoRepository.existsById(appUserInfoId);
    }

}
