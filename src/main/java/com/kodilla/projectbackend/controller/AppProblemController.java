package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppProblemDto;
import com.kodilla.projectbackend.facade.AppProblemFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/appProblem/admin")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class AppProblemController {

    private AppProblemFacade appProblemFacade;

    @GetMapping()
    public List<AppProblemDto> getAllAppProblem() {
        return appProblemFacade.getAllAppProblem();
    }

}
