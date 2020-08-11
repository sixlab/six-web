package cn.sixlab.six.web.schedule;

import cn.sixlab.six.web.spider.BaiduRankSpiderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Transactional
public class BaiduRankService{

    @Autowired
    private BaiduRankSpiderTask task;

    @PostConstruct
    public void init(){
        task.spider.linkTask();
    }

//    @Scheduled(cron = "0 5 0 * * ? ")
    public void link() {
        task.spider.linkTask();
    }

//    @Scheduled(cron = "30 0/1 * * * ? ")
    public void craw() {
        task.spider.crawTask();
    }

}
