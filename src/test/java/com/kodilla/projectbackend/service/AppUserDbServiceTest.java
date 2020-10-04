package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.repository.AppUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppUserDbServiceTest {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserDbService appUserDbService;

    @Autowired
    private AppUserMapper appUserMapper;

    @Test
    public void loadUserByUsername() {
        //Given
        AppUser appUser = appUserMapper
                .mapToAppUser(new AppUserDto("John","1234","ROLE_USER"));
        appUserRepository.save(appUser);
        //When
        AppUser appUserResult = appUserDbService.loadUserByUsername(appUser.getUsername());
        //Then
        assertEquals(appUser.getId(), appUserResult.getId());
        assertEquals(appUser.getUsername(),appUserResult.getUsername());
        assertEquals(appUser.getPassword(),appUserResult.getPassword());
        assertEquals(appUser.getRole(),appUserResult.getRole());
        //CleanUp
        appUserRepository.delete(appUser);
    }

    @Test
    public void getAppUserById() {
        //Given
        AppUser appUser = appUserMapper
                .mapToAppUser(new AppUserDto("John","1234","ROLE_USER"));
        appUserRepository.save(appUser);
        //When
        AppUser appUserResult = appUserDbService.getAppUserById(appUser.getId());
        //Then
        assertEquals(appUser.getId(),appUserResult.getId());
        assertEquals(appUser.getUsername(),appUserResult.getUsername());
        assertEquals(appUser.getPassword(),appUserResult.getPassword());
        assertEquals(appUser.getRole(),appUserResult.getRole());
        //CleanUp
        appUserRepository.delete(appUser);
    }

    @Test(expected = UserNotFoundException.class)
    public void  getAppUserByIdException() {
        //When & Then
        appUserDbService.getAppUserById(10_000L);
    }

    @Test
    public void getAllAppUsers() {
        //Given
        AppUser appUser = appUserMapper
                .mapToAppUser(new AppUserDto("John","1234","ROLE_USER"));
        List<AppUser> appUserList = appUserRepository.findAll();
        appUserRepository.save(appUser);
        //When
        List<AppUser> appUserListResult = appUserDbService.getAllAppUsers();
        //Then
        assertTrue(appUserList.size()<appUserListResult.size());
        assertTrue(appUserListResult.contains(appUser));
        //CleanUp
        appUserRepository.delete(appUser);
    }

    @Test
    public void saveAppUser() {
        //Given
        AppUser appUser = appUserMapper
                .mapToAppUser(new AppUserDto("John","1234","ROLE_USER"));
        //When
        appUserDbService.saveAppUser(appUser);
        Optional<AppUser> appUserResult = appUserRepository.findById(appUser.getId());
        //Then
        assertTrue(appUserResult.isPresent());
        assertEquals(appUser.getId(),appUserResult.get().getId());
        assertEquals(appUser.getUsername(),appUserResult.get().getUsername());
        assertEquals(appUser.getPassword(),appUserResult.get().getPassword());
        assertEquals(appUser.getRole(),appUserResult.get().getRole());
        //CleanUp
        appUserRepository.delete(appUser);
    }

    @Test
    public void checkExistsByUsername() {
        //Given
        AppUser appUser = appUserMapper
                .mapToAppUser(new AppUserDto("John","1234","ROLE_USER"));
        appUserRepository.save(appUser);
        //When
        Boolean result = appUserDbService.checkExistsByUsername(appUser.getUsername());
        //Then
        assertTrue(result);
        //CleanUp
        appUserRepository.delete(appUser);
    }

    @Test
    public void deleteAppUser() {
        //Given
        AppUser appUser = appUserMapper
                .mapToAppUser(new AppUserDto("John","1234","ROLE_USER"));
        appUserRepository.save(appUser);
        //When
        Boolean result = appUserDbService.deleteAppUser(appUser.getId());
        //Then
        assertFalse(result);
        //CleanUp
        appUserRepository.delete(appUser);
    }

    @Test
    public void existByAppUserId() {
        //Given
        AppUser appUser = appUserMapper
                .mapToAppUser(new AppUserDto("John","1234","ROLE_USER"));
        appUserRepository.save(appUser);
        //When
        Boolean result = appUserDbService.existByAppUserId(appUser.getId());
        //Then
        assertTrue(result);
        //CleanUp
        appUserRepository.delete(appUser);
    }
}