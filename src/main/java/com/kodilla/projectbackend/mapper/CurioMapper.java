package com.kodilla.projectbackend.mapper;

import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class CurioMapper {

    public Curio mapToCurio(final CurioDto curioDto) {
        return new Curio(curioDto.getId(),curioDto.getText(),curioDto.getYear(), LocalDate.now());
    }

    public CurioDto mapToCurioDto(final Curio curio) {
        return new CurioDto(curio.getId(),curio.getText(),curio.getYear());
    }

}
