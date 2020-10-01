package com.kodilla.projectbackend.controller;


import com.kodilla.projectbackend.domian.*;
import com.kodilla.projectbackend.facade.CalorieInfoFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/calorieInfo")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CalorieInfoController {

    private CalorieInfoFacade calorieInfoFacade;

    @PostMapping()
    public Boolean createAppCalorieCalculator(
            @RequestParam Long appUserId, @RequestParam Double weight, @RequestParam Double fitness) {
        return calorieInfoFacade.createAppCalorieInfo(appUserId,weight,fitness);
    }

    @GetMapping()
    public CalorieInfoDto getAppCalorieCalculator(@RequestParam Long appUserId) {
        return calorieInfoFacade.getAppCalorieInfo(appUserId);
    }

    @PutMapping()
    public void updateAppCalorieCalculator(
            @RequestParam Long appUserId, @RequestParam Double weight, @RequestParam Double fitness) {
        calorieInfoFacade.updateAppCalorieInfo(appUserId,weight,fitness);
    }

    @GetMapping(value = "/check")
    public Boolean checkExistByAppUserId(@RequestParam Long appUserId) {
        return calorieInfoFacade.checkExistByAppUserId(appUserId);
    }

}
