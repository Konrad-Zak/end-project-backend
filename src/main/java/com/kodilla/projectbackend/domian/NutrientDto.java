package com.kodilla.projectbackend.domian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NutrientDto {

    @JsonProperty("ENERC_KCAL")
    private Double calories;

    @JsonProperty("PROCNT")
    private Double protein;

    @JsonProperty("FAT")
    private Double fat;

    @JsonProperty("CHOCDF")
    private Double carbohydrates;
}
