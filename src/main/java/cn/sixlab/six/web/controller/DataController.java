package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.vo.ResultJson;

@Controller
@RequestMapping("/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/gold")
    public String gold(ModelMap model) {

        return "data/gold";
    }

    @ResponseBody
    @PostMapping(value = "/gold/data/{type}")
    public ResultJson delete(@PathVariable Integer type, @RequestParam(defaultValue = "10") Integer times) {
        // 11 Calendar.HOUR_OF_DAY
        // 5  Calendar.DAY_OF_MONTH
        // 2  Calendar.MONTH
        return ResultJson.successData(dataService.goldData(type, times));
    }
}
