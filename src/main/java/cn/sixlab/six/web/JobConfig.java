package cn.sixlab.six.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tech.minesoft.mine.site.core.service.MsJobService;

@Component
public class JobConfig {

    @Autowired
    private MsJobService service;

    @Scheduled(cron = "0 3 7,19 * * ? ")
    public void jdGoldDaily() {
        service.run("jdGoldDaily");
    }

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void jdGoldInterval() {
        service.run("jdGoldInterval");
    }

    @Scheduled(cron = "0 5 0 * * ? ")
    public void baiduRankLink() {
        service.run("baiduRankLink");
    }

    @Scheduled(cron = "30 0/1 * * * ? ")
    public void baiduRankCraw() {
        service.run("baiduRankCraw");
    }
}
