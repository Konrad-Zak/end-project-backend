package com.kodilla.projectbackend.controller;

import com.google.gson.*;
import com.kodilla.projectbackend.domian.AppUserMessageDto;
import com.kodilla.projectbackend.facade.AppUserMessageFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppUserMessageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserMessageFacade appUserMessageFacade;

    @Test
    public void createAppUserMessage() throws Exception {
        //Given
        AppUserMessageDto appUserMessageDto = new AppUserMessageDto(
                1L,"t@wp.pl","test", LocalDate.now());

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class,
                        (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> new JsonPrimitive(src.toString()))
                .create();
        String jsonContent = gson.toJson(appUserMessageDto);

        when(appUserMessageFacade.createAppUserMessage(ArgumentMatchers.any(AppUserMessageDto.class))).thenReturn(true);

        //When & Then
        mockMvc.perform(post("/v1/appUserMessages")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(true)));
    }

    @Test
    public void getAllAppUserMessage() throws Exception {
        //Given
        AppUserMessageDto appUserMessageDtoOne = new AppUserMessageDto(
                1L,"t@wp.pl","test", LocalDate.now());
        AppUserMessageDto appUserMessageDtoTwo = new AppUserMessageDto(
                2L,"d@wp.pl","test 2", LocalDate.now());
        List<AppUserMessageDto> messageDtoList = Arrays.asList(appUserMessageDtoOne,appUserMessageDtoTwo);

        when(appUserMessageFacade.getAllAppUserMessage()).thenReturn(messageDtoList);

        //When & Then
        mockMvc.perform(get("/v1/appUserMessages/admin/allMessage")
                .with(user("ADMIN").password("ADMIN").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].email",is("t@wp.pl")))
                .andExpect(jsonPath("$[0].message",is("test")))
                .andExpect(jsonPath("$[0].localDate",is(LocalDate.now().toString())))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].email",is("d@wp.pl")))
                .andExpect(jsonPath("$[1].message",is("test 2")))
                .andExpect(jsonPath("$[1].localDate",is(LocalDate.now().toString())));
    }

    @Test
    public void deleteByLocalDateBefore() throws Exception {
        String localDate = LocalDate.now().toString();
        when(appUserMessageFacade.deleteByLocalDateBefore(localDate)).thenReturn(true);

        //When & Then
        mockMvc.perform(delete("/v1/appUserMessages/admin/delete?localDate="+localDate)
                .with(user("ADMIN").password("ADMIN").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(true)));
    }
}