package com.kodilla.projectbackend.configuration;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor
public class EmailConfiguration {

    @Value("${spring.mail.username}")
    private String adminEmail;

    @Value("${spring.mail.password}")
    private String emailPassword;
}
