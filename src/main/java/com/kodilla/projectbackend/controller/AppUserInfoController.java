package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.mapper.AppUserInfoMapper;
import com.kodilla.projectbackend.service.AppUserInfoDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/v1/appInfoUsers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserInfoController {

    private AppUserInfoDbService appUserInfoDbService;
    private AppUserInfoMapper appUserInfoMapper;

    @GetMapping()
    public AppUserInfoDto getAppUserInfoByAppUserId(@RequestParam Long appUserId){
        AppUserInfo appUserInfo = appUserInfoDbService.getAppUserInfoByAppUserId(appUserId)
                .orElseThrow(UserNotFoundException::new);
        return appUserInfoMapper.mapToAppUserInfoDto(appUserInfo);
    }

    @PostMapping()
    public void createAppUserInfo(@RequestBody AppUserInfoDto appUserInfoDto){
        appUserInfoDbService.saveAppUserInfo(appUserInfoMapper.mapToAppUserInfo(appUserInfoDto));
    }

}
