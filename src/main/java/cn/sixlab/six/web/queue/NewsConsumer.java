package cn.sixlab.six.web.queue;

import cn.sixlab.six.web.mapper.PostAttrMapper;
import cn.sixlab.six.web.models.PostAttr;
import cn.sixlab.six.web.spider.NewsListTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import tech.minesoft.mine.site.core.queue.QueueConsumer;

import java.util.Date;

@Component
public class NewsConsumer implements QueueConsumer {

    @Autowired
    private PostAttrMapper attrMapper;

    @Autowired
    private NewsListTask newsTask;

    @Override
    public void run(Message<Object> message) {
        Object payload = message.getPayload();
        String attrName = String.valueOf(payload);

        PostAttr postAttr = attrMapper.selectTag(attrName);
        if(null == postAttr){
            postAttr = new PostAttr();
            postAttr.setAttrName(attrName);
            postAttr.setAttrSummary("");
            postAttr.setAttrType("tag");
            postAttr.setCreateTime(new Date());

            attrMapper.insert(postAttr);

            postAttr.setAttrCode("attr"+postAttr.getId());

            attrMapper.updateByPrimaryKey(postAttr);
        }

        String link = "https://search.sina.com.cn/?c=news&by=&from=&t=&sort=rel&range=all&q=" + attrName;

        newsTask.addLink(link, postAttr.getId());
    }
}
