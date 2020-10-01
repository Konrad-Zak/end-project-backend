package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.client.CurioClient;
import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.mapper.CurioMapper;
import com.kodilla.projectbackend.repository.CurioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CurioDbService {

    private CurioRepository curioRepository;
    private CurioClient curioClient;

    public List<Curio> getCuriosOfDate(LocalDate localDate){
        return curioRepository.getAllByLocalDate(localDate);
    }

    public CurioDto getCurioDay(){
        return curioClient.getCurrentCurio();
    }

    public void saveCurio(Curio curio){
        curioRepository.save(curio);
    }

    public void cleanAllCuriosInDb() {
        curioRepository.deleteAll();
    }

}
