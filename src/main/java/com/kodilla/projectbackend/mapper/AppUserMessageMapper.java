package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.domian.AppUserMessage;
import com.kodilla.projectbackend.domian.AppUserMessageDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AppUserMessageMapper {

    public AppUserMessage mapToAppUserMessage(AppUserMessageDto appUserMessageDto) {
        return new AppUserMessage(appUserMessageDto.getId(),appUserMessageDto.getEmail(),
                appUserMessageDto.getMessage(), appUserMessageDto.getLocalDate());
    }

    public AppUserMessageDto mapToAppUserMessageDto(AppUserMessage appUserMessage) {
        return new AppUserMessageDto(appUserMessage.getId(), appUserMessage.getEmail(),
                appUserMessage.getMessage(), appUserMessage.getLocalDate());
    }

    public List<AppUserMessageDto> mapToAppUserMessageDtoList(List<AppUserMessage> appUserMessageList){
        return appUserMessageList.stream()
                .map(appUserMessage -> new AppUserMessageDto(
                        appUserMessage.getId(), appUserMessage.getEmail(),
                        appUserMessage.getMessage(), appUserMessage.getLocalDate()))
                .collect(Collectors.toList());
    }
}
