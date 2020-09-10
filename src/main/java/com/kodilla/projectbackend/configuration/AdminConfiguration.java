package com.kodilla.projectbackend.configuration;

import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminConfiguration.class);

    public AdminConfiguration(AppUserDbService appUserDbService) {
        AppUserDto appUserDto = new AppUserDto("ADMIN","ADMIN","ROLE_ADMIN");
        if(!appUserDbService.checkExistsByUsername(appUserDto.getUsername())){
            appUserDbService.saveAppUser(AppUserMapper.getInstance().mapToAppUser(appUserDto));
            LOGGER.info("Create Admin account in the database");
        } else {
            LOGGER.info("Found Admin account in the database");
        }
    }

}
