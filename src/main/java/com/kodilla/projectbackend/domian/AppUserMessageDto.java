package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserMessageDto {

    private Long id;
    private String email;
    private String message;
    private LocalDate localDate;

    public AppUserMessageDto(String email, String message, LocalDate localDate) {
        this.email = email;
        this.message = message;
        this.localDate = localDate;
    }
}
