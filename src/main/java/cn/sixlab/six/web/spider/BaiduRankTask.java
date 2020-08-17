package cn.sixlab.six.web.spider;

import cn.sixlab.six.web.mapper.RankGroupMapper;
import cn.sixlab.six.web.mapper.RankItemMapper;
import cn.sixlab.six.web.models.RankGroup;
import cn.sixlab.six.web.models.RankItem;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.minesoft.mine.site.core.queue.QueueTemplate;
import tech.minesoft.mine.site.core.utils.HttpUtils;
import tech.minesoft.mine.site.core.vo.ResultJson;
import tech.minesoft.mine.spider.core.Spider;
import tech.minesoft.mine.spider.core.component.SpiderExtractor;
import tech.minesoft.mine.spider.core.component.SpiderGenerator;
import tech.minesoft.mine.spider.core.component.SpiderSaver;
import tech.minesoft.mine.spider.core.utils.Content;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class BaiduRankTask implements SpiderGenerator, SpiderExtractor, SpiderSaver {
    private static String KEY_RESULT = "result";
    private static String KEY_GROUP = "groupId";

    @Autowired
    private RankGroupMapper groupMapper;

    @Autowired
    private RankItemMapper itemMapper;

    @Autowired
    private QueueTemplate template;

    public final Spider spider = init();

    private Spider init(){
        return Spider.builder()
                .setSpiderName("BaiduRank")
                .setExtractor(this)
                .setSaver(this)
                .setGenerator(this)
                .build();
    }

    @Override
    public List<SpiderRequest> generate(String spiderName) {
        List<RankGroup> groupList = groupMapper.selectItems();

        List<SpiderRequest> requestList = new ArrayList<>();
        for (RankGroup group : groupList) {
            Request request = new Request.Builder()
                    .url(group.getGroupLink())
                    .build();

            SpiderRequest build = SpiderRequest.build(spiderName, request);
            build.put(KEY_GROUP, group.getId());
            requestList.add(build);
        }

        return requestList;
    }

    @Override
    public Content extract(SpiderRequest request, SpiderResponse response) {
        try {
            ResultJson json = HttpUtils.responseHandler(response.getResponse(), Charset.forName("gb2312"));

            if(json.isSuccess()){
                List<RankItem> dataList = new ArrayList<>();

                Document document = Jsoup.parse(json.getMessage());

                Elements elements = document.select("table.list-table tr");

                boolean first = true;
                for (Element element : elements) {
                    if(first){
                        first = false;
                        continue;
                    }

                    if(element.hasClass("item-tr")){
                        continue;
                    }

                    RankItem item = new RankItem();

                    if(element.hasClass("hideline")){
                        Element rankElement = element.select(".num-top").first();
                        item.setItemRank(Integer.valueOf(rankElement.text()));
                    }else{
                        Element rankElement = element.select(".num-normal").first();
                        item.setItemRank(Integer.valueOf(rankElement.text()));
                    }

                    Element nameElement = element.select(".list-title").first();
                    item.setItemName(nameElement.text());

                    Element hitElement = element.select(".last span").first();
                    item.setItemHit(Integer.valueOf(hitElement.text()));

                    int change = 0;
                    if(hitElement.hasClass("icon-rise")){
                        change = 1;
                    }else if(hitElement.hasClass("icon-fall")){
                        change = -1;
                    }
                    item.setItemChange(change);

                    dataList.add(item);

                    template.send("newsConsumer", item.getItemName());
                }

                Content content = new Content(request);
                content.result.put(KEY_RESULT, dataList);
                return content;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveContent(Content content) {
        SpiderRequest request = content.getRequest();
        Object group = request.get(KEY_GROUP);
        Object result = content.result.get(KEY_RESULT);
        if(null!=group && null!=result){
            Integer groupId = (Integer) group;
            List<RankItem> dataList = (List<RankItem>) result;

            itemMapper.deleteGroup(groupId);
            itemMapper.insertGroup(groupId, dataList);
        }
    }
}
