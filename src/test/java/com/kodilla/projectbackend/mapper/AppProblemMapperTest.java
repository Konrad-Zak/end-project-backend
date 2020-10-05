package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.AppProblem;
import com.kodilla.projectbackend.domian.AppProblemDto;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class AppProblemMapperTest {

    private AppProblemMapper appProblemMapper = new AppProblemMapper();

    @Test
    public void mapToAppProblem() {
        //Given
        AppProblemDto appProblemDto = new AppProblemDto(2L,"test", Timestamp.from(Instant.now()));
        //When
        AppProblem appProblem = appProblemMapper.mapToAppProblem(appProblemDto);
        //Then
        assertEquals(appProblemDto.getId(),appProblem.getId());
        assertEquals(appProblemDto.getText(),appProblem.getText());
        assertEquals(appProblemDto.getTimestamp(),appProblem.getTimestamp());
    }

    @Test
    public void mapToAppProblemDtoList() {
        //Given
        Timestamp timestampOne = Timestamp.from(Instant.now());
        Timestamp timestampTwo = Timestamp.from(Instant.now());
        AppProblem appProblem = new AppProblem(2L,"test",timestampOne);
        AppProblem appProblem1 = new AppProblem(3L, "test1",timestampTwo);
        List<AppProblem> appProblemList = Arrays.asList(appProblem,appProblem1);
        //When
        List<AppProblemDto> appProblemDtoList = appProblemMapper.mapToAppProblemDtoList(appProblemList);
        //Then
        assertEquals(appProblemList.size(), appProblemDtoList.size());
        assertEquals(appProblemList.get(0).getId(),appProblemDtoList.get(0).getId());
        assertEquals(appProblemList.get(0).getText(),appProblemDtoList.get(0).getText());
        assertEquals(appProblemList.get(0).getTimestamp(),appProblemDtoList.get(0).getTimestamp());
        assertEquals(appProblemList.get(1).getId(),appProblemDtoList.get(1).getId());
        assertEquals(appProblemList.get(1).getText(),appProblemDtoList.get(1).getText());
        assertEquals(appProblemList.get(1).getTimestamp(),appProblemDtoList.get(1).getTimestamp());
    }
}