package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserCalorieDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalorieCalculateServiceTest {

    private CalorieCalculateService calorieCalculateService = new CalorieCalculateService();

    @Test
    public void calculateCalorie() {
        //Given
        AppUserCalorieDto appUserCalorieDto = new AppUserCalorieDto(
                120.0, 60.0, 225.0, 1920.0);
        //When
        AppUserCalorieDto appUserCalorieDtoResult = calorieCalculateService.calculateCalorie(60.0,2.0);
        //Then
        assertEquals(appUserCalorieDto.getProtein(), appUserCalorieDtoResult.getProtein());
        assertEquals(appUserCalorieDto.getFat(), appUserCalorieDtoResult.getFat());
        assertEquals(appUserCalorieDto.getCarbohydrates(), appUserCalorieDtoResult.getCarbohydrates());
        assertEquals(appUserCalorieDto.getCalories(), appUserCalorieDtoResult.getCalories());
    }
}