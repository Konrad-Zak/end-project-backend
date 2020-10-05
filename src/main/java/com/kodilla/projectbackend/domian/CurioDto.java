package com.kodilla.projectbackend.domian;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurioDto {

    private Long id;
    private String text;
    private Integer year;
    private LocalDate localDate;

    public CurioDto(String text, Integer year) {
        this.text = text;
        this.year = year;
    }


}
