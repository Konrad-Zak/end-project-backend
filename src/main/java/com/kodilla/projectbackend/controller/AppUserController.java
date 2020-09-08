package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appUsers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserController {

    private AppUserDbService appUserDbService;
    private AppUserMapper appUserMapper;

    @GetMapping()
    public List<AppUserDto> getAppUsers(){
        return appUserMapper.mapToAppUserDtoList(appUserDbService.getAllAppUsers());
    }

    @PostMapping("/create")
    public Boolean createAppUser(@RequestParam String login, @RequestParam String password){
        AppUserDto appUserDto = new AppUserDto(login,password);
        return appUserDbService.saveAppUser(appUserMapper.mapToAppUser(appUserDto));
    }

    @DeleteMapping(value = "/{appUserId}")
    public void deleteAppUser(@PathVariable Long appUserId){
        appUserDbService.deleteAppUser(appUserId);
    }

    @GetMapping("/find")
    public UserDetails getAppUser(@RequestParam String username){
        return appUserDbService.loadUserByUsername(username);
    }




}
