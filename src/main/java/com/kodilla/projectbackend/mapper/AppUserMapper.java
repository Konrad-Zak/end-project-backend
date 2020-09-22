package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppUserMapper {

    public AppUser mapToAppUser(final AppUserDto appUserDto){
        return new AppUser(
                appUserDto.getId(),appUserDto.getUsername(),appUserDto.getPassword(),appUserDto.getRole());
    }

    public AppUserDto mapToAppUserDto(final  AppUser appUser){
        return new AppUserDto(appUser.getId(),appUser.getUsername(),appUser.getPassword(),appUser.getRole());
    }

    public List<AppUserDto> mapToAppUserDtoList(final List<AppUser> appUserList){
        return appUserList.stream()
                .map(appUser -> new AppUserDto(
                        appUser.getId(),appUser.getUsername(),appUser.getPassword(),appUser.getRole()))
                .collect(Collectors.toList());
    }
}
