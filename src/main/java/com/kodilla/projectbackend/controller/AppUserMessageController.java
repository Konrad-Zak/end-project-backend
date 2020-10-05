package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppUserMessageDto;
import com.kodilla.projectbackend.facade.AppUserMessageFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appUserMessages")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppUserMessageController {

   private AppUserMessageFacade appUserMessageFacade;

    @PostMapping()
    public Boolean createAppUserMessage(@RequestBody AppUserMessageDto appUserMessageDto){
        return appUserMessageFacade.createAppUserMessage(appUserMessageDto);
    }

    @GetMapping(value = "/admin/allMessage")
    public List<AppUserMessageDto> getAllAppUserMessage() {
        return appUserMessageFacade.getAllAppUserMessage();
    }

    @DeleteMapping(value = "/admin/delete")
    public Boolean deleteByLocalDateBefore(@RequestParam String localDate) {
        return appUserMessageFacade.deleteByLocalDateBefore(localDate);
    }
}
