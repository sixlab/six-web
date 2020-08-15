package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.mapper.RankGroupMapper;
import cn.sixlab.six.web.spider.BaiduRankSpiderTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private BaiduRankSpiderTask task;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/list")
    public String list(ModelMap model) {
        return "test/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @GetMapping(value = "/baidu")
    public ResultJson baidu() {

        task.spider.linkTask();

        return ResultJson.success();
    }

}
