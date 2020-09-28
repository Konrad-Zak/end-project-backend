package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.facade.AppUserFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appUsers")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserController {

    private AppUserFacade appUserFacade;

    @GetMapping(value = "/admin/users")
    public List<AppUserDto> getAppUsers(){
        return appUserFacade.getAppUsers();
    }

    @PostMapping()
    public Boolean createAppUser(@RequestParam String username, @RequestParam String password) {
        return appUserFacade.createAppUser(username,password);
    }

    @PutMapping()
    public void updateAppUser(@RequestBody AppUserDto appUserDto) {
        appUserFacade.updateAppUser(appUserDto);
    }

    @DeleteMapping(value = "/admin/delete")
    public void deleteAppUser(@RequestParam Long appUserId) {
        appUserFacade.deleteAppUser(appUserId);
    }

    @GetMapping()
    public AppUserDto getAppUserByUsername(@RequestParam  String username) {
            return appUserFacade.getAppUserByUsername(username);
    }

    @GetMapping(value = "/check")
    public Boolean checkExistByUsername(@RequestParam String username) {
        return appUserFacade.checkExistByUsername(username);
    }

}
