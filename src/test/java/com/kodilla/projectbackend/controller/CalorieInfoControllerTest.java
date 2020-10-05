package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserCalorie;
import com.kodilla.projectbackend.domian.CalorieInfoDto;
import com.kodilla.projectbackend.facade.CalorieInfoFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CalorieInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalorieInfoFacade calorieInfoFacade;

    @Test
    public void createAppCalorieCalculator() throws Exception {
        //Given
        Long appUserId = 1L;
        Double weight = 70.0;
        Double fitness = 2.0;

        when(calorieInfoFacade.createAppCalorieInfo(appUserId,weight,fitness)).thenReturn(true);

        //When & Then
        mockMvc.perform(post("/v1/calorieInfo?appUserId=" + appUserId +
                "&weight=" + weight + "&fitness=" + fitness)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void getAppCalorieCalculator() throws Exception {
        //Given
        AppUser appUser = new AppUser(1L,"John","1234","ROLE_USER");
        AppUserCalorie appUserCalorie = new AppUserCalorie(1L,40.0,70.0,
                140.0,1000.0);
        CalorieInfoDto calorieInfoDto = new CalorieInfoDto(1L,70.0,2.0,
                appUserCalorie,appUser);

        when(calorieInfoFacade.getAppCalorieInfo(appUser.getId())).thenReturn(calorieInfoDto);

        //When & Then
        mockMvc.perform(get("/v1/calorieInfo?appUserId=" + appUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.appUserWeight",is(70.0)))
                .andExpect(jsonPath("$.appUserFitness",is(2.0)))
                .andExpect(jsonPath("$.appUserCalorie.id",is(1)))
                .andExpect(jsonPath("$.appUserCalorie.protein",is(40.0)))
                .andExpect(jsonPath("$.appUserCalorie.fat",is(70.0)))
                .andExpect(jsonPath("$.appUserCalorie.carbohydrates",is(140.0)))
                .andExpect(jsonPath("$.appUserCalorie.calories",is(1000.0)))
                .andExpect(jsonPath("$.appUser.id",is(1)))
                .andExpect(jsonPath("$.appUser.username",is("John")))
                .andExpect(jsonPath("$.appUser.password",is("1234")))
                .andExpect(jsonPath("$.appUser.role",is("ROLE_USER")));

    }

    @Test
    public void updateAppCalorieCalculator() throws Exception {
        //When & Then
        mockMvc.perform(put("/v1/calorieInfo?appUserId=1&weight=70.0&fitness=2.0")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void checkExistByAppUserId() throws Exception {
        //Given
        Long appUserId = 1L;

        when(calorieInfoFacade.checkExistByAppUserId(appUserId)).thenReturn(true);

        mockMvc.perform(get("/v1/calorieInfo/check?appUserId=" + appUserId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }
}