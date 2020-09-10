package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.repository.AppUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppUserDbServiceTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserDbService appUserDbService;

    @Test
    public void loadUserByUsername() {
        //Given
        AppUserDto appUserDto = new AppUserDto("John","1234","ROLE_USER");
        appUserRepository.save(AppUserMapper.getInstance().mapToAppUser(appUserDto));
        //When
        AppUser appUser = appUserDbService.loadUserByUsername("John");
        //Then
        assertEquals(appUserDto.getUsername(),appUser.getUsername());
        //CleanUp
        appUserRepository.deleteAll();
    }

    @Test
    public void getAllAppUsers() {
    }

    @Test
    public void saveAppUser() {
    }

    @Test
    public void checkExistsByUsername() {
    }

    @Test
    public void deleteAppUser() {
    }
}