package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppUserMapperTest {

    private AppUserMapper appUserMapper = new AppUserMapper(new PasswordEncoder() {
        @Override
        public String encode(CharSequence rawPassword) {
            return String.valueOf(rawPassword.hashCode());
        }

        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            return false;
        }
    });

    @Test
    public void mapToAppUser() {
        //Given
        AppUserDto appUserDto = new AppUserDto(2L,"John","1234","ROLE_USER");
        //When
        AppUser appUser = appUserMapper.mapToAppUser(appUserDto);
        //Then
        assertEquals(appUserDto.getId(),appUser.getId());
        assertEquals(appUserDto.getUsername(),appUser.getUsername());
        assertNotEquals(appUserDto.getPassword(),appUser.getPassword());
        assertEquals(appUserDto.getRole(),appUser.getRole());
    }

    @Test
    public void mapToAppUserDto() {
        //Given
        AppUser appUser= new AppUser(2L,"John","1234","ROLE_USER");
        //When
        AppUserDto appUserDto = appUserMapper.mapToAppUserDto(appUser);
        //Then
        assertEquals(appUser.getId(),appUserDto.getId());
        assertEquals(appUser.getUsername(),appUserDto.getUsername());
        assertEquals(appUser.getPassword(),appUserDto.getPassword());
        assertEquals(appUser.getRole(),appUserDto.getRole());
    }

    @Test
    public void mapToAppUserDtoList() {
        //Given
        AppUser appUserOne= new AppUser(2L,"John","1234","ROLE_USER");
        AppUser appUserTwo= new AppUser(3L,"John 1","12345","ROLE_USER");
        List<AppUser> appUserList = Arrays.asList(appUserOne,appUserTwo);
        //When
        List<AppUserDto> appUserDtoList = appUserMapper.mapToAppUserDtoList(appUserList);
        //Then
        assertEquals(appUserList.size(),appUserDtoList.size());
        assertEquals(appUserList.get(0).getId(),appUserDtoList.get(0).getId());
        assertEquals(appUserList.get(0).getRole(),appUserDtoList.get(0).getRole());
        assertEquals(appUserList.get(0).getPassword(),appUserDtoList.get(0).getPassword());
        assertEquals(appUserList.get(0).getUsername(),appUserDtoList.get(0).getUsername());
        assertEquals(appUserList.get(1).getId(),appUserDtoList.get(1).getId());
        assertEquals(appUserList.get(1).getRole(),appUserDtoList.get(1).getRole());
        assertEquals(appUserList.get(1).getPassword(),appUserDtoList.get(1).getPassword());
        assertEquals(appUserList.get(1).getUsername(),appUserDtoList.get(1).getUsername());
    }
}