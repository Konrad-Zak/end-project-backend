package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserMessage;
import com.kodilla.projectbackend.domian.AppUserMessageDto;
import com.kodilla.projectbackend.mapper.AppUserMessageMapper;
import com.kodilla.projectbackend.repository.AppUserMessageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppUserMessageDbServiceTest {

    @Autowired
    private AppUserMessageRepository appUserMessageRepository;

    @Autowired
    private AppUserMessageDbService appUserMessageDbService;

    @Autowired
    private AppUserMessageMapper appUserMessageMapper;

    @Test
    public void getAllAppUserMessage() {
        //Given
        LocalDate localDateOne = LocalDate.now();
        LocalDate localDateTwo = LocalDate.now().minusDays(1L);
        AppUserMessageDto appUserMessageDtoOne = new AppUserMessageDto(
                "test@wp.pl","test", localDateOne);
        AppUserMessageDto appUserMessageDtoTwo = new AppUserMessageDto(
                "test2@wp.pl","test2", localDateTwo);
        AppUserMessage appUserMessageOne = appUserMessageMapper.mapToAppUserMessage(appUserMessageDtoOne);
        AppUserMessage appUserMessageTwo = appUserMessageMapper.mapToAppUserMessage(appUserMessageDtoTwo);
        appUserMessageRepository.save(appUserMessageOne);
        appUserMessageRepository.save(appUserMessageTwo);
        //When
        List<AppUserMessage> appUserMessageList = appUserMessageDbService.getAllAppUserMessage();
        //Then
        assertEquals(appUserMessageOne.getId(),appUserMessageList.get(0).getId());
        assertEquals(appUserMessageOne.getMessage(),appUserMessageList.get(0).getMessage());
        assertEquals(appUserMessageOne.getEmail(),appUserMessageList.get(0).getEmail());
        assertEquals(localDateOne,appUserMessageList.get(0).getLocalDate());
        assertEquals(appUserMessageTwo.getId(),appUserMessageList.get(1).getId());
        assertEquals(appUserMessageTwo.getMessage(),appUserMessageList.get(1).getMessage());
        assertEquals(appUserMessageTwo.getEmail(),appUserMessageList.get(1).getEmail());
        assertEquals(localDateTwo,appUserMessageList.get(1).getLocalDate());
        //CleanUp
        appUserMessageRepository.delete(appUserMessageOne);
        appUserMessageRepository.delete(appUserMessageTwo);
    }

    @Test
    public void saveAppUserMessage() {
        //Given
        AppUserMessageDto appUserMessageDtoOne = new AppUserMessageDto(
                "test@wp.pl","test", LocalDate.now());
        AppUserMessage appUserMessageOne = appUserMessageMapper.mapToAppUserMessage(appUserMessageDtoOne);
        //When
        appUserMessageDbService.saveAppUserMessage(appUserMessageOne);
        Optional<AppUserMessage> appUserMessageResult = appUserMessageRepository.findById(appUserMessageOne.getId());
        //Then
        assertTrue(appUserMessageResult.isPresent());
        assertEquals(appUserMessageOne.getId(),appUserMessageResult.get().getId());
        assertEquals(appUserMessageOne.getEmail(),appUserMessageResult.get().getEmail());
        assertEquals(appUserMessageOne.getMessage(),appUserMessageResult.get().getMessage());
        assertEquals(appUserMessageOne.getLocalDate(),appUserMessageResult.get().getLocalDate());
        //CleanUp
        appUserMessageRepository.delete(appUserMessageOne);
    }

    @Test
    public void appUserMessageIsExist() {
        //Given
        AppUserMessageDto appUserMessageDtoOne = new AppUserMessageDto(
                "test@wp.pl","test", LocalDate.now());
        AppUserMessage appUserMessageOne = appUserMessageMapper.mapToAppUserMessage(appUserMessageDtoOne);
        appUserMessageDbService.saveAppUserMessage(appUserMessageOne);
        //When
        Boolean result = appUserMessageDbService.appUserMessageIsExist(appUserMessageOne.getId());
        //Then
        assertTrue(result);
        //CleanUp
        appUserMessageRepository.delete(appUserMessageOne);
    }

    @Test
    public void deleteByLocalDateBefore() {
        //Given
        AppUserMessageDto appUserMessageDtoOne = new AppUserMessageDto(
                "test@wp.pl","test", LocalDate.now().minusDays(10L));
        AppUserMessageDto appUserMessageDtoTwo = new AppUserMessageDto(
                "test2@wp.pl","test2", LocalDate.now().minusDays(2L));
        AppUserMessage appUserMessageOne = appUserMessageMapper.mapToAppUserMessage(appUserMessageDtoOne);
        AppUserMessage appUserMessageTwo = appUserMessageMapper.mapToAppUserMessage(appUserMessageDtoTwo);
        appUserMessageDbService.saveAppUserMessage(appUserMessageOne);
        appUserMessageDbService.saveAppUserMessage(appUserMessageTwo);
        //When
        Boolean result = appUserMessageDbService.deleteByLocalDateBefore(LocalDate.now());
        //Then
        assertFalse(result);
        //CleanUp
        appUserMessageRepository.delete(appUserMessageOne);
        appUserMessageRepository.delete(appUserMessageTwo);
    }
}