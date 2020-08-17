package cn.sixlab.six.web.schedule;

import cn.sixlab.six.web.spider.NewsListTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class NewsListJob extends QuartzJobBean {

    @Autowired
    private NewsListTask task;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // @Scheduled(cron = "30 0/1 * * * ? ")
        task.spider.crawTask();
    }
}
