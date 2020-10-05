package com.kodilla.projectbackend.controller;

import com.google.gson.Gson;
import com.kodilla.projectbackend.domian.AppUser;
import com.kodilla.projectbackend.domian.AppUserInfoDto;
import com.kodilla.projectbackend.facade.AppUserInfoFacade;
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
public class AppUserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserInfoFacade appUserInfoFacade;

    @Test
    public void getAppUserInfoByAppUserId() throws Exception {
        //Given
        AppUser appUser = new AppUser(1L,"John", "1234","ROLE_USER");
        AppUserInfoDto appUserInfoDto = new AppUserInfoDto(1L,"Tom","t@wp.pl",appUser);

        when(appUserInfoFacade.getAppUserInfoByAppUserId(appUser.getId())).thenReturn(appUserInfoDto);

        //When & Then
        mockMvc.perform(get("/v1/appInfoUsers?appUserId=" + appUser.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.firstName", is("Tom")))
                .andExpect(jsonPath("$.email", is("t@wp.pl")))
                .andExpect(jsonPath("$.appUser.id", is(1)))
                .andExpect(jsonPath("$.appUser.username", is("John")))
                .andExpect(jsonPath("$.appUser.password", is("1234")))
                .andExpect(jsonPath("$.appUser.role", is("ROLE_USER")));
    }

    @Test
    public void getAppUsersInfo() throws Exception {
        //Given
        AppUser appUserOne = new AppUser(1L,"John", "1234","ROLE_USER");
        AppUser appUserTwo = new AppUser(2L,"John 2", "123","ROLE_USER");
        AppUserInfoDto appUserInfoDtoOne = new AppUserInfoDto(1L,"Tom","t@wp.pl",appUserOne);
        AppUserInfoDto appUserInfoDtoTwo = new AppUserInfoDto(2L,"Dan","d@wp.pl",appUserTwo);
        List<AppUserInfoDto> appUserInfoDtoList = Arrays.asList(appUserInfoDtoOne,appUserInfoDtoTwo);

        when(appUserInfoFacade.getAppUsersInfo()).thenReturn(appUserInfoDtoList);

        //When & Then
        mockMvc.perform(get("/v1/appInfoUsers/admin/users")
                .with(user("ADMIN").password("ADMIN").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].firstName", is("Tom")))
                .andExpect(jsonPath("$[0].email", is("t@wp.pl")))
                .andExpect(jsonPath("$[0].appUser.id", is(1)))
                .andExpect(jsonPath("$[0].appUser.username", is("John")))
                .andExpect(jsonPath("$[0].appUser.password", is("1234")))
                .andExpect(jsonPath("$[0].appUser.role", is("ROLE_USER")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].firstName", is("Dan")))
                .andExpect(jsonPath("$[1].email", is("d@wp.pl")))
                .andExpect(jsonPath("$[1].appUser.id", is(2)))
                .andExpect(jsonPath("$[1].appUser.username", is("John 2")))
                .andExpect(jsonPath("$[1].appUser.password", is("123")))
                .andExpect(jsonPath("$[1].appUser.role", is("ROLE_USER")));
    }

    @Test
    public void createAppUserInfo() throws Exception {
        //Given
        AppUser appUser = new AppUser(1L,"John", "1234","ROLE_USER");
        AppUserInfoDto appUserInfoDto = new AppUserInfoDto(1L,"Tom","t@wp.pl",appUser);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(appUserInfoDto);

        when(appUserInfoFacade.createAppUserInfo(ArgumentMatchers.any(AppUserInfoDto.class))).thenReturn(true);

        //When & Then
        mockMvc.perform(post("/v1/appInfoUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",is(true)));
    }

    @Test
    public void updateAppUserInfo() throws Exception {
        //Given
        AppUser appUser = new AppUser(1L,"John", "1234","ROLE_USER");
        AppUserInfoDto appUserInfoDto = new AppUserInfoDto(1L,"Tom","t@wp.pl",appUser);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(appUserInfoDto);

        //When & Then
        mockMvc.perform(put("/v1/appInfoUsers")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

}