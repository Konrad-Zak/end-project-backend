package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.mapper.CurioMapper;
import com.kodilla.projectbackend.service.CurioDbService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class CurioFacade {

    private CurioDbService curioDbService;

    public CurioDto getCurioOfToday(){
        CurioDto curioDto = curioDbService.getCurioDay();
        return curioDto.getText() != null ? saveAndReturn(curioDto): getRandomCurioDay();
    }

    private CurioDto saveAndReturn(CurioDto curioDto) {
        curioDbService.saveCurio(CurioMapper.getInstance().mapToCurio(curioDto));
        return curioDto;
    }

    private CurioDto getRandomCurioDay() {
        Random random = new Random();
        List<Curio> curioList = curioDbService.getCuriosOfDate(LocalDate.now());
        if(curioList.size() > 0){
            return CurioMapper.getInstance().mapToCurioDto(
                    curioList.get(random.nextInt(curioList.size())));
        } else {
            return new CurioDto();
        }
    }


}
