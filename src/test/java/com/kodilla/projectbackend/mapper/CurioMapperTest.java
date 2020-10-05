package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class CurioMapperTest {

    private CurioMapper curioMapper = new CurioMapper();

    @Test
    public void mapToCurio() {
        //Given
        CurioDto curioDto = new CurioDto(2L,"test",2000, LocalDate.now());
        //When
        Curio curio = curioMapper.mapToCurio(curioDto);
        //Then
        assertEquals(curioDto.getId(),curio.getId());
        assertEquals(curioDto.getText(),curio.getText());
        assertEquals(curioDto.getYear(),curio.getYear());
        assertEquals(curioDto.getLocalDate(),curio.getLocalDate());
    }

    @Test
    public void mapToCurioDto() {
        //Given
        Curio curio = new Curio(2L,"test",2000, LocalDate.now());
        //When
        CurioDto curioDto = curioMapper.mapToCurioDto(curio);
        //Then
        assertEquals(curio.getId(),curioDto.getId());
        assertEquals(curio.getText(),curioDto.getText());
        assertEquals(curio.getYear(),curioDto.getYear());
        assertEquals(curio.getLocalDate(),curioDto.getLocalDate());
    }
}