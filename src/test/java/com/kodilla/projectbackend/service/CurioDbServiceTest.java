package com.kodilla.projectbackend.service;

import com.kodilla.projectbackend.domian.Curio;
import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.mapper.CurioMapper;
import com.kodilla.projectbackend.repository.CurioRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CurioDbServiceTest {

    @Autowired
    private CurioRepository curioRepository;

    @Autowired
    private CurioDbService curioDbService;

    @Autowired
    private CurioMapper curioMapper;

    @Test
    public void getCuriosOfDate() {
        //Given
        Curio curioOne = curioMapper.mapToCurio(new CurioDto("test 1", 1991));
        Curio curioTwo = curioMapper.mapToCurio(new CurioDto("test 2", 1990));
        curioRepository.save(curioOne);
        curioRepository.save(curioTwo);
        //When
        List<Curio> curioList = curioDbService.getCuriosOfDate(LocalDate.now());
        //Then
        assertEquals(2,curioList.size());
        assertEquals(curioOne.getId(), curioList.get(0).getId());
        assertEquals(curioOne.getText(), curioList.get(0).getText());
        assertEquals(curioOne.getLocalDate(), curioList.get(0).getLocalDate());
        assertEquals(curioOne.getYear(), curioList.get(0).getYear());
        //CleanUp
        curioRepository.delete(curioOne);
        curioRepository.delete(curioTwo);
    }

    @Test
    public void saveCurio() {
        //Given
        Curio curioOne = curioMapper.mapToCurio(new CurioDto("test 1", 1991));
        //When
        curioDbService.saveCurio(curioOne);
        Optional<Curio> curioResult = curioRepository.findById(curioOne.getId());
        //Then
        assertTrue(curioResult.isPresent());
        assertEquals(curioOne.getId(),curioResult.get().getId());
        assertEquals(curioOne.getYear(), curioResult.get().getYear());
        assertEquals(curioOne.getText(), curioResult.get().getText());
        assertEquals(curioOne.getLocalDate(), curioResult.get().getLocalDate());
        //CleanUp
        curioRepository.delete(curioOne);
    }

    @Test
    public void cleanAllCuriosInDb() {
        //Given
        Curio curioOne = curioMapper.mapToCurio(new CurioDto("test 1", 1991));
        curioDbService.saveCurio(curioOne);
        //When
        curioDbService.cleanAllCuriosInDb();
        boolean result = curioRepository.existsById(curioOne.getId());
        //Then
        assertFalse(result);
    }
}