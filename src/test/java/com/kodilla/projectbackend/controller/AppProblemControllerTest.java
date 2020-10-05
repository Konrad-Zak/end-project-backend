package com.kodilla.projectbackend.controller;

import com.kodilla.projectbackend.domian.AppProblemDto;
import com.kodilla.projectbackend.facade.AppProblemFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppProblemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppProblemFacade appProblemFacade;

    @Test
    public void getAllAppProblem() throws Exception {
        //Given
        Timestamp timestampOne = Timestamp.from(Instant.now());
        Timestamp timestampTwo = Timestamp.from(Instant.now());
        List<AppProblemDto> appProblemDtoList = Arrays.asList(new AppProblemDto(1L,"test 1",timestampOne),
                new AppProblemDto(2L, "test 2", timestampTwo));

        when(appProblemFacade.getAllAppProblem()).thenReturn(appProblemDtoList);

        //When & Then
        mockMvc.perform(get("/v1/appProblem/admin")
                .with(user("ADMIN").password("ADMIN").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].text",is("test 1")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].text",is("test 2")));
    }

}