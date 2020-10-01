package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUserCalorie;
import com.kodilla.projectbackend.domian.AppUserCalorieDto;
import org.springframework.stereotype.Component;


@Component
public class AppUserCalorieMapper {

    public AppUserCalorie mapToAppUserCalorie(AppUserCalorieDto appUserCalorieDto){
        return new AppUserCalorie(appUserCalorieDto.getId(),appUserCalorieDto.getProtein(),appUserCalorieDto.getFat(),
                appUserCalorieDto.getCarbohydrates(),appUserCalorieDto.getCalories());
    }
}
