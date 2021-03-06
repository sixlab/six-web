package cn.sixlab.six.web.config;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FreemarkerConfig {

    @Autowired
    public void setSharedVariable(Configuration configuration) {
        List<String> includes = new ArrayList<>();
        includes.add("frame/frame.ftl");

        configuration.setAutoIncludes(includes);
    }

}