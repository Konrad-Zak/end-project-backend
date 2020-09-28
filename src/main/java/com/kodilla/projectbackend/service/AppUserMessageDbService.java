package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserMessage;
import com.kodilla.projectbackend.repository.AppUserMessageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class AppUserMessageDbService {

    private AppUserMessageRepository appUserMessageRepository;

    public List<AppUserMessage> gelAllAppUserMessage(){
        return appUserMessageRepository.findAll();
    }

    public void saveAppUserMessage(AppUserMessage appUserMessage) {
        appUserMessageRepository.save(appUserMessage);
    }

    public Boolean appUserMessageIsExist(Long appUserMessageId) {
        return appUserMessageRepository.existsById(appUserMessageId);
    }

    public Boolean deleteByLocalDateBefore(LocalDate localDate) {
        return appUserMessageRepository.deleteByLocalDateBefore(localDate);
    }

}
