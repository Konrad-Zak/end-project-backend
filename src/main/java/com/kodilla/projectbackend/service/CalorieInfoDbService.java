package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.CalorieInfo;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.repository.CalorieInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalorieInfoService {

    private CalorieInfoRepository calorieInfoRepository;

    public void saveAppCalorieInfo(CalorieInfo calorieInfo) {
        calorieInfoRepository.save(calorieInfo);
    }

    public CalorieInfo getCalorieInfoByAppUserId(Long appUserId) {
        return calorieInfoRepository.findByAppUserId(appUserId).orElseThrow(UserNotFoundException::new);
    }

    public Boolean deleteByCalorieInfoId(Long calorieInfoId) {
        calorieInfoRepository.deleteById(calorieInfoId);
        return calorieInfoRepository.existsById(calorieInfoId);
    }

    public Boolean checkExistByAppUserId(Long appUserId) {
        return calorieInfoRepository.existsByAppUser_Id(appUserId);
    }

}
