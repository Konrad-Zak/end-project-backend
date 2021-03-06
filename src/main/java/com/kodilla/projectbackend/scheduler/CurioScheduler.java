package com.kodilla.projectbackend.scheduler;

import com.kodilla.projectbackend.configuration.EmailConfiguration;
import com.kodilla.projectbackend.domian.CurioDto;
import com.kodilla.projectbackend.domian.Mail;
import com.kodilla.projectbackend.observer.CurioClientProblem;
import com.kodilla.projectbackend.service.CurioDbService;
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
public class CurioScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurioScheduler.class);
    private static final String SUBJECT = "Check Curio service working";
    private static final String PROBLEM = "Service break down ";
    private CurioDbService curioDbService;
    private CurioClientProblem curioClientProblem;
    private MailSenderService mailSenderService;
    private EmailConfiguration emailConfiguration;

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanCurioDb() {
        LOGGER.info("Clean All Curios DataBase");
        curioDbService.cleanAllCuriosInDb();
    }

    @Scheduled(cron = "0 0 * * * *")
    public void checkWorkingService() {
        LOGGER.info(SUBJECT);
        CurioDto curioDto = curioDbService.getCurioDay();
        if(curioDto.getText()!=null & curioDto.getYear()!=null) {
            LOGGER.info("Curio service working ok");
        } else {
            LOGGER.info("Curio service working not ok");
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
        curioClientProblem.addMessage(PROBLEM);
        curioClientProblem.notifyObservers();
    }
}
