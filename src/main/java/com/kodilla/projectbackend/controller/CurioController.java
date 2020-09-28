package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.facade.CurioFacade;
import com.kodilla.projectbackend.service.CurioDbService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/curio")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CurioController {

    private  CurioFacade curioFacade;

    @GetMapping()
    public CurioDto getCurioCurrentDate() {
        return curioFacade.getCurioOfToday();
    }
}
