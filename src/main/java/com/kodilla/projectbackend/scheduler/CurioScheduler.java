package com.kodilla.projectbackend.scheduler;

import com.kodilla.projectbackend.service.CurioDbService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CurioScheduler {

    private CurioDbService curioDbService;

    @Scheduled(cron = "0 0 0 * * *")
    public void cleanCurioDb(){
        curioDbService.cleanAllCuriosInDb();
    }
}
