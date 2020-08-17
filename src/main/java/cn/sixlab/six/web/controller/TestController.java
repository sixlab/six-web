package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.service.PostService;
import cn.sixlab.six.web.spider.BaiduRankTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.minesoft.mine.site.core.vo.ResultJson;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private BaiduRankTask task;

    @Autowired
    private PostService service;

    @RequestMapping(value = "/test0")
    public String list(ModelMap model) {
        return "test/list";
    }

    @ResponseBody
    @GetMapping(value = "/test1")
    public ResultJson test1() {

        return ResultJson.successData(service.selectPage(1,8));
    }

}
