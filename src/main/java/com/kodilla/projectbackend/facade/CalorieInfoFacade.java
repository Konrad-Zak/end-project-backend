package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.*;
import com.kodilla.projectbackend.exception.UserIdException;
import com.kodilla.projectbackend.mapper.AppUserCalorieMapper;
import com.kodilla.projectbackend.mapper.CalorieInfoMapper;
import com.kodilla.projectbackend.service.AppUserCalorieDbService;
import com.kodilla.projectbackend.service.AppUserDbService;
import com.kodilla.projectbackend.service.CalorieCalculateService;
import com.kodilla.projectbackend.service.CalorieInfoDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CalorieInfoFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CalorieInfoFacade.class);
    private CalorieCalculateService calorieCalculateService;
    private AppUserDbService appUserDbService;
    private CalorieInfoDbService calorieInfoDbService;
    private AppUserCalorieDbService appUserCalorieDbService;
    private AppUserCalorieMapper appUserCalorieMapper;
    private CalorieInfoMapper calorieInfoMapper;


    public Boolean createAppCalorieInfo(Long appUserId, Double weight, Double fitness) {
        if (!calorieInfoDbService.checkExistByAppUserId(appUserId)){
            AppUserCalorie appUserCalorie = appUserCalorieMapper
                    .mapToAppUserCalorie(calorieCalculateService.calculateCalorie(weight,fitness));
            saveAppUserCalorie(appUserCalorie);

            AppUser appUser = appUserDbService.getAppUserById(appUserId);

            saveAppCalorieInfo(new CalorieInfoDto(weight,fitness,appUserCalorie,appUser));
            return calorieInfoDbService.checkExistByAppUserId(appUserId);
        } else {
            throw new UserIdException();
        }
    }

    public CalorieInfoDto getAppCalorieInfo(Long appUserId) {
        return calorieInfoMapper.simpleMapToCalorieInfoDto(calorieInfoDbService.getCalorieInfoByAppUserId(appUserId));
    }

    public void updateAppCalorieInfo(Long appUserId, Double weight, Double fitness) {
        CalorieInfoDto calorieInfoDto = calorieInfoMapper
                .mapToCalorieInfoDto(calorieInfoDbService.getCalorieInfoByAppUserId(appUserId));

        AppUserCalorieDto appUserCalorieDto = calorieCalculateService.calculateCalorie(weight,fitness);
        appUserCalorieDto.setId(calorieInfoDto.getAppUserCalorie().getId());
        AppUserCalorie appUserCalorie = appUserCalorieMapper.mapToAppUserCalorie(appUserCalorieDto);
        saveAppUserCalorie(appUserCalorie);

        calorieInfoDto.setNewValues(weight,fitness);
        saveAppCalorieInfo(calorieInfoDto);
    }

    public Boolean checkExistByAppUserId(Long appUserId) {
        return calorieInfoDbService.checkExistByAppUserId(appUserId);
    }

    private void saveAppUserCalorie(AppUserCalorie appUserCalorie) {
        LOGGER.debug("Request: create new appUserCalorie of id: " + appUserCalorie.getId());
        appUserCalorieDbService.createAppUserCalorie(appUserCalorie);
    }

    private void saveAppCalorieInfo(CalorieInfoDto calorieInfoDto) {
        LOGGER.debug("Request: create new appUserCalorie for appUserId: " + calorieInfoDto.getAppUser().getId());
        calorieInfoDbService.saveCalorieInfo(calorieInfoMapper.mapToCalorieInfo(calorieInfoDto));
    }

}
