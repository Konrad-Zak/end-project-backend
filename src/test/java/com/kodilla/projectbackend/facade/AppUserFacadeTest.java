package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
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
public class AppUserFacadeTest {

    @InjectMocks
    private AppUserFacade appUserFacade;

    @Mock
    private AppUserDbService appUserDbService;

    @Mock
    private AppUserMapper appUserMapper;

    @Test
    public void getAppUsers() {
        //Given
        List<AppUser> appUserList = Collections.singletonList(
                new AppUser(1L, "John", "1234", "ROLE_USER"));
        List<AppUserDto> appUserListDto = Collections.singletonList(
                new AppUserDto(1L, "John", "1234", "ROLE_USER"));

        when(appUserDbService.getAllAppUsers()).thenReturn(appUserList);
        when(appUserMapper.mapToAppUserDtoList(appUserList)).thenReturn(appUserListDto);

        //When
        List<AppUserDto> appUserDtoList = appUserFacade.getAppUsers();

        //Then
        assertEquals(appUserListDto.size(),appUserDtoList.size());
        assertEquals(appUserListDto.get(0).getId(),appUserDtoList.get(0).getId());
        assertEquals(appUserListDto.get(0).getUsername(),appUserDtoList.get(0).getUsername());
        assertEquals(appUserListDto.get(0).getPassword(),appUserDtoList.get(0).getPassword());
        assertEquals(appUserListDto.get(0).getRole(),appUserDtoList.get(0).getRole());
    }

    @Test
    public void createAppUser() {
        //Given
        String username = "John";
        String password = "1234";

        when(appUserDbService.checkExistsByUsername(username)).thenReturn(true);

        //When
        Boolean result = appUserFacade.createAppUser(username,password);

        //Then
        assertTrue(result);
    }

    @Test
    public void getAppUserByUsername() {
        //Given
        String username = "John";
        AppUserDto appUserDto = new AppUserDto(1L,"John","1234","ROLE_USER");
        AppUser appUser = new AppUser(1L,"John","1234","ROLE_USER");

        when(appUserDbService.loadUserByUsername(username)).thenReturn(appUser);
        when(appUserMapper.mapToAppUserDto(appUser)).thenReturn(appUserDto);

        //When
        AppUserDto appUserDtoResult = appUserFacade.getAppUserByUsername(username);

        //Then
        assertEquals(appUserDto.getId(),appUserDtoResult.getId());
        assertEquals(appUserDto.getUsername(),appUserDtoResult.getUsername());
        assertEquals(appUserDto.getPassword(),appUserDtoResult.getPassword());
        assertEquals(appUserDto.getRole(),appUserDtoResult.getRole());
    }

    @Test
    public void checkExistByUsername() {
        //Given
        String username = "John";
        when(appUserDbService.checkExistsByUsername(username)).thenReturn(true);

        //When
        Boolean result = appUserFacade.checkExistByUsername(username);

        //Then
        assertTrue(result);
    }
}