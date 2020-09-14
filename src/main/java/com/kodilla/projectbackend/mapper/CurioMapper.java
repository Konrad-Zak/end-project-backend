package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public class CurioMapper {

    private static CurioMapper curioMapper = null;

    public CurioMapper(){
    }

    public static CurioMapper getInstance() {
        if (curioMapper == null) {
            curioMapper = new CurioMapper();
        }
        return curioMapper;
    }

    public Curio mapToCurio(final CurioDto curioDto) {
        return new Curio(curioDto.getId(),curioDto.getText(),curioDto.getYear(), LocalDate.now());
    }

    public CurioDto mapToCurioDto(final Curio curio) {
        return new CurioDto(curio.getId(),curio.getText(),curio.getYear());
    }

}
