package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppUserCalorie;
import com.kodilla.projectbackend.repository.AppUserCalorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppUserCalorieDbService {

    private AppUserCalorieRepository appUserCalorieRepository;

    public void createAppUserCalorie(AppUserCalorie appUserCalorie){
        appUserCalorieRepository.save(appUserCalorie);
    }
}
