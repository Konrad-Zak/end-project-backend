package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.SearchFoodDto;
import com.kodilla.projectbackend.facade.EdamamFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/edamam")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class EdamamController {

    private EdamamFacade edamamFacade;

    @GetMapping()
    public SearchFoodDto getSearchFood(@RequestParam String foodName) {
        return edamamFacade.getSearchFood(foodName);
    }
}
