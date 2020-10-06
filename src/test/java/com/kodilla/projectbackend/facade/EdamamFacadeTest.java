package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.FoodDto;
import com.kodilla.projectbackend.domian.NutrientDto;
import com.kodilla.projectbackend.domian.ParsedDto;
import com.kodilla.projectbackend.domian.SearchFoodDto;
import com.kodilla.projectbackend.service.EdamamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EdamamFacadeTest {

    @InjectMocks
    private EdamamFacade edamamFacade;

    @Mock
    private EdamamService edamamService;

    @Test
    public void getSearchFood() {
        //Given
        String searchFood = "test";
        NutrientDto nutrientDto = new NutrientDto(2000.0,20.0,50.0,250.0);
        FoodDto foodDto = new FoodDto(nutrientDto);
        ParsedDto parsedDto = new ParsedDto(foodDto);
        List<ParsedDto> parsedDtoList = Collections.singletonList(parsedDto);
        SearchFoodDto searchFoodDto = new SearchFoodDto(searchFood, parsedDtoList);

        when(edamamService.getSearchFood(searchFood)).thenReturn(searchFoodDto);

        //When
        SearchFoodDto searchFoodDtoResult = edamamFacade.getSearchFood(searchFood);

        //Then
        assertEquals(searchFoodDto.getText(),searchFoodDtoResult.getText());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getCalories(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getCalories());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getCarbohydrates(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getCarbohydrates());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getFat(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getFat());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getProtein(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getProtein());

    }
    @Test(expected = ResponseStatusException.class)
    public void getSearchFoodException() {
        //Given
        String searchFood = "test";

        when(edamamService.getSearchFood(searchFood)).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        //When
        edamamFacade.getSearchFood(searchFood);
    }

}