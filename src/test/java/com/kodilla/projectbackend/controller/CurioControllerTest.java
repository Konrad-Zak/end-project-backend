package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.facade.CurioFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CurioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurioFacade curioFacade;

    @Test
    public void getCurioCurrentDate() throws Exception {
        //Given
        CurioDto curioDto = new CurioDto(1L,"test",LocalDate.now().getYear(), LocalDate.now());

        when(curioFacade.getCurioOfToday()).thenReturn(curioDto);

        //When & Then
        mockMvc.perform(get("/v1/curio")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.text", is("test")))
                .andExpect(jsonPath("$.year", is(LocalDate.now().getYear())))
                .andExpect(jsonPath("$.localDate", is(LocalDate.now().toString())));
    }
}