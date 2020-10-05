package com.kodilla.projectbackend.client;

import com.kodilla.projectbackend.domian.SearchFoodDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@AllArgsConstructor
public class EdamamClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(EdamamClient.class);
    private RestTemplate restTemplate;

    public SearchFoodDto getSearchFood(String foodName) {
        try {
            LOGGER.debug("Send request to external edamam system");
            URI uri = UriComponentsBuilder.fromHttpUrl("https://api.edamam.com/api/food-database/v2/parser")
                    .queryParam("app_id","3e93a065")
                    .queryParam("app_key","882438b570964258465ac7715c9c41cd" )
                    .queryParam("ingr",foodName)
                    .build().encode().toUri();
            return restTemplate.getForObject(uri, SearchFoodDto.class);
        } catch (RuntimeException ex) {
            LOGGER.error("Problem with External edamam system");
            return new SearchFoodDto();
        }
    }

}
