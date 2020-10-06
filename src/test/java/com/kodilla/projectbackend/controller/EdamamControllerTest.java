package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.FoodDto;
import com.kodilla.projectbackend.domian.NutrientDto;
import com.kodilla.projectbackend.domian.ParsedDto;
import com.kodilla.projectbackend.domian.SearchFoodDto;
import com.kodilla.projectbackend.facade.EdamamFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EdamamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EdamamFacade edamamFacade;

    @Test
    public void getSearchFood() throws Exception {
        //Given
        String searchFood = "test";
        NutrientDto nutrientDto = new NutrientDto(2000.0,20.0,50.0,250.0);
        FoodDto foodDto = new FoodDto(nutrientDto);
        ParsedDto parsedDto = new ParsedDto(foodDto);
        List<ParsedDto> parsedDtoList = Collections.singletonList(parsedDto);
        SearchFoodDto searchFoodDto = new SearchFoodDto(searchFood, parsedDtoList);

        when(edamamFacade.getSearchFood(searchFood)).thenReturn(searchFoodDto);

        //When & Then
        mockMvc.perform(get("/v1/edamam?foodName="+searchFood)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text",is(searchFood)))
                .andExpect(jsonPath("$.parsed[0].food.nutrients.ENERC_KCAL",is(2000.0)))
                .andExpect(jsonPath("$.parsed[0].food.nutrients.PROCNT",is(20.0)))
                .andExpect(jsonPath("$.parsed[0].food.nutrients.FAT",is(50.0)))
                .andExpect(jsonPath("$.parsed[0].food.nutrients.CHOCDF",is(250.0)));

    }
}