package com.kodilla.projectbackend.scheduler;

import com.kodilla.projectbackend.configuration.EmailConfiguration;
import com.kodilla.projectbackend.domian.Mail;
import com.kodilla.projectbackend.domian.SearchFoodDto;
import com.kodilla.projectbackend.observer.EdamamCientProblem;
import com.kodilla.projectbackend.service.EdamamService;
import com.kodilla.projectbackend.service.MailSenderService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

@Component
@AllArgsConstructor
public class EdamamScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(EdamamScheduler.class);
    private static final String SUBJECT = "Check Edamam service working";
    private static final String PROBLEM = "Service break down ";
    private static final String SEARCHED_FOOD = "apple";
    private EdamamService edamamService;
    private EdamamCientProblem edamamCientProblem;
    private MailSenderService mailSenderService;
    private EmailConfiguration emailConfiguration;

    @Scheduled(cron = "0 5 * * * *")
    public void checkWorkingService() {
        LOGGER.info(SUBJECT);
        SearchFoodDto searchFoodDto = edamamService.getSearchFood(SEARCHED_FOOD);
        if(searchFoodDto.getParsed()!=null) {
            LOGGER.info("Edamam service working ok");
        } else {
            LOGGER.info("Edamam service working not ok");
            sendEmailToAdmin();
            saveProblemInDb();
        }
    }

    private void sendEmailToAdmin() {
        Timestamp timestamp = Timestamp.from(Instant.now());
        Mail mail = new Mail(emailConfiguration.getAdminEmail(),SUBJECT,PROBLEM + timestamp.toString());
        mailSenderService.send(mail);
    }

    private void saveProblemInDb() {
        edamamCientProblem.addMessage(PROBLEM);
        edamamCientProblem.notifyObservers();
    }
}
