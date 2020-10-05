package com.kodilla.projectbackend.scheduler;

import com.kodilla.projectbackend.observer.CurioClientProblem;
import com.kodilla.projectbackend.service.CurioDbService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CurioScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurioScheduler.class);
    private CurioDbService curioDbService;
    private CurioClientProblem curioClientProblem;

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanCurioDb() {
        LOGGER.info("Clean All Curios DataBase");
        curioDbService.cleanAllCuriosInDb();
        curioClientProblem.addMessage("delete all Curios");
    }
}
