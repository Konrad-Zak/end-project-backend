package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.mapper.AppUserInfoMapper;
import com.kodilla.projectbackend.service.AppUserInfoDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AppUserInfoFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserInfoFacade.class);
    private AppUserInfoDbService appUserInfoDbService;
    private AppUserInfoMapper appUserInfoMapper;

    public AppUserInfoDto getAppUserInfoByAppUserId(Long appUserId) {
        LOGGER.debug("Request: get appUserInfo of appUserId: " + appUserId);
        return appUserInfoMapper.mapToAppUserInfoDto(appUserInfoDbService.getAppUserInfoByAppUserId(appUserId));
    }

    public List<AppUserInfoDto> getAppUsersInfo() {
        LOGGER.debug("Request: get all appInfoUsers ");
        return appUserInfoMapper.mapToAppUserInfoDtoList(appUserInfoDbService.getAllAppUserInfo());
    }

    public Boolean createAppUserInfo(AppUserInfoDto appUserInfoDto) {
        AppUserInfo appUserInfo = appUserInfoMapper.mapToAppUserInfo(appUserInfoDto);
        appUserInfoDbService.saveAppUserInfo(appUserInfo);
        LOGGER.info("Request: create new appUserInfo ");
        return appUserInfoDbService.checkAppUserInfoExist(appUserInfo.getId());
    }

    public void updateAppUserInfo(AppUserInfoDto appUserInfoDto) {
        LOGGER.info("Request: update appUserInfo of id: " + appUserInfoDto.getId());
        appUserInfoDbService.saveAppUserInfo(appUserInfoMapper.mapToAppUserInfo(appUserInfoDto));
    }
}
