package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.mapper.AppUserInfoMapper;
import com.kodilla.projectbackend.service.AppUserInfoDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppUserInfoFacadeTest {

    @InjectMocks
    private AppUserInfoFacade appUserInfoFacade;

    @Mock
    private AppUserInfoDbService appUserInfoDbService;

    @Mock
    private AppUserInfoMapper appUserInfoMapper;

    @Test
    public void getAppUserInfoByAppUserId() {
        //Given
        Long appUserId = 1L;
        AppUser appUser = new AppUser(1L,"John","1234", "ROLE_USER");
        AppUserInfo appUserInfo = new AppUserInfo(1L,"Tom","t@wp.pl",appUser);
        AppUserInfoDto appUserInfoDto = new AppUserInfoDto(1L,"Tom","t@wp.pl",appUser);

        when(appUserInfoDbService.getAppUserInfoByAppUserId(appUserId)).thenReturn(appUserInfo);
        when(appUserInfoMapper.mapToAppUserInfoDto(appUserInfo)).thenReturn(appUserInfoDto);

        //When
        AppUserInfoDto appUserInfoDtoResult = appUserInfoFacade.getAppUserInfoByAppUserId(appUserId);

        //Then
        assertEquals(appUserInfoDto.getId(),appUserInfoDtoResult.getId());
        assertEquals(appUserInfoDto.getFirstName(),appUserInfoDtoResult.getFirstName());
        assertEquals(appUserInfoDto.getEmail(),appUserInfoDtoResult.getEmail());
        assertEquals(appUserInfoDto.getAppUser().getId(),appUserInfoDtoResult.getAppUser().getId());
        assertEquals(appUserInfoDto.getAppUser().getUsername(),appUserInfoDtoResult.getAppUser().getUsername());
        assertEquals(appUserInfoDto.getAppUser().getPassword(),appUserInfoDtoResult.getAppUser().getPassword());
        assertEquals(appUserInfoDto.getAppUser().getRole(),appUserInfoDtoResult.getAppUser().getRole());

    }

    @Test
    public void getAppUsersInfo() {
        //Given
        AppUser appUser = new AppUser(1L,"John","1234", "ROLE_USER");
        AppUserInfo appUserInfo = new AppUserInfo(1L,"Tom","t@wp.pl",appUser);
        AppUserInfoDto appUserInfoDto = new AppUserInfoDto(1L,"Tom","t@wp.pl",appUser);
        List<AppUserInfo> appUserInfoList = Collections.singletonList(appUserInfo);
        List<AppUserInfoDto> appUserInfoDtoList = Collections.singletonList(appUserInfoDto);

        when(appUserInfoDbService.getAllAppUserInfo()).thenReturn(appUserInfoList);
        when(appUserInfoMapper.mapToAppUserInfoDtoList(appUserInfoList)).thenReturn(appUserInfoDtoList);

        //When
        List<AppUserInfoDto> appUserInfoDtoResult = appUserInfoFacade.getAppUsersInfo();

        //Then
        assertEquals(appUserInfoDtoList.size(),appUserInfoDtoResult.size());
        assertEquals(appUserInfoDtoList.get(0).getId(),appUserInfoDtoResult.get(0).getId());
        assertEquals(appUserInfoDtoList.get(0).getFirstName(),appUserInfoDtoResult.get(0).getFirstName());
        assertEquals(appUserInfoDtoList.get(0).getEmail(),appUserInfoDtoResult.get(0).getEmail());
        assertEquals(appUserInfoDtoList.get(0).getAppUser().getId(),appUserInfoDtoResult.get(0).getAppUser().getId());
        assertEquals(appUserInfoDtoList.get(0).getAppUser().getUsername(),
                appUserInfoDtoResult.get(0).getAppUser().getUsername());
        assertEquals(appUserInfoDtoList.get(0).getAppUser().getPassword(),
                appUserInfoDtoResult.get(0).getAppUser().getPassword());
        assertEquals(appUserInfoDtoList.get(0).getAppUser().getRole(),
                appUserInfoDtoResult.get(0).getAppUser().getRole());
    }

    @Test
    public void createAppUserInfo() {
        //Given
        AppUser appUser = new AppUser(1L,"John","1234", "ROLE_USER");
        AppUserInfo appUserInfo = new AppUserInfo(1L,"Tom","t@wp.pl",appUser);
        AppUserInfoDto appUserInfoDto = new AppUserInfoDto(1L,"Tom","t@wp.pl",appUser);

        when(appUserInfoMapper.mapToAppUserInfo(appUserInfoDto)).thenReturn(appUserInfo);
        when(appUserInfoDbService.checkAppUserInfoExist(appUserInfo.getId())).thenReturn(true);

        //When
        Boolean result = appUserInfoFacade.createAppUserInfo(appUserInfoDto);

        //Then
        assertTrue(result);
    }

}