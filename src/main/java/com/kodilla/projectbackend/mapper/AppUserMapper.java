package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;


import java.util.List;
import java.util.stream.Collectors;

public final class AppUserMapper {

    private static AppUserMapper appUserMapper = null;

    public AppUserMapper() {
    }

    public static AppUserMapper getInstance() {
        if (appUserMapper == null) {
            appUserMapper = new AppUserMapper();
        }
        return appUserMapper;
    }

    public AppUser mapToAppUser(final AppUserDto appUserDto){
        return new AppUser(appUserDto.getId(),appUserDto.getUsername(),appUserDto.getPassword(),appUserDto.getRole());
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
