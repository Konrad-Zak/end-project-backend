package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.*;
import com.kodilla.projectbackend.mapper.AppUserCalorieMapper;
import com.kodilla.projectbackend.mapper.CalorieInfoMapper;
import com.kodilla.projectbackend.service.AppUserCalorieDbService;
import com.kodilla.projectbackend.service.AppUserDbService;
import com.kodilla.projectbackend.service.CalorieCalculateService;
import com.kodilla.projectbackend.service.CalorieInfoDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalorieInfoFacadeTest {

    @InjectMocks
    CalorieInfoFacade calorieInfoFacade;

    @Mock
    private CalorieCalculateService calorieCalculateService;

    @Mock
    private AppUserDbService appUserDbService;

    @Mock
    private CalorieInfoDbService calorieInfoDbService;

    @Mock
    private AppUserCalorieDbService appUserCalorieDbService;

    @Mock
    private AppUserCalorieMapper appUserCalorieMapper;

    @Mock
    private CalorieInfoMapper calorieInfoMapper;

    @Test(expected = RuntimeException.class)
    public void createAppCalorieInfoException() {
        //Given
        long appUserId = 1L;
        double weight = 70.0;
        double fitness = 2.0;

        when(calorieInfoDbService.checkExistByAppUserId(appUserId)).thenReturn(true);
        //When
        calorieInfoFacade.createAppCalorieInfo(appUserId,weight,fitness);
    }

    @Test()
    public void createAppCalorieInfo() {
        //Given
        Long appUserId = 1L;
        Double weight = 70.0;
        Double fitness = 2.0;
        AppUserCalorieDto appUserCalorieDto = new AppUserCalorieDto(
                1L,40.0,70.0,120.0,1000.0);
        AppUserCalorie appUserCalorie = new AppUserCalorie(
                1L,40.0,70.0,120.0,1000.0);
        AppUser appUser = new AppUser(1L,"John","1234","ROLE_USER");

        when(calorieCalculateService.calculateCalorie(weight,fitness)).thenReturn(appUserCalorieDto);
        when(appUserCalorieMapper.mapToAppUserCalorie(appUserCalorieDto)).thenReturn(appUserCalorie);
        when(appUserDbService.getAppUserById(appUser.getId())).thenReturn(appUser);
        appUserCalorieDbService.createAppUserCalorie(appUserCalorie);
        when(calorieInfoDbService.checkExistByAppUserId(appUserId)).thenReturn(false);

        //When
        Boolean result = calorieInfoFacade.createAppCalorieInfo(appUserId,weight,fitness);
        //Then
        assertFalse(result);
    }

    @Test
    public void getAppCalorieInfo() {
        //Given
        Long appUserId = 1L;
        AppUser appUser = new AppUser(1L,"John","1234","ROLE_USER");
        AppUserCalorie appUserCalorie = new AppUserCalorie(
                1L,40.0,70.0,120.0,1000.0);
        CalorieInfo calorieInfo = new CalorieInfo(1L, 70.0, 2.0,
                appUserCalorie, appUser);
        CalorieInfoDto calorieInfoDto = new CalorieInfoDto(1L, 70.0, 2.0,
                appUserCalorie);

        when(calorieInfoDbService.getCalorieInfoByAppUserId(appUserId)).thenReturn(calorieInfo);
        when(calorieInfoMapper.simpleMapToCalorieInfoDto(calorieInfo)).thenReturn(calorieInfoDto);

        //When
        CalorieInfoDto calorieInfoDtoResult = calorieInfoFacade.getAppCalorieInfo(appUserId);

        //Then
        assertEquals(calorieInfoDto.getId(),calorieInfoDtoResult.getId());
        assertEquals(calorieInfoDto.getAppUserWeight(),calorieInfoDtoResult.getAppUserWeight());
        assertEquals(calorieInfoDto.getAppUserFitness(),calorieInfoDtoResult.getAppUserFitness());
        assertEquals(calorieInfoDto.getAppUserCalorie().getId(),calorieInfoDtoResult.getAppUserCalorie().getId());
        assertEquals(calorieInfoDto.getAppUserCalorie().getCarbohydrates(),
                calorieInfoDtoResult.getAppUserCalorie().getCarbohydrates());
        assertEquals(calorieInfoDto.getAppUserCalorie().getCalories(),
                calorieInfoDtoResult.getAppUserCalorie().getCalories());
        assertEquals(calorieInfoDto.getAppUserCalorie().getProtein(),
                calorieInfoDtoResult.getAppUserCalorie().getProtein());
        assertEquals(calorieInfoDto.getAppUserCalorie().getFat(),calorieInfoDtoResult.getAppUserCalorie().getFat());
    }

    @Test
    public void checkExistByAppUserId() {
        //Given
        Long appUserId = 1L;
        when(calorieInfoDbService.checkExistByAppUserId(appUserId)).thenReturn(true);

        //When
        Boolean result = calorieInfoFacade.checkExistByAppUserId(appUserId);

        //Then
        assertTrue(result);
    }
}