package com.kodilla.projectbackend.client;

import com.kodilla.projectbackend.configuration.ExternalApiConfiguration;
import com.kodilla.projectbackend.domian.FoodDto;
import com.kodilla.projectbackend.domian.NutrientDto;
import com.kodilla.projectbackend.domian.ParsedDto;
import com.kodilla.projectbackend.domian.SearchFoodDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EdamamClientTest {

    @InjectMocks
    private EdamamClient edamamClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExternalApiConfiguration externalApiConfiguration;

    @Before
    public void init(){
        when(externalApiConfiguration.getEdamamApiEndpoint()).thenReturn("http://test.com");
        when(externalApiConfiguration.getEdamamAppId()).thenReturn("test");
        when(externalApiConfiguration.getEdamamAppKey()).thenReturn("test1");
    }

    @Test
    public void getSearchFood() throws URISyntaxException {
        //Given
        String searchFood = "apple";
        NutrientDto nutrientDto = new NutrientDto(2000.0,20.0,50.0,250.0);
        FoodDto foodDto = new FoodDto(nutrientDto);
        ParsedDto parsedDto = new ParsedDto(foodDto);
        List<ParsedDto> parsedDtoList = Collections.singletonList(parsedDto);
        SearchFoodDto searchFoodDto = new SearchFoodDto(searchFood, parsedDtoList);
        URI uri = new URI( "http://test.com?app_id=test&app_key=test1&ingr="+searchFood);
        when(restTemplate.getForObject(uri,SearchFoodDto.class)).thenReturn(searchFoodDto);
        //When
        SearchFoodDto searchFoodDtoResult = edamamClient.getSearchFood(searchFood);
        //Then
        assertEquals(searchFoodDto.getText(),searchFoodDtoResult.getText());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getProtein(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getProtein());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getFat(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getFat());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getCarbohydrates(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getCarbohydrates());
        assertEquals(searchFoodDto.getParsed().get(0).getFood().getNutrients().getCalories(),
                searchFoodDtoResult.getParsed().get(0).getFood().getNutrients().getCalories());
    }

}