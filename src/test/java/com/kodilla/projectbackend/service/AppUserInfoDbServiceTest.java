package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.mapper.AppUserInfoMapper;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.repository.AppUserInfoRepository;
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
public class AppUserInfoDbServiceTest {

    @Autowired
    private AppUserInfoRepository appUserInfoRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserInfoDbService appUserInfoDbService;

    @Autowired
    private AppUserInfoMapper appUserInfoMapper;

    @Autowired
    private AppUserMapper appUserMapper;

    @Test
    public void getAllAppUserInfo() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("test1","test1","ROLE_USER"));
        AppUser appUserTwo = appUserMapper.mapToAppUser(
                new AppUserDto("test2","test2","ROLE_USER"));
        AppUserInfoDto appUserInfoDtoOne = new AppUserInfoDto("John", "john@wp.com",appUserOne);
        AppUserInfoDto appUserInfoDtoTwo = new AppUserInfoDto("John2", "john2@wp.com",appUserTwo);
        AppUserInfo appUserInfoOne = appUserInfoMapper.mapToAppUserInfo(appUserInfoDtoOne);
        AppUserInfo appUserInfoTwo = appUserInfoMapper.mapToAppUserInfo(appUserInfoDtoTwo);
        List<AppUserInfo> appUserInfoListBefore = appUserInfoRepository.findAll();
        appUserRepository.save(appUserOne);
        appUserRepository.save(appUserTwo);
        appUserInfoRepository.save(appUserInfoOne);
        appUserInfoRepository.save(appUserInfoTwo);
        //When
        List<AppUserInfo> appUserInfoListAfter = appUserInfoDbService.getAllAppUserInfo();
        //Then
        assertTrue(appUserInfoListAfter.size()>appUserInfoListBefore.size());
        assertTrue(appUserInfoListAfter.contains(appUserInfoTwo));
        assertTrue(appUserInfoListAfter.contains(appUserInfoOne));
        //CleanUp
        appUserInfoRepository.delete(appUserInfoOne);
        appUserInfoRepository.delete(appUserInfoTwo);
        appUserRepository.delete(appUserOne);
        appUserRepository.delete(appUserTwo);
    }

    @Test
    public void saveAppUserInfo() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("test1","test1","ROLE_USER"));
        AppUserInfoDto appUserInfoDtoOne = new AppUserInfoDto("John", "john@wp.com",appUserOne);
        AppUserInfo appUserInfoOne = appUserInfoMapper.mapToAppUserInfo(appUserInfoDtoOne);
        appUserRepository.save(appUserOne);
        //When
        appUserInfoDbService.saveAppUserInfo(appUserInfoOne);
        Optional<AppUserInfo> appUserInfoResult = appUserInfoRepository.findById(appUserInfoOne.getId());
        //Then
        assertTrue(appUserInfoResult.isPresent());
        assertEquals(appUserInfoOne.getId(),appUserInfoResult.get().getId());
        assertEquals(appUserInfoOne.getFirstName(),appUserInfoResult.get().getFirstName());
        assertEquals(appUserInfoOne.getEmail(),appUserInfoResult.get().getEmail());
        assertEquals(appUserOne.getId(),appUserInfoResult.get().getAppUser().getId());
        assertEquals(appUserOne.getUsername(),appUserInfoResult.get().getAppUser().getUsername());
        assertEquals(appUserOne.getPassword(),appUserInfoResult.get().getAppUser().getPassword());
        assertEquals(appUserOne.getRole(),appUserInfoResult.get().getAppUser().getRole());
        //CleanUp
        appUserInfoRepository.delete(appUserInfoOne);
        appUserRepository.delete(appUserOne);
    }

    @Test
    public void getAppUserInfoByAppUserId() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("test1","test1","ROLE_USER"));
        AppUserInfoDto appUserInfoDtoOne = new AppUserInfoDto("John", "john@wp.com",appUserOne);
        AppUserInfo appUserInfoOne = appUserInfoMapper.mapToAppUserInfo(appUserInfoDtoOne);
        appUserRepository.save(appUserOne);
        appUserInfoDbService.saveAppUserInfo(appUserInfoOne);
        //When
        AppUserInfo appUserInfoResult = appUserInfoDbService.getAppUserInfoByAppUserId(appUserOne.getId());
        //Then
        assertEquals(appUserInfoOne.getId(),appUserInfoResult.getId());
        assertEquals(appUserInfoOne.getFirstName(),appUserInfoResult.getFirstName());
        assertEquals(appUserInfoOne.getEmail(),appUserInfoResult.getEmail());
        assertEquals(appUserOne.getId(),appUserInfoResult.getAppUser().getId());
        assertEquals(appUserOne.getUsername(),appUserInfoResult.getAppUser().getUsername());
        assertEquals(appUserOne.getPassword(),appUserInfoResult.getAppUser().getPassword());
        assertEquals(appUserOne.getRole(),appUserInfoResult.getAppUser().getRole());
        //CleanUp
        appUserInfoRepository.delete(appUserInfoOne);
        appUserRepository.delete(appUserOne);
    }

    @Test(expected = UserNotFoundException.class)
    public void getAppUserInfoByAppUserIdException() {
        //When & Then
        appUserInfoDbService.getAppUserInfoByAppUserId(10L);
    }

    @Test
    public void checkAppUserInfoExist() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("test1","test1","ROLE_USER"));
        AppUserInfoDto appUserInfoDtoOne = new AppUserInfoDto("John", "john@wp.com",appUserOne);
        AppUserInfo appUserInfoOne = appUserInfoMapper.mapToAppUserInfo(appUserInfoDtoOne);
        appUserRepository.save(appUserOne);
        appUserInfoDbService.saveAppUserInfo(appUserInfoOne);
        //When
        Boolean result = appUserInfoDbService.checkAppUserInfoExist(appUserInfoOne.getId());
        //Then
        assertTrue(result);
        //CleanUp
        appUserInfoRepository.delete(appUserInfoOne);
        appUserRepository.delete(appUserOne);
    }

    @Test
    public void deleteByAppUserId() {
        //Given
        AppUser appUserOne = appUserMapper.mapToAppUser(
                new AppUserDto("test1","test1","ROLE_USER"));
        AppUserInfoDto appUserInfoDtoOne = new AppUserInfoDto("John", "john@wp.com",appUserOne);
        AppUserInfo appUserInfoOne = appUserInfoMapper.mapToAppUserInfo(appUserInfoDtoOne);
        appUserRepository.save(appUserOne);
        appUserInfoDbService.saveAppUserInfo(appUserInfoOne);
        //When
        Boolean result = appUserInfoDbService.deleteByAppUserId(appUserInfoOne.getId());
        //Then
        assertFalse(result);
        //CleanUp
        appUserInfoRepository.delete(appUserInfoOne);
        appUserRepository.delete(appUserOne);
    }

}