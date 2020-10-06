package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.facade.AppUserInfoFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appInfoUsers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserInfoController {

    private AppUserInfoFacade appUserInfoFacade;

    @GetMapping()
    public AppUserInfoDto getAppUserInfoByAppUserId(@RequestParam Long appUserId) {
        return appUserInfoFacade.getAppUserInfoByAppUserId(appUserId);
    }

    @GetMapping(value = "admin/users")
    public List<AppUserInfoDto> getAppUsersInfo() {
        return appUserInfoFacade.getAppUsersInfo();
    }

    @PostMapping()
    public Boolean createAppUserInfo(@RequestBody AppUserInfoDto appUserInfoDto) {
        return appUserInfoFacade.createAppUserInfo(appUserInfoDto);
    }

    @PutMapping()
    public void updateAppUserInfo(@RequestBody AppUserInfoDto appUserInfoDto) {
        appUserInfoFacade.updateAppUserInfo(appUserInfoDto);
    }

}
