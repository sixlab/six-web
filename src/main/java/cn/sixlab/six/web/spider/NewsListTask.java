package cn.sixlab.six.web.spider;

import cn.sixlab.six.web.mapper.PostInfoMapper;
import cn.sixlab.six.web.models.PostInfo;
import okhttp3.Request;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.minesoft.mine.site.core.utils.HttpUtils;
import tech.minesoft.mine.site.core.utils.SignUtils;
import tech.minesoft.mine.site.core.vo.ResultJson;
import tech.minesoft.mine.spider.core.Spider;
import tech.minesoft.mine.spider.core.component.SpiderExtractor;
import tech.minesoft.mine.spider.core.component.SpiderGenerator;
import tech.minesoft.mine.spider.core.component.SpiderSaver;
import tech.minesoft.mine.spider.core.utils.Content;
import tech.minesoft.mine.spider.core.vo.SpiderRequest;
import tech.minesoft.mine.spider.core.vo.SpiderResponse;

import java.util.List;

@Component
public class NewsListTask implements SpiderGenerator, SpiderExtractor, SpiderSaver {
    private static String KEY_ATTR = "attr_id";

    public final Spider spider = init();

    private Spider init(){
        return Spider.builder()
                .setSpiderName("ItemsNewsList")
                .setExtractor(this)
                .setSaver(this)
                .setGenerator(this)
                .build();
    }

    @Override
    public List<SpiderRequest> generate(String spiderName) {
        return null;
    }

    @Autowired
    private NewsItemTask itemTask;

    @Autowired
    private PostInfoMapper postInfoMapper;

    @Override
    public Content extract(SpiderRequest request, SpiderResponse response) {
        try {
            ResultJson json = HttpUtils.responseHandler(response.getResponse());

            if(json.isSuccess()){
                Document document = Jsoup.parse(json.getMessage());
                Elements elements = document.select(".box-result h2 a");

                if(elements.size()>0){
                    for (Element element : elements) {
                        String link = element.attr("href");

                        PostInfo postInfo = postInfoMapper.selectAlias(SignUtils.md5(link));

                        if(null == postInfo){
                            itemTask.addLink(link, (Integer) request.get(KEY_ATTR));
                        }
                    }
                }

                return new Content(request);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void saveContent(Content content) {

    }

    public void addLink(String link, Integer attrId) {
        Request request = new Request.Builder()
                .url(link)
                .build();

        SpiderRequest build = SpiderRequest.build(spider.spiderName, request);
        build.put(KEY_ATTR, attrId);
        spider.addLink(build);
    }
}
