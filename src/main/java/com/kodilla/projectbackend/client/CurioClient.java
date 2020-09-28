package com.kodilla.projectbackend.client;

import com.kodilla.projectbackend.domian.CurioDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@AllArgsConstructor
@Component
public class CurioClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurioClient.class);
    private RestTemplate restTemplate;

    public CurioDto getCurrentCurio() {
        try {
            URI uri = UriComponentsBuilder.fromHttpUrl("http://numbersapi.com/" + LocalDate.now().getMonthValue()
                    + "/" + LocalDate.now().getDayOfMonth() + "/date")
                    .queryParam("json")
                    .build().encode().toUri();
            return restTemplate.getForObject(uri, CurioDto.class);
        } catch (ResourceAccessException ex){
            LOGGER.error("Problem with External curio system");
            return new CurioDto();
        }
    }

}
