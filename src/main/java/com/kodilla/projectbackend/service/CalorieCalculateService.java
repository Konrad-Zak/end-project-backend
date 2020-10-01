package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserCalorieDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalorieCalculateService {

    private static final int FITNESS_CONSTANT = 30;
    private static final int PROTEIN_PER_KG = 2;
    private static final int FAT_PER_KG = 1;

    public AppUserCalorieDto calculateCalorie(Double weight, Double fitness) {
        double calorie = weight * (fitness + FITNESS_CONSTANT);
        double protein = weight * PROTEIN_PER_KG;
        double fat = weight * FAT_PER_KG;
        double carbohydrates = (calorie - (protein*4) - (fat*9))/4;
        return new AppUserCalorieDto(protein,fat,carbohydrates,calorie);
    }

}
