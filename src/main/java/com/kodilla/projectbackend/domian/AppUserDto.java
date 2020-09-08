package com.kodilla.projectbackend.domian;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@AllArgsConstructor
public class AppUserDto {
    private final static String ROLE = "USER";
    private Long id;
    private String username;
    private String password;
    private String role;

    public AppUserDto(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public AppUserDto(String username, String password) {
        this.username = username;
        this.password = password;
        this.role = ROLE;
    }
}
