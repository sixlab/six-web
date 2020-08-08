package cn.sixlab.six.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.minesoft.minesite.core.service.JobService;

@Component
public class JobConfig {

    @Autowired
    private JobService service;

    @Scheduled(cron = "0 30 0 * * ? ")
    public void baiduRankDaily() {
        service.run("baiduRankDailyService");
    }

    @Scheduled(cron = "0 3 7,19 * * ? ")
    public void daily() {
        service.run("jdGoldDailyService");
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void jdPrice() {
        service.run("jdGoldIntervalService");
    }

}
