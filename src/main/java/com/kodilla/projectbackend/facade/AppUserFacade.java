package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.configuration.AdminConfiguration;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
@AllArgsConstructor
public class AppUserFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserFacade.class);
    private AppUserDbService appUserDbService;
    private PasswordEncoder passwordEncoder;
    private AppUserMapper appUserMapper;

    public List<AppUserDto> getAppUsers(){
        LOGGER.debug("Request: get all users");
        return appUserMapper.mapToAppUserDtoList(appUserDbService.getAllAppUsers());
    }

    public Boolean createAppUser(@RequestParam String username, @RequestParam String password){
        AppUserDto appUserDto = new AppUserDto(username,passwordEncoder.encode(password));
        LOGGER.info("Request: create new user: " + username);
        appUserDbService.saveAppUser(appUserMapper.mapToAppUser(appUserDto));
        return appUserDbService.checkExistsByUsername(username);
    }

    public void updateAppUser(@RequestParam Long appUserId, @RequestParam String username, @RequestParam String password){
        AppUserDto appUserDto = new AppUserDto(appUserId,username,passwordEncoder.encode(password));
        LOGGER.info("Request: update user: " + username);
        appUserDbService.saveAppUser(appUserMapper.mapToAppUser(appUserDto));
    }

    public void deleteAppUser(@RequestParam Long appUserId){
        LOGGER.info("Request: delete user with id: " + appUserId);
        appUserDbService.deleteAppUser(appUserId);
    }

    public AppUserDto getAppUserByUsername(@RequestParam  String username){
        LOGGER.debug("Request: get user: " + username);
        return appUserMapper.mapToAppUserDto(appUserDbService.loadUserByUsername(username));
    }

    public Boolean checkExistByUsername(@RequestParam String username){
        LOGGER.debug("Request: Check exist user: " + username);
        return appUserDbService.checkExistsByUsername(username);
    }

}
