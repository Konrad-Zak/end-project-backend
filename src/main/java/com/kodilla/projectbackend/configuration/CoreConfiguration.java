package com.kodilla.projectbackend.configuration;

import com.kodilla.projectbackend.observer.CurioClientProblem;
import com.kodilla.projectbackend.observer.NewClientProblem;
import com.kodilla.projectbackend.observer.ProblemObserver;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
public class CoreConfiguration {

    private ProblemObserver problemObserver;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public CurioClientProblem curioClientProblem(){
        return new CurioClientProblem(problemObserver);
    }

    @Bean
    public NewClientProblem newClientProblem(){
        return new NewClientProblem(problemObserver);
    }
}
