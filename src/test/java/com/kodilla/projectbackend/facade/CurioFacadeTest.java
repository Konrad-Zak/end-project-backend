package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.mapper.CurioMapper;
import com.kodilla.projectbackend.service.CurioDbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurioFacadeTest {

    @InjectMocks
    private CurioFacade curioFacade;

    @Mock
    private CurioDbService curioDbService;

    @Mock
    private CurioMapper curioMapper;

    @Test
    public void getCurioOfToday() {
        //Given
        Curio curio = new Curio(1L,"test", 2000, LocalDate.now());
        CurioDto curioDto = new CurioDto("test",2000);
        when(curioMapper.mapToCurio(curioDto)).thenReturn(curio);
        when(curioDbService.getCurioDay()).thenReturn(curioDto);
        //When
        CurioDto curioDtoResult = curioFacade.getCurioOfToday();
        //Then
        assertEquals(curioDto.getText(),curioDtoResult.getText());
        assertEquals(curioDto.getYear(),curioDtoResult.getYear());
    }
}