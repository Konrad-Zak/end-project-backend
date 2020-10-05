package com.kodilla.projectbackend.client;

import com.kodilla.projectbackend.configuration.ExternalApiConfiguration;
import com.kodilla.projectbackend.domian.CurioDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurioClientTest {

    @InjectMocks
    private CurioClient curioClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ExternalApiConfiguration apiConfiguration;

    @Before
    public void init(){
        when(apiConfiguration.getCurioApiEndpoint()).thenReturn("http://test.com");
    }

    @Test
    public void getCurrentCurio() throws URISyntaxException {
        //Given
        CurioDto curioDto = new CurioDto("test",2000);
        URI uri = new URI( "http://test.com" + LocalDate.now().getMonthValue()
                + "/" + LocalDate.now().getDayOfMonth() + "/date?json");
        when(restTemplate.getForObject(uri, CurioDto.class)).thenReturn(curioDto);
        //When
        CurioDto curioDtoResult = curioClient.getCurrentCurio();
        //Then
        assertEquals(curioDto.getText(),curioDtoResult.getText());
        assertEquals(curioDto.getYear(),curioDtoResult.getYear());
    }
}