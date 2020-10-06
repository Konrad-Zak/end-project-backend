package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.configuration.EmailConfiguration;
import com.kodilla.projectbackend.domian.AppUserMessage;
import com.kodilla.projectbackend.domian.AppUserMessageDto;
import com.kodilla.projectbackend.domian.Mail;
import com.kodilla.projectbackend.mapper.AppUserMessageMapper;
import com.kodilla.projectbackend.service.AppUserMessageDbService;
import com.kodilla.projectbackend.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class AppUserMessageFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserMessageFacade.class);
    private AppUserMessageDbService appUserMessageDbService;
    private AppUserMessageMapper appUserMessageMapper;
    private MailSenderService mailSenderService;
    private EmailConfiguration emailConfiguration;

    public Boolean createAppUserMessage(AppUserMessageDto appUserMessageDto) {
        try {
            AppUserMessage appUserMessage = appUserMessageMapper.mapToAppUserMessage(appUserMessageDto);
            LOGGER.info("Request: create new appUserMessage form " + appUserMessage.getEmail());
            appUserMessageDbService.saveAppUserMessage(appUserMessage);
            sendMailToAdmin(appUserMessage);
            return appUserMessageDbService.appUserMessageIsExist(appUserMessage.getId());
        } catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    public List<AppUserMessageDto> getAllAppUserMessage() {
        LOGGER.debug("Request: get all users message ");
        return appUserMessageMapper.mapToAppUserMessageDtoList(appUserMessageDbService.getAllAppUserMessage());
    }

    public Boolean deleteByLocalDateBefore(String localDate) {
        try {
            LocalDate localDateConvert = LocalDate.parse(localDate);
            LOGGER.debug("Request: delete all users message before: " + localDateConvert);
            return !appUserMessageDbService.deleteByLocalDateBefore(localDateConvert);
        } catch (RuntimeException ex){
            LOGGER.error("Request: Can not delete all users message before: " + localDate);
            return false;
        }
    }

    private void sendMailToAdmin(AppUserMessage appUserMessage) {
        String subject = "New message form: "+ appUserMessage.getEmail();
        mailSenderService.send(
                new Mail(emailConfiguration.getAdminEmail(), subject , appUserMessage.getMessage()));
    }

}
