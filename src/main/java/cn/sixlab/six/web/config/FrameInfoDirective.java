package cn.sixlab.six.web.config;


import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tech.minesoft.mine.site.core.service.MsMetaService;

import java.io.IOException;
import java.util.Map;

@Component
public class FrameInfoDirective implements TemplateDirectiveModel {

    @Autowired
    public void setSharedVariable(Configuration configuration) {
        configuration.setSharedVariable("FrameInfo", this);
    }

    @Autowired
    private MsMetaService metaService;

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        //获取网站配置信息,
        Map<String,String> siteMap = metaService.siteInfo();

        //将网站配置信息添加到全局变量
        env.setVariable("siteInfo",new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(siteMap));


//        String uri = MapUtils.getString(params, "uri", "/api/index");
//
//        if (uri.endsWith("/")) {
//            uri += "index";
//        }
//
//        List messages = new ArrayList<>();
//
//        env.setVariable("path", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(uri));
//        env.setVariable("messages", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(messages));


        if (body != null) {
            body.render(env.getOut());
        }
    }
}
