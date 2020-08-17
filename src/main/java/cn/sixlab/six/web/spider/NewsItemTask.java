package cn.sixlab.six.web.spider;

import cn.sixlab.six.web.mapper.PostAttrRelateMapper;
import cn.sixlab.six.web.mapper.PostInfoMapper;
import cn.sixlab.six.web.models.PostAttrRelate;
import cn.sixlab.six.web.models.PostInfo;
import cn.sixlab.six.web.utils.Const;
import okhttp3.Request;
import org.apache.commons.collections4.MapUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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

import java.util.Date;
import java.util.List;

@Component
public class NewsItemTask implements SpiderGenerator, SpiderExtractor, SpiderSaver {
    private static String KEY_ATTR = "attr_id";

    public final Spider spider = init();

    private Spider init(){
        return Spider.builder()
                .setSpiderName("ItemsNews")
                .setExtractor(this)
                .setSaver(this)
                .setGenerator(this)
                .build();
    }

    @Override
    public List<SpiderRequest> generate(String spiderName) {
        return null;
    }

    @Override
    public Content extract(SpiderRequest request, SpiderResponse response) {
        try {
            ResultJson json = HttpUtils.responseHandler(response.getResponse());

            if(json.isSuccess()){
                Content content = new Content(request);
                Document document = Jsoup.parse(json.getMessage());

                String host = request.getRequest().url().host();

                if(host.startsWith("blog.")){
                    sinaBlog(content, document);
                }else if(host.startsWith("news.") || host.startsWith("k.")){
                    sinaNews(content, document);
                }else{
                    return null;
                }

                return content;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private void sinaBlog(Content content, Document document){
        String title = "";
        String html = title;
        String summary = title;

        Element titleElement = document.selectFirst("#articlebody .articalTitle h2");

        if(null!=titleElement){
            title = titleElement.text();
        }

        Element articleElement = document.selectFirst("#articlebody .articalContent");

        if(null != articleElement){
            html = articleElement.html();
            summary = articleElement.text();
            if(null!=summary && summary.length()>100){
                summary = summary.substring(0, 100);
            }
        }

        content.result.put("title", title);
        content.result.put("content", html);
        content.result.put("summary", summary);
    }

    private void sinaNews(Content content, Document document){
        String title = "";
        String html = title;
        String summary = title;

        Element titleElement = document.selectFirst(".main-title");

        if(null!=titleElement){
            title = titleElement.text();
        }

        Element articleElement = document.selectFirst("#article");

        if(null != articleElement){
            html = articleElement.html();
            summary = articleElement.text();
            if(null!=summary && summary.length()>100){
                summary = summary.substring(0, 100);
            }
        }

        content.result.put("title", title);
        content.result.put("content", html);
        content.result.put("summary", summary);
    }

    @Autowired
    private PostInfoMapper postInfoMapper;

    @Autowired
    private PostAttrRelateMapper relateMapper;

    @Override
    public void saveContent(Content content) {
        Integer attrId = (Integer) content.getRequest().get(KEY_ATTR);
        String aliasName = SignUtils.md5(content.getRequest().getUrl());

        PostInfo postInfo = postInfoMapper.selectAlias(aliasName);
        if( null == postInfo ){
            postInfo = new PostInfo();
            postInfo.setAliasName(aliasName);
            postInfo.setAuthorId(0);
            postInfo.setAuthorName("小编");
            postInfo.setPostType(Const.POST_TYPE_NEWS);
            postInfo.setPostTitle(MapUtils.getString(content.result, "title"));
            postInfo.setPostSummary(MapUtils.getString(content.result, "summary"));
            postInfo.setPostContent(MapUtils.getString(content.result, "content"));
            postInfo.setPostStatus(Const.POST_STATUS_OPEN);
            postInfo.setCommentStatus(Const.COMMENT_STATUS_OPEN);
            postInfo.setCommentCount(0);
            postInfo.setPostDate(new Date());
            postInfo.setPostModified(new Date());
            postInfo.setCreateTime(new Date());

            postInfoMapper.insert(postInfo);
        }

        PostAttrRelate relate = relateMapper.selectRelate(postInfo.getId(), attrId);
        if(null == relate){
            relate = new PostAttrRelate();
            relate.setAttrId(attrId);
            relate.setPostId(postInfo.getId());
            relate.setCreateTime(new Date());

            relateMapper.insert(relate);
        }
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
