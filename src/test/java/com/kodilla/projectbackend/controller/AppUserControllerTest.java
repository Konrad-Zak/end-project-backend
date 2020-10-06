package com.kodilla.projectbackend.controller;

import com.google.gson.Gson;
import com.kodilla.projectbackend.domian.AppUserDto;
import com.kodilla.projectbackend.facade.AppUserFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AppUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserFacade appUserFacade;

    @Test
    public void getAppUsers() throws Exception {
        //Given
        List<AppUserDto> appUserDtoList = Arrays.asList(
                new AppUserDto(1L,"User1","1234","ROLE_USER"),
                new AppUserDto(2L,"User2","123","ROLE_USER"));

        when(appUserFacade.getAppUsers()).thenReturn(appUserDtoList);

        //When & Then
        mockMvc.perform(get("/v1/appUsers/admin/users")
                .with(user("ADMIN").password("ADMIN").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].username",is("User1")))
                .andExpect(jsonPath("$[0].password",is("1234")))
                .andExpect(jsonPath("$[0].role",is("ROLE_USER")))
                .andExpect(jsonPath("$[1].id",is(2)))
                .andExpect(jsonPath("$[1].username",is("User2")))
                .andExpect(jsonPath("$[1].password",is("123")))
                .andExpect(jsonPath("$[1].role",is("ROLE_USER")));

    }

    @Test
    public void createAppUser() throws Exception {
        //Given
        String username = "John";
        String password = "1234";

        when(appUserFacade.createAppUser(username,password)).thenReturn(true);

        //When & Then
        mockMvc.perform(post("/v1/appUsers?username=" + username + "&password=" + password)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(true)));
    }

    @Test
    public void updateAppUser() throws Exception {
        //Given
        AppUserDto appUserDto = new AppUserDto(1L,"User1","1234","ROLE_USER");

        Gson gson = new Gson();
        String jsonContent = gson.toJson(appUserDto);

        //When & Then
        mockMvc.perform(put("/v1/appUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteAppUser() throws Exception {
        //Given
        long appUserId = 7L;
        //When & Then
        mockMvc.perform(delete("/v1/appUsers/admin/delete?appUserId="+appUserId)
                .with(user("ADMIN").password("ADMIN").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getAppUserByUsername() throws Exception {
        //Given
        String username = "John";
        AppUserDto appUserDto = new AppUserDto(1L,"John","1234","ROLE_USER");

        when(appUserFacade.getAppUserByUsername(username)).thenReturn(appUserDto);

        //When & Then
        mockMvc.perform(get("/v1/appUsers?username="+username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.username",is("John")))
                .andExpect(jsonPath("$.password",is("1234")))
                .andExpect(jsonPath("$.role",is("ROLE_USER")));
    }

    @Test
    public void checkExistByUsername() throws Exception {
        //Given
        String username = "John";

        when(appUserFacade.checkExistByUsername(username)).thenReturn(true);

        //When & Then
        mockMvc.perform(get("/v1/appUsers/check?username="+username)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(true)));
    }

}