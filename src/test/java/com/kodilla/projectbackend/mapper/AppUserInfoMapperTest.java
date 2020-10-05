package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppUserInfoMapperTest {

    private AppUserInfoMapper appUserInfoMapper = new AppUserInfoMapper();

    @Test
    public void mapToAppUserInfo() {
        //Given
        AppUser appUser = new AppUser(2L,"Joker","1234","ROLE_USER");
        AppUserInfoDto appUserInfoDto = new AppUserInfoDto(10L,"John","j@wp.pl",appUser);
        //When
        AppUserInfo appUserInfo = appUserInfoMapper.mapToAppUserInfo(appUserInfoDto);
        //Then
        assertEquals(appUserInfoDto.getId(),appUserInfo.getId());
        assertEquals(appUserInfoDto.getFirstName(),appUserInfo.getFirstName());
        assertEquals(appUserInfoDto.getEmail(),appUserInfo.getEmail());
        assertEquals(appUserInfoDto.getAppUser().getId(),appUserInfoDto.getAppUser().getId());
        assertEquals(appUserInfoDto.getAppUser().getUsername(),appUserInfoDto.getAppUser().getUsername());
        assertEquals(appUserInfoDto.getAppUser().getPassword(),appUserInfoDto.getAppUser().getPassword());
        assertEquals(appUserInfoDto.getAppUser().getRole(),appUserInfoDto.getAppUser().getRole());
    }

    @Test
    public void mapToAppUserInfoDto() {
        //Given
        AppUser appUser = new AppUser(2L,"Joker","1234","ROLE_USER");
        AppUserInfo appUserInfo = new AppUserInfo(10L,"John","j@wp.pl",appUser);
        //When
        AppUserInfoDto appUserInfoDto = appUserInfoMapper.mapToAppUserInfoDto(appUserInfo);
        //Then
        assertEquals(appUserInfo.getId(),appUserInfoDto.getId());
        assertEquals(appUserInfo.getFirstName(),appUserInfoDto.getFirstName());
        assertEquals(appUserInfo.getEmail(),appUserInfoDto.getEmail());
    }
}