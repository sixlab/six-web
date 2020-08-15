package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.minesoft.mine.site.core.vo.ResultJson;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/list")
    public String list(ModelMap model) {

        return "data/list";
    }

    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {

        return ResultJson.success();
    }
}
