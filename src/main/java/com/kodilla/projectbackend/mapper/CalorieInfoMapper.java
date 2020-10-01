package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.CalorieInfo;
import com.kodilla.projectbackend.domian.CalorieInfoDto;
import org.springframework.stereotype.Component;

@Component
public class CalorieInfoMapper {

    public CalorieInfo mapToCalorieInfo(CalorieInfoDto calorieInfoDto) {
        return new CalorieInfo(
                calorieInfoDto.getId(),calorieInfoDto.getAppUserWeight(),calorieInfoDto.getAppUserFitness(),
                calorieInfoDto.getAppUserCalorie(),calorieInfoDto.getAppUser());
    }

    public CalorieInfoDto mapToCalorieInfoDto(CalorieInfo calorieInfo) {
        return new CalorieInfoDto(calorieInfo.getId(),calorieInfo.getAppUserWeight(),calorieInfo.getAppUserFitness(),
                calorieInfo.getAppUserCalorie(), calorieInfo.getAppUser());
    }

    public CalorieInfoDto simpleMapToCalorieInfoDto(CalorieInfo calorieInfo) {
        return new CalorieInfoDto(calorieInfo.getId(),calorieInfo.getAppUserWeight(),
                calorieInfo.getAppUserFitness(),calorieInfo.getAppUserCalorie());
    }

}
