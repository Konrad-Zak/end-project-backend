package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppProblemDto;
import com.kodilla.projectbackend.mapper.AppProblemMapper;
import com.kodilla.projectbackend.service.AppProblemDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AppProblemFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppProblemFacade.class);
    private AppProblemDbService appProblemDbService;
    private AppProblemMapper appProblemMapper;

    public List<AppProblemDto> getAllAppProblem() {
        LOGGER.info("Request: get all app problems");
        return appProblemMapper.mapToAppProblemDtoList(appProblemDbService.getAllAppProblem());
    }
}
