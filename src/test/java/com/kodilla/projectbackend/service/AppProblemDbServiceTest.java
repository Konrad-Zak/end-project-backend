package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.AppProblem;
import com.kodilla.projectbackend.domian.AppProblemDto;
import com.kodilla.projectbackend.mapper.AppProblemMapper;
import com.kodilla.projectbackend.repository.AppProblemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AppProblemDbServiceTest {

    @Autowired
    private AppProblemRepository appProblemRepository;

    @Autowired
    private AppProblemMapper appProblemMapper;

    @Autowired
    private AppProblemDbService appProblemDbService;

    @Test
    public void saveProblem() {
        //Given
        Timestamp timestamp = Timestamp.from(Instant.now());
        AppProblem appProblem = appProblemMapper.mapToAppProblem(new AppProblemDto("Problem", timestamp));
        //When
        appProblemDbService.saveProblem(appProblem);
        Optional<AppProblem> appProblemResult = appProblemRepository.findById(appProblem.getId());
        //Then
        assertTrue(appProblemResult.isPresent());
        assertEquals(appProblem.getId(),appProblemResult.get().getId());
        assertEquals(appProblem.getText(),appProblemResult.get().getText());
        //CleanUp
        appProblemRepository.delete(appProblem);
    }

    @Test
    public void getAllAppProblem() {
        //Given
        Timestamp firstProblem = Timestamp.from(Instant.now());
        Timestamp secondProblem = Timestamp.from(Instant.now());
        AppProblem appProblemOne = appProblemMapper.mapToAppProblem(new AppProblemDto("Problem 1",firstProblem));
        AppProblem appProblemTwo = appProblemMapper.mapToAppProblem(new AppProblemDto("Problem 2",secondProblem));
        appProblemRepository.save(appProblemOne);
        appProblemRepository.save(appProblemTwo);
        //When
        List<AppProblem> appProblemList = appProblemDbService.getAllAppProblem();
        //Then
        assertEquals(2, appProblemList.size());
        assertEquals(appProblemOne.getId(),appProblemList.get(0).getId());
        assertEquals(appProblemOne.getText(),appProblemList.get(0).getText());
        assertEquals(appProblemTwo.getId(),appProblemList.get(1).getId());
        assertEquals(appProblemTwo.getText(),appProblemList.get(1).getText());
        //CleanUp
        appProblemRepository.deleteAll();
    }

}