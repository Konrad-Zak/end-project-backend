package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUserInfo;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.exception.UserNotFoundException;
import com.kodilla.projectbackend.mapper.AppUserInfoMapper;
import com.kodilla.projectbackend.service.AppUserInfoDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/v1/appInfoUsers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserInfoController {

    private AppUserInfoDbService appUserInfoDbService;
    private AppUserInfoMapper appUserInfoMapper;

    @GetMapping()
    public AppUserInfoDto getAppUserInfoByAppUserId(@RequestParam Long appUserId) {
        AppUserInfo appUserInfo = appUserInfoDbService.getAppUserInfoByAppUserId(appUserId)
                .orElseThrow(UserNotFoundException::new);
        return appUserInfoMapper.mapToAppUserInfoDto(appUserInfo);
    }

    @GetMapping(value = "admin/users")
    public List<AppUserInfo> getAppUsersInfo() {
        return appUserInfoDbService.getAllAppUserInfo();
    }

    @PostMapping()
    public Boolean createAppUserInfo(@RequestBody AppUserInfoDto appUserInfoDto) {
        AppUserInfo appUserInfo = appUserInfoMapper.mapToAppUserInfo(appUserInfoDto);
        appUserInfoDbService.saveAppUserInfo(appUserInfo);
        return appUserInfoDbService.checkAppUserInfoExist(appUserInfo.getId());
    }

    @PutMapping()
    public void updateAppUserInfo(@RequestBody AppUserInfoDto appUserInfoDto) {
        appUserInfoDbService.saveAppUserInfo(appUserInfoMapper.mapToAppUserInfo(appUserInfoDto));
    }

}
