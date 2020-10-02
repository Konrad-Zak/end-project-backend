package com.kodilla.projectbackend.configuration;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ExternalApiConfiguration {

    @Value("${curio.api.endpoint}")
    private String curioApiEndpoint;

    @Value("${edamam.api.endpoint}")
    private String edamamApiEndpoint;

    @Value("${edamam.api.appId}")
    private String edamamAppId;

    @Value("${edamam.api.appKey}")
    private String edamamAppKey;

}
