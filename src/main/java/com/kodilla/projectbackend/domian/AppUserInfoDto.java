package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AppUserInfoDto {

    private Long id;
    private String firstName;
    private String email;
    private AppUser appUser;

}
