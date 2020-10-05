package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppProblemDto;
import com.kodilla.projectbackend.facade.AppProblemFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/appProblem")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppProblemController {

    private AppProblemFacade appProblemFacade;

    @GetMapping()
    public List<AppProblemDto> getAllAppProblem() {
        return appProblemFacade.getAllAppProblem();
    }

}
