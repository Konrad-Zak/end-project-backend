package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.SearchFoodDto;
import com.kodilla.projectbackend.observer.EdamamCientProblem;
import com.kodilla.projectbackend.service.EdamamService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
@AllArgsConstructor
public class EdamamFacade {

    private EdamamService edamamService;
    private EdamamCientProblem edamamCientProblem;

    public SearchFoodDto getSearchFood(String foodName) {
        SearchFoodDto searchFoodDto = edamamService.getSearchFood(foodName);
        if(searchFoodDto.getParsed()!=null){
            return searchFoodDto;
        } else {
            reportProblem();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    private void reportProblem() {
        edamamCientProblem.addMessage("External edamam system is break down");
        edamamCientProblem.notifyObservers();
    }

}
