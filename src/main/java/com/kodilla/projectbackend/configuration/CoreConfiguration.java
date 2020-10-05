package com.kodilla.projectbackend.configuration;

import com.kodilla.projectbackend.observer.CurioClientProblem;
import com.kodilla.projectbackend.observer.EdamamCientProblem;
import com.kodilla.projectbackend.observer.SendEmailProcessProblem;
import com.kodilla.projectbackend.observer.ProblemObserver;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@Configuration
@AllArgsConstructor
@EnableScheduling
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
    public SendEmailProcessProblem sendEmailProcessProblem(){
        return new SendEmailProcessProblem(problemObserver);
    }

    @Bean
    public EdamamCientProblem edamamCientProblem() {
        return new EdamamCientProblem(problemObserver);
    }
}
