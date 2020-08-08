package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.models.RankGroup;
import cn.sixlab.six.web.models.RankItem;
import cn.sixlab.six.web.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService service;

    @RequestMapping(value = "/list.html")
    public String list(ModelMap model) {

        List<RankGroup> dataList = service.listAll();

        model.put("dataList", dataList);
        model.put("listSize", dataList.size());

        return "rank/list";
    }

    @RequestMapping(value = "/{groupId}.html")
    public String item(ModelMap model, @PathVariable("groupId") Integer groupId) {

        RankGroup group = service.selectGroup(groupId);
        List<RankItem> itemList = service.selectGroupItems(groupId);

        model.put("group", group);
        model.put("dataList", itemList);

        return "rank/item";
    }

}
