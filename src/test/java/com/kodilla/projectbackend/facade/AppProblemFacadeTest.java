package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.AppProblem;
import com.kodilla.projectbackend.domian.AppProblemDto;
import com.kodilla.projectbackend.mapper.AppProblemMapper;
import com.kodilla.projectbackend.service.AppProblemDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AppProblemFacadeTest {

    @InjectMocks
    private AppProblemFacade appProblemFacade;

    @Mock
    private AppProblemDbService appProblemDbService;

    @Mock
    private AppProblemMapper appProblemMapper;

    @Test
    public void getAllAppProblem() {
        //Given
        Timestamp timestamp = Timestamp.from(Instant.now());
        AppProblem appProblem = new AppProblem(1L,"test",timestamp);
        AppProblemDto appProblemDto = new AppProblemDto(1L,"test",timestamp);
        List<AppProblem> appProblemList = Collections.singletonList(appProblem);
        List<AppProblemDto> appProblemDtoList = Collections.singletonList(appProblemDto);

        when(appProblemDbService.getAllAppProblem()).thenReturn(appProblemList);
        when(appProblemMapper.mapToAppProblemDtoList(appProblemList)).thenReturn(appProblemDtoList);

        //When
        List<AppProblemDto> appProblemDtoResult = appProblemFacade.getAllAppProblem();

        //Then
        assertEquals(appProblemDtoList.size(),appProblemDtoResult.size());
        assertEquals(appProblemDtoList.get(0).getId(),appProblemDtoResult.get(0).getId());
        assertEquals(appProblemDtoList.get(0).getText(),appProblemDtoResult.get(0).getText());
        assertEquals(appProblemDtoList.get(0).getTimestamp(),appProblemDtoResult.get(0).getTimestamp());
    }

}