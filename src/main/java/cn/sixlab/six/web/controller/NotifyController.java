package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.models.SixNotifyConfig;
import cn.sixlab.six.web.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.vo.ResultJson;

import java.util.List;

@Controller
@RequestMapping("/notify")
public class NotifyController {

    @Autowired
    private NotifyService notifyService;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/list")
    public String list(ModelMap map) {
        List<SixNotifyConfig> dataList = notifyService.loadAll();
        map.put("dataList", dataList);
        return "notify/list";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "notify/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap map, @PathVariable Integer id) {
        SixNotifyConfig data = notifyService.select(id);
        map.put("data", data);
        return "notify/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable Integer id) {

        notifyService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(SixNotifyConfig data) {

        if(null==data.getId()){
            notifyService.add(data);
        }else{
            notifyService.modify(data);
        }

        return ResultJson.success();
    }
}
