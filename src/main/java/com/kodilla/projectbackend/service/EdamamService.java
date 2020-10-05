package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.client.EdamamClient;
import com.kodilla.projectbackend.domian.SearchFoodDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EdamamService {

    private EdamamClient edamamClient;

    public SearchFoodDto getSearchFood(String foodName) {
        return edamamClient.getSearchFood(foodName);
    }
}
