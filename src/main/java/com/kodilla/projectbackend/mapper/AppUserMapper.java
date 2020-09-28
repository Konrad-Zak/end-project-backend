package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class AppUserMapper {

    private final static String ROLE = "ROLE_USER";
    private PasswordEncoder passwordEncoder;

    public AppUser mapToAppUser(final AppUserDto appUserDto) {
        return new AppUser(appUserDto.getId(),appUserDto.getUsername(),
                passwordEncoder.encode(appUserDto.getPassword()),selectRole(appUserDto));
    }

    public AppUserDto mapToAppUserDto(final  AppUser appUser) {
        return new AppUserDto(appUser.getId(),appUser.getUsername(),appUser.getPassword(),appUser.getRole());
    }

    public List<AppUserDto> mapToAppUserDtoList(final List<AppUser> appUserList) {
        return appUserList.stream()
                .map(appUser -> new AppUserDto(
                        appUser.getId(),appUser.getUsername(),appUser.getPassword(),appUser.getRole()))
                .collect(Collectors.toList());
    }

    private String selectRole(AppUserDto appUserDto) {
        return appUserDto.getRole()==null ? ROLE : appUserDto.getRole();
    }
}
