package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserCalorieDto {

    private Long id;
    private Double protein;
    private Double fat;
    private Double carbohydrates;
    private Double calories;

    public AppUserCalorieDto(Double protein, Double fat, Double carbohydrates, Double calories) {
        this.protein = protein;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.calories = calories;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
