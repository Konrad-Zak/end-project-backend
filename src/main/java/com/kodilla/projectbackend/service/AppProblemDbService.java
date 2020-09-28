package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppProblem;
import com.kodilla.projectbackend.repository.AppProblemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AppProblemDbService {

    private AppProblemRepository appProblemRepository;

    public void saveProblem(AppProblem appProblem){
        appProblemRepository.save(appProblem);
    }
}
