package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.models.PostAttr;
import cn.sixlab.six.web.service.PostService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PostsController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/posts.html")
    public String posts(ModelMap model) {

        return attrWithPage(model, "", 1);
    }

    @RequestMapping(value = "/posts/{pageNum}.html")
    public String postsPager(ModelMap model, @PathVariable("pageNum") Integer pageNum) {

        return attrWithPage(model, "", pageNum);
    }

    @RequestMapping(value = "/category/{category}.html")
    public String category(ModelMap model,@PathVariable("category") String category) {

        return attrWithPage(model, category, 1);
    }

    @RequestMapping(value = "/category/{category}/{pageNum}.html")
    public String categoryPager(ModelMap model,@PathVariable("category") String category, @PathVariable("pageNum") Integer pageNum) {

        return attrWithPage(model, category, pageNum);
    }

    @RequestMapping(value = "/tag/{tag}.html")
    public String tag(ModelMap model,@PathVariable("tag") String tag) {

        return attrWithPage(model, tag, 1);
    }

    @RequestMapping(value = "/tag/{tag}/{pageNum}.html")
    public String tagPager(ModelMap model,@PathVariable("tag") String tag, @PathVariable("pageNum") Integer pageNum) {

        return attrWithPage(model, tag, pageNum);
    }

    public String attrWithPage(ModelMap model, String attrCode, Integer pageNum){
        if(StringUtils.isNotBlank(attrCode)){
            PostAttr attr = postService.attr(attrCode);

            if(null != attr){
                model.put("attrId", attr.getId());
                model.put("attrName", attr.getAttrName());
                model.put("attrType", attr.getAttrType());
            }
        }
        model.put("pageNum", pageNum);

        return "post/list";
    }
}
