package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.*;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.mapper.AppUserCalorieMapper;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.mapper.CalorieInfoMapper;
import com.kodilla.projectbackend.repository.AppUserCalorieRepository;
import com.kodilla.projectbackend.repository.AppUserRepository;
import com.kodilla.projectbackend.repository.CalorieInfoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CalorieInfoDbServiceTest {

    @Autowired
    private CalorieInfoDbService calorieInfoDbService;

    @Autowired
    private CalorieInfoRepository calorieInfoRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserCalorieRepository appUserCalorieRepository;

    @Autowired
    private AppUserCalorieMapper appUserCalorieMapper;

    @Autowired
    private AppUserMapper appUserMapper;

    @Autowired
    private CalorieInfoMapper calorieInfoMapper;

    @Test
    public void saveCalorieInfo() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("John","1234","ROLE_USER"));
        AppUserCalorie appUserCalorieOne = appUserCalorieMapper.mapToAppUserCalorie(
                new AppUserCalorieDto(20.0,50.0,70.0, 1000.0));
        CalorieInfo calorieInfo = calorieInfoMapper.mapToCalorieInfo(
                new CalorieInfoDto(50.0,2.0,appUserCalorieOne,appUserOne));
        appUserRepository.save(appUserOne);
        appUserCalorieRepository.save(appUserCalorieOne);
        //When
        calorieInfoDbService.saveCalorieInfo(calorieInfo);
        Optional<CalorieInfo> calorieInfoResult = calorieInfoRepository.findById(calorieInfo.getId());
        //Then
        assertTrue(calorieInfoResult.isPresent());
        assertEquals(calorieInfo.getId(),calorieInfoResult.get().getId());
        assertEquals(calorieInfo.getAppUserFitness(),calorieInfoResult.get().getAppUserFitness());
        assertEquals(calorieInfo.getAppUserWeight(),calorieInfoResult.get().getAppUserWeight());
        assertEquals(calorieInfo.getAppUser().getId(),calorieInfoResult.get().getAppUser().getId());
        assertEquals(calorieInfo.getAppUser().getUsername(),calorieInfoResult.get().getAppUser().getUsername());
        assertEquals(calorieInfo.getAppUser().getPassword(),calorieInfoResult.get().getAppUser().getPassword());
        assertEquals(calorieInfo.getAppUser().getRole(),calorieInfoResult.get().getAppUser().getRole());
        assertEquals(calorieInfo.getAppUserCalorie().getId(),calorieInfoResult.get().getAppUserCalorie().getId());
        assertEquals(calorieInfo.getAppUserCalorie().getFat(),calorieInfoResult.get().getAppUserCalorie().getFat());
        assertEquals(calorieInfo.getAppUserCalorie().getProtein(),
                calorieInfoResult.get().getAppUserCalorie().getProtein());
        assertEquals(calorieInfo.getAppUserCalorie().getCalories(),
                calorieInfoResult.get().getAppUserCalorie().getCalories());
        assertEquals(calorieInfo.getAppUserCalorie().getCarbohydrates(),
                calorieInfoResult.get().getAppUserCalorie().getCarbohydrates());
        //CleanUp
        calorieInfoRepository.delete(calorieInfo);
        appUserRepository.delete(appUserOne);
    }

    @Test
    public void getCalorieInfoByAppUserId() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("John","1234","ROLE_USER"));
        AppUserCalorie appUserCalorieOne = appUserCalorieMapper.mapToAppUserCalorie(
                new AppUserCalorieDto(20.0,50.0,70.0, 1000.0));
        CalorieInfo calorieInfo = calorieInfoMapper.mapToCalorieInfo(
                new CalorieInfoDto(50.0,2.0,appUserCalorieOne,appUserOne));
        appUserRepository.save(appUserOne);
        appUserCalorieRepository.save(appUserCalorieOne);
        calorieInfoRepository.save(calorieInfo);
        //When
        CalorieInfo calorieInfoResult = calorieInfoDbService.getCalorieInfoByAppUserId(appUserOne.getId());
        //Then
        assertEquals(calorieInfo.getId(),calorieInfoResult.getId());
        assertEquals(calorieInfo.getAppUserFitness(),calorieInfoResult.getAppUserFitness());
        assertEquals(calorieInfo.getAppUserWeight(),calorieInfoResult.getAppUserWeight());
        assertEquals(calorieInfo.getAppUser().getId(),calorieInfoResult.getAppUser().getId());
        assertEquals(calorieInfo.getAppUser().getUsername(),calorieInfoResult.getAppUser().getUsername());
        assertEquals(calorieInfo.getAppUser().getPassword(),calorieInfoResult.getAppUser().getPassword());
        assertEquals(calorieInfo.getAppUser().getRole(),calorieInfoResult.getAppUser().getRole());
        assertEquals(calorieInfo.getAppUserCalorie().getId(),calorieInfoResult.getAppUserCalorie().getId());
        assertEquals(calorieInfo.getAppUserCalorie().getFat(),calorieInfoResult.getAppUserCalorie().getFat());
        assertEquals(calorieInfo.getAppUserCalorie().getProtein(),calorieInfoResult.getAppUserCalorie().getProtein());
        assertEquals(calorieInfo.getAppUserCalorie().getCalories(),calorieInfoResult.getAppUserCalorie().getCalories());
        assertEquals(calorieInfo.getAppUserCalorie().getCarbohydrates(),
                calorieInfoResult.getAppUserCalorie().getCarbohydrates());
        //CleanUp
        calorieInfoRepository.delete(calorieInfo);
        appUserRepository.delete(appUserOne);
    }

    @Test(expected = UserNotFoundException.class)
    public void getCalorieInfoByAppUserIdException() {
        calorieInfoDbService.getCalorieInfoByAppUserId(10_000L);
    }

    @Test
    public void deleteByCalorieInfoId() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("John","1234","ROLE_USER"));
        AppUserCalorie appUserCalorieOne = appUserCalorieMapper.mapToAppUserCalorie(
                new AppUserCalorieDto(20.0,50.0,70.0, 1000.0));
        CalorieInfo calorieInfo = calorieInfoMapper.mapToCalorieInfo(
                new CalorieInfoDto(50.0,2.0,appUserCalorieOne,appUserOne));
        appUserRepository.save(appUserOne);
        appUserCalorieRepository.save(appUserCalorieOne);
        calorieInfoRepository.save(calorieInfo);
        //When
        Boolean result = calorieInfoDbService.deleteByCalorieInfoId(calorieInfo.getId());
        //Then
        assertFalse(result);
        //CleanUp
        appUserRepository.delete(appUserOne);
    }

    @Test
    public void checkExistByAppUserId() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("John","1234","ROLE_USER"));
        AppUserCalorie appUserCalorieOne = appUserCalorieMapper.mapToAppUserCalorie(
                new AppUserCalorieDto(20.0,50.0,70.0, 1000.0));
        CalorieInfo calorieInfo = calorieInfoMapper.mapToCalorieInfo(
                new CalorieInfoDto(50.0,2.0,appUserCalorieOne,appUserOne));
        appUserRepository.save(appUserOne);
        appUserCalorieRepository.save(appUserCalorieOne);
        calorieInfoRepository.save(calorieInfo);
        //When
        Boolean result = calorieInfoDbService.checkExistByAppUserId(appUserOne.getId());
        //Then
        assertTrue(result);
        //CleanUp
        calorieInfoRepository.delete(calorieInfo);
        appUserRepository.delete(appUserOne);
    }
}