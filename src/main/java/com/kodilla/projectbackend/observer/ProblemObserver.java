package com.kodilla.projectbackend.observer;

import com.kodilla.projectbackend.domian.AppProblem;
import com.kodilla.projectbackend.domian.AppProblemDto;
import com.kodilla.projectbackend.mapper.AppProblemMapper;
import com.kodilla.projectbackend.service.AppProblemDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@AllArgsConstructor
public class ProblemObserver implements Observer {

    private AppProblemMapper appProblemMapper;
    private AppProblemDbService appProblemDbService;


    @Override
    public void update(Problem problem) {
        AppProblemDto appProblemDto = new AppProblemDto(problem.getMessage(),Timestamp.from(Instant.now()));
        AppProblem appProblem = appProblemMapper.mapToAppProblem(appProblemDto);
        appProblemDbService.saveProblem(appProblem);
    }
}
