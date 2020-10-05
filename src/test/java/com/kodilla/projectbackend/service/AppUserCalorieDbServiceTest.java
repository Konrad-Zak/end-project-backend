package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserCalorie;
import com.kodilla.projectbackend.domian.AppUserCalorieDto;
import com.kodilla.projectbackend.mapper.AppUserCalorieMapper;
import com.kodilla.projectbackend.repository.AppUserCalorieRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppUserCalorieDbServiceTest {

    @Autowired
    private AppUserCalorieRepository appUserCalorieRepository;

    @Autowired
    private AppUserCalorieMapper appUserCalorieMapper;

    @Autowired
    private AppUserCalorieDbService appUserCalorieDbService;

    @Test
    public void createAppUserCalorie() {
        //Given
        AppUserCalorieDto appUserCalorieDto = new AppUserCalorieDto(
                70.0,80.0,200.0,2000.0);
        AppUserCalorie appUserCalorie = appUserCalorieMapper.mapToAppUserCalorie(appUserCalorieDto);
        //When
        appUserCalorieDbService.createAppUserCalorie(appUserCalorie);
        Optional<AppUserCalorie> appUserCalorieResult = appUserCalorieRepository.findById(appUserCalorie.getId());
        //Then
        assertTrue(appUserCalorieResult.isPresent());
        assertEquals(appUserCalorie.getId(),appUserCalorieResult.get().getId());
        assertEquals(appUserCalorie.getProtein(),appUserCalorieResult.get().getProtein());
        assertEquals(appUserCalorie.getCalories(),appUserCalorieResult.get().getCalories());
        assertEquals(appUserCalorie.getFat(),appUserCalorieResult.get().getFat());
        assertEquals(appUserCalorie.getCarbohydrates(),appUserCalorieResult.get().getCarbohydrates());
        //CleanUp
        appUserCalorieRepository.deleteAll();
    }
}