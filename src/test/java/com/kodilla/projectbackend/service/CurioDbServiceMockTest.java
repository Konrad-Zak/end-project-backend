package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.client.CurioClient;
import com.kodilla.projectbackend.domian.CurioDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurioDbServiceMockTest {

    @InjectMocks
    private CurioDbService curioDbService;

    @Mock
    private CurioClient curioClient;

    @Test
    public void getCurioDay() {
        //Given
        CurioDto curioDto = new CurioDto("test",2000);
        when(curioClient.getCurrentCurio()).thenReturn(curioDto);
        //When
        CurioDto curioDtoResult = curioDbService.getCurioDay();
        //Then
        assertEquals(curioDto.getText(), curioDtoResult.getText());
        assertEquals(curioDto.getYear(), curioDtoResult.getYear());
    }
}