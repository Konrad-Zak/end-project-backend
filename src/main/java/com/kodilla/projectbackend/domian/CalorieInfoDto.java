package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CalorieInfoDto {

    private Long id;
    private Double appUserWeight;
    private Double appUserFitness;
    private AppUserCalorie appUserCalorie;
    private AppUser appUser;

    public CalorieInfoDto(Double appUserWeight, Double appUserFitness, AppUserCalorie appUserCalorie, AppUser appUser) {
        this.appUserWeight = appUserWeight;
        this.appUserFitness = appUserFitness;
        this.appUserCalorie = appUserCalorie;
        this.appUser = appUser;
    }

    public CalorieInfoDto(Long id, Double appUserWeight, Double appUserFitness, AppUserCalorie appUserCalorie) {
        this.id = id;
        this.appUserWeight = appUserWeight;
        this.appUserFitness = appUserFitness;
        this.appUserCalorie = appUserCalorie;
    }

    public void setNewValues(Double appUserWeight, Double appUserFitness){
        this.appUserWeight = appUserWeight;
        this.appUserFitness =appUserFitness;
    }


}
