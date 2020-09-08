package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appUsers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserController {

    private AppUserDbService appUserDbService;
    private AppUserMapper appUserMapper;

    @GetMapping(value = "")
    public List<AppUser> getAppUsers(){
        return appUserDbService.getAllAppUsers();
    }

    @PostMapping()
    public Boolean createAppUser(@RequestParam String login, @RequestParam String password){
        AppUserDto appUserDto = new AppUserDto(login,password);
        return appUserDbService.saveAppUser(appUserMapper.mapToAppUser(appUserDto));
    }

    @DeleteMapping(value = "/{appUserId}")
    public void deleteAppUser(@PathVariable Long appUserId){
        appUserDbService.deleteAppUser(appUserId);
    }

}
