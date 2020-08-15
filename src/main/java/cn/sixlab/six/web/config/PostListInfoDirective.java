package cn.sixlab.six.web.config;

import cn.sixlab.six.web.service.PostService;
import cn.sixlab.six.web.vo.FullPostInfo;
import com.github.pagehelper.PageInfo;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class PostListInfoDirective implements TemplateDirectiveModel {

    @Autowired
    public void setSharedVariable(Configuration configuration) {
        configuration.setSharedVariable("PostListInfo", this);
    }

    @Autowired
    private PostService postService;

    @Override
    public void execute(Environment env, Map map, TemplateModel[] templateModels, TemplateDirectiveBody body) throws TemplateException, IOException {
        Map<String, String> temp = new HashMap<>();
        for (Object key : map.keySet()) {
            String val = MapUtils.getString(map, key);
            temp.put(key.toString(), val);
        }

        String postType = MapUtils.getString(temp, "postType");

        Integer pageNum = MapUtils.getInteger(temp, "pageNum", 1);
        Integer pageSize = MapUtils.getInteger(temp, "pageSize", 10);

        String attrIds = MapUtils.getString(temp, "attrIds");

        PageInfo<FullPostInfo> postList = postService.postList(postType, attrIds, pageNum, pageSize);

        env.setVariable("postList",new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(postList));

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
