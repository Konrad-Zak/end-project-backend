package com.kodilla.projectbackend.configuration;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.mapper.AppUserInfoMapper;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
import com.kodilla.projectbackend.service.AppUserInfoDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserConfiguration.class);

    public UserConfiguration(AppUserDbService appUserDbService, AppUserInfoDbService appUserInfoDbService,
                             AppUserInfoMapper appUserInfoMapper, AppUserMapper appUserMapper) {
        AppUserDto appUserDto = new AppUserDto("user","user","ROLE_USER");
        if(!appUserDbService.checkExistsByUsername(appUserDto.getUsername())){
            AppUser appUser = appUserMapper.mapToAppUser(appUserDto);
            appUserDbService.saveAppUser(appUser);
            AppUserInfoDto appUserInfoDto = new AppUserInfoDto("UserName","u@kodilla.com",appUser);
            appUserInfoDbService.saveAppUserInfo(appUserInfoMapper.mapToAppUserInfo(appUserInfoDto));
            LOGGER.info("Create User account in the database");
        } else {
            LOGGER.info("Found User account in the database");
        }
    }
}
