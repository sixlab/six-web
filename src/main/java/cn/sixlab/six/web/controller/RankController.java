package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.mapper.PostAttrMapper;
import cn.sixlab.six.web.models.PostAttr;
import cn.sixlab.six.web.models.RankGroup;
import cn.sixlab.six.web.models.RankItem;
import cn.sixlab.six.web.service.RankService;
import cn.sixlab.six.web.utils.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private RankService service;

    @Autowired
    private PostAttrMapper attrMapper;

    @RequestMapping(value = "/list.html")
    public String list(ModelMap model) {

        List<RankGroup> dataList = service.listAll();

        model.put("dataList", dataList);
        model.put("listSize", dataList.size());

        return "rank/list";
    }

    @RequestMapping(value = "/news/{attrName}.html")
    public String news(ModelMap model, @PathVariable("attrName") String attrName) {

        return news(model, attrName, 1);
    }

    @RequestMapping(value = "/news/{attrName}/{pageNum}.html")
    public String news(ModelMap model, @PathVariable("attrName") String attrName, @PathVariable("pageNum") Integer pageNum) {
        log.info("/rank/news:" + attrName);

        PostAttr attr = attrMapper.selectTag(attrName);

        if(null!=attr){
            model.put("attrId", attr.getId());
            model.put("attrName", attr.getAttrName());
            model.put("attrType", attr.getAttrType());
        }
        model.put("pageNum", pageNum);
        model.put("postType", Const.POST_TYPE_NEWS);
        model.put("title", attrName + "相关资讯");
        model.put("keywords", attrName+",资讯,相关资讯,新闻,相关新闻");
        model.put("description", attrName+"相关资讯。");

        return "post/list";
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
