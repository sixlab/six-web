package cn.sixlab.six.web.schedule;

import cn.sixlab.six.web.spider.BaiduRankTask;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class BaiduRankLinkJob extends QuartzJobBean {

    @Autowired
    private BaiduRankTask task;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        // @Scheduled(cron = "0 5 0 * * ? ")
        task.spider.linkTask();
    }
}
