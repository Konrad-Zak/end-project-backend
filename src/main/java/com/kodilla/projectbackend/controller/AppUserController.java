package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.mapper.AppUserMapper;
import com.kodilla.projectbackend.service.AppUserDbService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appUsers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserController {

    private AppUserDbService appUserDbService;
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/admin/users")
    public List<AppUserDto> getAppUsers(){
        return AppUserMapper.getInstance().mapToAppUserDtoList(appUserDbService.getAllAppUsers());
    }

    @PostMapping()
    public void createAppUser(@RequestParam String login, @RequestParam String password){
        AppUserDto appUserDto = new AppUserDto(login,passwordEncoder.encode(password));
        appUserDbService.saveAppUser(AppUserMapper.getInstance().mapToAppUser(appUserDto));
    }

    @PutMapping()
    public void updateAppUser(@RequestParam Long appUserId, @RequestParam String login, @RequestParam String password){
        AppUserDto appUserDto = new AppUserDto(appUserId,login,passwordEncoder.encode(password));
        appUserDbService.saveAppUser(AppUserMapper.getInstance().mapToAppUser(appUserDto));
    }

    @DeleteMapping(value = "/admin/delete")
    public void deleteAppUser(@RequestParam Long appUserId){
        appUserDbService.deleteAppUser(appUserId);
    }

    @GetMapping()
    public AppUserDto getAppUserByUsername(@RequestParam  String username){
            return AppUserMapper.getInstance().mapToAppUserDto(appUserDbService.loadUserByUsername(username));
    }

    @GetMapping("/find")
    public AppUserDto getAppUserByLoginId(@RequestParam Long appUserId){
        return AppUserMapper.getInstance().mapToAppUserDto(appUserDbService.findUserById(appUserId));
    }

}
