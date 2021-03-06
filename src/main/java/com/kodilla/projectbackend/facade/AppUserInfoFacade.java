package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.mapper.AppUserInfoMapper;
import com.kodilla.projectbackend.service.AppUserInfoDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
@AllArgsConstructor
public class AppUserInfoFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserInfoFacade.class);
    private AppUserInfoDbService appUserInfoDbService;
    private AppUserInfoMapper appUserInfoMapper;

    public AppUserInfoDto getAppUserInfoByAppUserId(Long appUserId) {
        try {
            LOGGER.debug("Request: get appUserInfo of appUserId: " + appUserId);
            return appUserInfoMapper.mapToAppUserInfoDto(appUserInfoDbService.getAppUserInfoByAppUserId(appUserId));
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public List<AppUserInfoDto> getAppUsersInfo() {
        LOGGER.debug("Request: get all appInfoUsers ");
        return appUserInfoMapper.mapToAppUserInfoDtoList(appUserInfoDbService.getAllAppUserInfo());
    }

    public Boolean createAppUserInfo(AppUserInfoDto appUserInfoDto) {
        try{
            AppUserInfo appUserInfo = appUserInfoMapper.mapToAppUserInfo(appUserInfoDto);
            appUserInfoDbService.saveAppUserInfo(appUserInfo);
            LOGGER.info("Request: create new appUserInfo ");
            return appUserInfoDbService.checkAppUserInfoExist(appUserInfo.getId());
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public void updateAppUserInfo(AppUserInfoDto appUserInfoDto) {
        try {
            LOGGER.info("Request: update appUserInfo of id: " + appUserInfoDto.getId());
            appUserInfoDbService.saveAppUserInfo(appUserInfoMapper.mapToAppUserInfo(appUserInfoDto));
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}
