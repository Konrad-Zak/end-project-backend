package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.configuration.EmailConfiguration;
import com.kodilla.projectbackend.domian.AppUserMessage;
import com.kodilla.projectbackend.domian.AppUserMessageDto;
import com.kodilla.projectbackend.domian.Mail;
import com.kodilla.projectbackend.mapper.AppUserMessageMapper;
import com.kodilla.projectbackend.service.AppUserMessageDbService;
import com.kodilla.projectbackend.service.MailSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppUserMessageFacadeTest {

    @InjectMocks
    AppUserMessageFacade appUserMessageFacade;

    @Mock
    private AppUserMessageDbService appUserMessageDbService;

    @Mock
    private AppUserMessageMapper appUserMessageMapper;

    @Mock
    private MailSenderService mailSenderService;

    @Mock
    private EmailConfiguration emailConfiguration;

    @Test
    public void createAppUserMessage() {
        //Given
        AppUserMessageDto appUserMessageDto = new AppUserMessageDto(1L,"t@wp.pl",
                "test", LocalDate.now());
        AppUserMessage appUserMessage = new AppUserMessage(1L,"t@wp.pl",
                "test", LocalDate.now());
        String subject = "New message form: "+ appUserMessage.getEmail();
        mailSenderService.send(
                new Mail(emailConfiguration.getAdminEmail(), subject , appUserMessage.getMessage()));
        when(appUserMessageMapper.mapToAppUserMessage(appUserMessageDto)).thenReturn(appUserMessage);
        when(appUserMessageDbService.appUserMessageIsExist(appUserMessage.getId())).thenReturn(true);

        //When
        Boolean result = appUserMessageFacade.createAppUserMessage(appUserMessageDto);

        //Then
        assertTrue(result);
    }

    @Test
    public void getAllAppUserMessage() {
        //Given
        AppUserMessageDto appUserMessageDto = new AppUserMessageDto(1L,"t@wp.pl",
                "test", LocalDate.now());
        AppUserMessage appUserMessage = new AppUserMessage(1L,"t@wp.pl",
                "test", LocalDate.now());
        List<AppUserMessage> appUserMessageList = Collections.singletonList(appUserMessage);
        List<AppUserMessageDto> appUserMessageDtoList = Collections.singletonList(appUserMessageDto);

        when(appUserMessageDbService.getAllAppUserMessage()).thenReturn(appUserMessageList);
        when(appUserMessageMapper.mapToAppUserMessageDtoList(appUserMessageList)).thenReturn(appUserMessageDtoList);

        //When
        List<AppUserMessageDto> appUserMessageDtoListResult = appUserMessageFacade.getAllAppUserMessage();

        //Then
        assertEquals(appUserMessageDtoList.size(),appUserMessageDtoListResult.size());
        assertEquals(appUserMessageDtoList.get(0).getId(),appUserMessageDtoListResult.get(0).getId());
        assertEquals(appUserMessageDtoList.get(0).getMessage(),appUserMessageDtoListResult.get(0).getMessage());
        assertEquals(appUserMessageDtoList.get(0).getEmail(),appUserMessageDtoListResult.get(0).getEmail());
        assertEquals(appUserMessageDtoList.get(0).getLocalDate(),appUserMessageDtoListResult.get(0).getLocalDate());
    }

    @Test
    public void deleteByLocalDateBefore() {
        //Given
        String localDate = "2020-06-20";
        LocalDate localDateConvert = LocalDate.parse(localDate);

        when(appUserMessageDbService.deleteByLocalDateBefore(localDateConvert)).thenReturn(false);

        //When
        Boolean result = appUserMessageFacade.deleteByLocalDateBefore(localDate);

        //Then
        assertTrue(result);
    }
}