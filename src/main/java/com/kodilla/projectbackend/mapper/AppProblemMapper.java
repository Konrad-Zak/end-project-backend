package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppProblem;
import com.kodilla.projectbackend.domian.AppProblemDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AppProblemMapper {

    public AppProblem mapToAppProblem(AppProblemDto appProblemDto){
        return new AppProblem(appProblemDto.getId(),appProblemDto.getText(),appProblemDto.getTimestamp());
    }

    public List<AppProblemDto> mapToAppProblemDtoList(List<AppProblem> appProblemList) {
        return appProblemList.stream()
                .map(appProblem ->
                        new AppProblemDto(appProblem.getId(),appProblem.getText(),appProblem.getTimestamp()))
                .collect(Collectors.toList());
    }
}
