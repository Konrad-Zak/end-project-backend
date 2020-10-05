package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserCalorie;
import com.kodilla.projectbackend.domian.CalorieInfo;
import com.kodilla.projectbackend.domian.CalorieInfoDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class CalorieInfoMapperTest {

    private CalorieInfoMapper calorieInfoMapper = new CalorieInfoMapper();

    @Test
    public void mapToCalorieInfo() {
        //Given
        AppUser appUser = new AppUser(10L,"John","1234","ROLE_USER");
        AppUserCalorie appUserCalorie = new AppUserCalorie(
                4L,60.0,50.0,120.0,1000.0);
        CalorieInfoDto calorieInfoDto = new CalorieInfoDto(
                4L,50.0,2.0,appUserCalorie,appUser);
        //When
        CalorieInfo calorieInfo = calorieInfoMapper.mapToCalorieInfo(calorieInfoDto);
        //Then
        assertEquals(calorieInfoDto.getId(),calorieInfo.getId());
        assertEquals(calorieInfoDto.getAppUserWeight(),calorieInfo.getAppUserWeight());
        assertEquals(calorieInfoDto.getAppUserFitness(),calorieInfo.getAppUserFitness());
        assertEquals(calorieInfoDto.getAppUser().getId(),calorieInfo.getAppUser().getId());
        assertEquals(calorieInfoDto.getAppUser().getPassword(),calorieInfo.getAppUser().getPassword());
        assertEquals(calorieInfoDto.getAppUser().getRole(),calorieInfo.getAppUser().getRole());
        assertEquals(calorieInfoDto.getAppUser().getUsername(),calorieInfo.getAppUser().getUsername());
        assertEquals(calorieInfoDto.getAppUserCalorie().getId(),calorieInfo.getAppUserCalorie().getId());
        assertEquals(calorieInfoDto.getAppUserCalorie().getFat(),calorieInfo.getAppUserCalorie().getFat());
        assertEquals(calorieInfoDto.getAppUserCalorie().getProtein(),calorieInfo.getAppUserCalorie().getProtein());
        assertEquals(calorieInfoDto.getAppUserCalorie().getCalories(),calorieInfo.getAppUserCalorie().getCalories());
        assertEquals(calorieInfoDto.getAppUserCalorie().getCarbohydrates(),
                calorieInfo.getAppUserCalorie().getCarbohydrates());
    }

    @Test
    public void mapToCalorieInfoDto() {
        //Given
        AppUser appUser = new AppUser(10L,"John","1234","ROLE_USER");
        AppUserCalorie appUserCalorie = new AppUserCalorie(
                4L,60.0,50.0,120.0,1000.0);
        CalorieInfo calorieInfo = new CalorieInfo(
                4L,50.0,2.0,appUserCalorie,appUser);
        //When
        CalorieInfoDto calorieInfoDto = calorieInfoMapper.mapToCalorieInfoDto(calorieInfo);
        //Then
        assertEquals(calorieInfo.getId(),calorieInfoDto.getId());
        assertEquals(calorieInfo.getAppUserWeight(),calorieInfoDto.getAppUserWeight());
        assertEquals(calorieInfo.getAppUserFitness(),calorieInfoDto.getAppUserFitness());
        assertEquals(calorieInfo.getAppUser().getId(),calorieInfoDto.getAppUser().getId());
        assertEquals(calorieInfo.getAppUser().getPassword(),calorieInfoDto.getAppUser().getPassword());
        assertEquals(calorieInfo.getAppUser().getRole(),calorieInfoDto.getAppUser().getRole());
        assertEquals(calorieInfo.getAppUser().getUsername(),calorieInfoDto.getAppUser().getUsername());
        assertEquals(calorieInfo.getAppUserCalorie().getId(),calorieInfoDto.getAppUserCalorie().getId());
        assertEquals(calorieInfo.getAppUserCalorie().getFat(),calorieInfoDto.getAppUserCalorie().getFat());
        assertEquals(calorieInfo.getAppUserCalorie().getProtein(),calorieInfoDto.getAppUserCalorie().getProtein());
        assertEquals(calorieInfo.getAppUserCalorie().getCalories(),calorieInfoDto.getAppUserCalorie().getCalories());
        assertEquals(calorieInfo.getAppUserCalorie().getCarbohydrates(),
                calorieInfoDto.getAppUserCalorie().getCarbohydrates());
    }

    @Test
    public void simpleMapToCalorieInfoDto() {
        //Given
        AppUser appUser = new AppUser(10L,"John","1234","ROLE_USER");
        AppUserCalorie appUserCalorie = new AppUserCalorie(
                4L,60.0,50.0,120.0,1000.0);
        CalorieInfo calorieInfo = new CalorieInfo(
                4L,50.0,2.0,appUserCalorie,appUser);
        //When
        CalorieInfoDto calorieInfoDto = calorieInfoMapper.simpleMapToCalorieInfoDto(calorieInfo);
        //Then
        assertEquals(calorieInfo.getId(),calorieInfoDto.getId());
        assertEquals(calorieInfo.getAppUserWeight(),calorieInfoDto.getAppUserWeight());
        assertEquals(calorieInfo.getAppUserFitness(),calorieInfoDto.getAppUserFitness());
        assertEquals(calorieInfo.getAppUserCalorie().getId(),calorieInfoDto.getAppUserCalorie().getId());
        assertEquals(calorieInfo.getAppUserCalorie().getFat(),calorieInfoDto.getAppUserCalorie().getFat());
        assertEquals(calorieInfo.getAppUserCalorie().getProtein(),calorieInfoDto.getAppUserCalorie().getProtein());
        assertEquals(calorieInfo.getAppUserCalorie().getCalories(),calorieInfoDto.getAppUserCalorie().getCalories());
        assertEquals(calorieInfo.getAppUserCalorie().getCarbohydrates(),
                calorieInfoDto.getAppUserCalorie().getCarbohydrates());
    }
}