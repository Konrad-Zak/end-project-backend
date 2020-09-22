package com.kodilla.projectbackend.facade;

import com.kodilla.projectbackend.configuration.AdminConfiguration;
import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.mapper.CurioMapper;
import com.kodilla.projectbackend.observer.*;
import com.kodilla.projectbackend.service.CurioDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Component
@AllArgsConstructor
public class CurioFacade {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurioFacade.class);
    private CurioDbService curioDbService;
    private CurioClientProblem curioClientProblem;
    private CurioMapper curioMapper;

    public CurioDto getCurioOfToday(){
        CurioDto curioDto = curioDbService.getCurioDay();
        return curioDto.getText() != null ? saveAndReturn(curioDto): getAlternativeCurio();
    }

    private CurioDto saveAndReturn(CurioDto curioDto) {
        LOGGER.debug("Save new Curio in data base");
        curioDbService.saveCurio(curioMapper.mapToCurio(curioDto));
        return curioDto;
    }

    private CurioDto getAlternativeCurio(){
        reportProblem();
        return getRandomCurioDay();
    }

    private void reportProblem(){
        curioClientProblem.addMessage("External curio system is break down");
        curioClientProblem.notifyObservers();
    }

    private CurioDto getRandomCurioDay() {
        Random random = new Random();
        List<Curio> curioList = curioDbService.getCuriosOfDate(LocalDate.now());
        if(curioList.size() > 0){
            return curioMapper.mapToCurioDto(
                    curioList.get(random.nextInt(curioList.size())));
        } else {
            return new CurioDto();
        }
    }


}
