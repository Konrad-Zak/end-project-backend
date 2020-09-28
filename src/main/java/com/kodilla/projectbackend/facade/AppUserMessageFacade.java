package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppUserMessage;
import com.kodilla.projectbackend.domian.AppUserMessageDto;
import com.kodilla.projectbackend.mapper.AppUserMessageMapper;
import com.kodilla.projectbackend.service.AppUserMessageDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class AppUserMessageFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppUserMessageFacade.class);
    private AppUserMessageDbService appUserMessageDbService;
    private AppUserMessageMapper appUserMessageMapper;

    public Boolean createAppUserMessage(AppUserMessageDto appUserMessageDto) {
        AppUserMessage appUserMessage = appUserMessageMapper.mapToAppUserMessage(appUserMessageDto);
        LOGGER.info("Request: create new appUserMessage form "+ appUserMessage.getEmail());
        appUserMessageDbService.saveAppUserMessage(appUserMessage);
        return appUserMessageDbService.appUserMessageIsExist(appUserMessage.getId());
    }

    public List<AppUserMessageDto> getAllAppUserMessage() {
        LOGGER.debug("Request: get all users message ");
        return appUserMessageMapper.mapToAppUserMessageDtoList(appUserMessageDbService.gelAllAppUserMessage());
    }

    public Boolean deleteByLocalDateBefore(LocalDate localDate) {
        LOGGER.debug("Request: delete all users message before: " + localDate);
        return appUserMessageDbService.deleteByLocalDateBefore(localDate);
    }

}
