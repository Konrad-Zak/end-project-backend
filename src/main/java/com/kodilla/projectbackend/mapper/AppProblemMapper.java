package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppProblem;
import com.kodilla.projectbackend.domian.AppProblemDto;
import org.springframework.stereotype.Component;

@Component
public class AppProblemMapper {

    public AppProblem mapToAppProblem(AppProblemDto appProblemDto){
        return new AppProblem(appProblemDto.getId(),appProblemDto.getText(),appProblemDto.getTimestamp());
    }
}
