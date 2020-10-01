package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.domian.CalorieInfo;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
import com.kodilla.projectbackend.service.AppUserInfoDbService;
import com.kodilla.projectbackend.service.CalorieInfoService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AppUserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserFacade.class);
    private AppUserDbService appUserDbService;
    private AppUserInfoDbService appUserInfoDbService;
    private CalorieInfoService calorieInfoService;
    private AppUserMapper appUserMapper;

    public List<AppUserDto> getAppUsers() {
        LOGGER.debug("Request: get all users");
        return appUserMapper.mapToAppUserDtoList(appUserDbService.getAllAppUsers());
    }

    public Boolean createAppUser(String username, String password) {
        AppUserDto appUserDto = new AppUserDto(username,password);
        LOGGER.info("Request: create new user: " + username);
        appUserDbService.saveAppUser(appUserMapper.mapToAppUser(appUserDto));
        return appUserDbService.checkExistsByUsername(username);
    }

    public void updateAppUser(AppUserDto appUserDto) {
        LOGGER.info("Request: update user: " + appUserDto.getUsername());
        appUserDbService.saveAppUser(appUserMapper.mapToAppUser(appUserDto));
    }

    public void deleteAppUser(Long appUserId) {
        if(appUserDbService.existByAppUserId(appUserId)){
            deleteProcess(appUserId);
        } else {
            throw new UserNotFoundException();
        }
    }

    public AppUserDto getAppUserByUsername(String username) {
        LOGGER.debug("Request: get user: " + username);
        return appUserMapper.mapToAppUserDto(appUserDbService.loadUserByUsername(username));
    }

    public Boolean checkExistByUsername(String username) {
        LOGGER.debug("Request: Check exist user: " + username);
        return appUserDbService.checkExistsByUsername(username);
    }

    private void deleteProcess(Long appUserId) {
        AppUserInfo appUserInfo = appUserInfoDbService.getAppUserInfoByAppUserId(appUserId);
        CalorieInfo calorieInfo = calorieInfoService.getCalorieInfoByAppUserId(appUserId);
        Boolean resultCalorieInfoDelete = calorieInfoService.deleteByCalorieInfoId(calorieInfo.getId());
        Boolean resultAppUserDelete = appUserDbService.deleteAppUser(appUserId);
        Boolean resultAppUserInfoDelete = appUserInfoDbService.deleteByAppUserId(appUserInfo.getId());
        if(!resultAppUserInfoDelete && !resultAppUserDelete && !resultCalorieInfoDelete){
            LOGGER.info("Request: Complete delete user with id: " + appUserId);
        } else {
            LOGGER.info("Request: delete user with id: " + appUserId + " - not possible");
        }
    }

}
