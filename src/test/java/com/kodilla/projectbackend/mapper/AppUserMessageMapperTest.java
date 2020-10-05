package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUserMessage;
import com.kodilla.projectbackend.domian.AppUserMessageDto;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppUserMessageMapperTest {

    private AppUserMessageMapper appUserMessageMapper = new AppUserMessageMapper();

    @Test
    public void mapToAppUserMessage() {
        //Given
        AppUserMessageDto appUserMessageDto = new AppUserMessageDto(
                1L,"t@wp.pl","test", LocalDate.now());
        //When
        AppUserMessage appUserMessage = appUserMessageMapper.mapToAppUserMessage(appUserMessageDto);
        //Then
        assertEquals(appUserMessageDto.getId(),appUserMessage.getId());
        assertEquals(appUserMessageDto.getMessage(),appUserMessage.getMessage());
        assertEquals(appUserMessageDto.getEmail(),appUserMessage.getEmail());
        assertEquals(appUserMessageDto.getLocalDate(),appUserMessage.getLocalDate());
    }

    @Test
    public void mapToAppUserMessageDtoList() {
        //Given
        AppUserMessage appUserMessageOne = new AppUserMessage(
                1L,"t@wp.pl","test", LocalDate.now());
        AppUserMessage appUserMessageTwo = new AppUserMessage(
                2L,"t2@wp.pl","test2", LocalDate.now().minusDays(1L));
        List<AppUserMessage> appUserMessageList = Arrays.asList(appUserMessageOne,appUserMessageTwo);
        //When
        List<AppUserMessageDto> resultList = appUserMessageMapper.mapToAppUserMessageDtoList(appUserMessageList);
        //Then
        assertEquals(appUserMessageList.size(),resultList.size());
        assertEquals(appUserMessageList.get(0).getId(),resultList.get(0).getId());
        assertEquals(appUserMessageList.get(0).getEmail(),resultList.get(0).getEmail());
        assertEquals(appUserMessageList.get(0).getMessage(),resultList.get(0).getMessage());
        assertEquals(appUserMessageList.get(0).getLocalDate(),resultList.get(0).getLocalDate());
        assertEquals(appUserMessageList.get(1).getId(),resultList.get(1).getId());
        assertEquals(appUserMessageList.get(1).getEmail(),resultList.get(1).getEmail());
        assertEquals(appUserMessageList.get(1).getMessage(),resultList.get(1).getMessage());
        assertEquals(appUserMessageList.get(1).getLocalDate(),resultList.get(1).getLocalDate());
    }
}