package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

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

    @Test
    public void mapToAppUserInfoDtoList() {
        //Given
        AppUser appUserOne = new AppUser(1L,"Joker","1234","ROLE_USER");
        AppUser appUserTwo = new AppUser(2L,"John","123","ROLE_USER");
        AppUserInfo appUserInfoOne = new AppUserInfo(10L,"John","j@wp.pl",appUserOne);
        AppUserInfo appUserInfoTwo = new AppUserInfo(10L,"John","j@wp.pl",appUserTwo);
        List<AppUserInfo> appUserInfoList = Arrays.asList(appUserInfoOne,appUserInfoTwo);
        //When
        List<AppUserInfoDto> appUserInfoDtoList = appUserInfoMapper.mapToAppUserInfoDtoList(appUserInfoList);
        //Then
        assertEquals(appUserInfoList.size(),appUserInfoDtoList.size());
        assertEquals(appUserInfoList.get(0).getId(),appUserInfoDtoList.get(0).getId());
        assertEquals(appUserInfoList.get(0).getFirstName(),appUserInfoDtoList.get(0).getFirstName());
        assertEquals(appUserInfoList.get(0).getEmail(),appUserInfoDtoList.get(0).getEmail());
        assertEquals(appUserInfoList.get(0).getAppUser().getId(),appUserInfoDtoList.get(0).getAppUser().getId());
        assertEquals(appUserInfoList.get(0).getAppUser().getUsername(),
                appUserInfoDtoList.get(0).getAppUser().getUsername());
        assertEquals(appUserInfoList.get(0).getAppUser().getPassword(),
                appUserInfoDtoList.get(0).getAppUser().getPassword());
        assertEquals(appUserInfoList.get(0).getAppUser().getRole(),appUserInfoDtoList.get(0).getAppUser().getRole());
        assertEquals(appUserInfoList.get(1).getId(),appUserInfoDtoList.get(1).getId());
        assertEquals(appUserInfoList.get(1).getFirstName(),appUserInfoDtoList.get(1).getFirstName());
        assertEquals(appUserInfoList.get(1).getEmail(),appUserInfoDtoList.get(1).getEmail());
        assertEquals(appUserInfoList.get(1).getAppUser().getId(),appUserInfoDtoList.get(1).getAppUser().getId());
        assertEquals(appUserInfoList.get(1).getAppUser().getUsername(),
                appUserInfoDtoList.get(1).getAppUser().getUsername());
        assertEquals(appUserInfoList.get(1).getAppUser().getPassword(),
                appUserInfoDtoList.get(1).getAppUser().getPassword());
        assertEquals(appUserInfoList.get(1).getAppUser().getRole(),appUserInfoDtoList.get(1).getAppUser().getRole());
    }

}