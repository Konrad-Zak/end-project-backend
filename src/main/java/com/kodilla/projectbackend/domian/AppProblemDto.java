package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class AppProblemDto {
    private Long id;
    private String text;
    private Timestamp timestamp;

    public AppProblemDto(String text, Timestamp timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }
}
