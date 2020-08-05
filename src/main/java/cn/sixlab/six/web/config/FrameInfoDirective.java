package cn.sixlab.six.web.config;


import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.*;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class FrameInfoDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String uri = MapUtils.getString(params, "uri", "/api/index");

        if (uri.endsWith("/")) {
            uri += "index";
        }

        List messages = new ArrayList<>();

        env.setVariable("path", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(uri));
        env.setVariable("messages", new BeansWrapperBuilder(Configuration.VERSION_2_3_29).build().wrap(messages));

        if (body != null) {
            body.render(env.getOut());
        }
    }
}
