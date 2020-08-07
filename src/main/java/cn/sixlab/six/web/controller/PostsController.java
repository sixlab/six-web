package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.service.PostService;
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

    @RequestMapping(value = "/posts/")
    public String posts(ModelMap model) {

        return postsPager(model, 1);
    }

    @RequestMapping(value = "/posts/{pageNo}.html")
    public String postsPager(ModelMap model, @PathVariable("pageNo") Integer pageNo) {

        postService.page(pageNo);

        model.put("list", null);

        return "list";
    }

    @RequestMapping(value = "/category/{category}/")
    public String category(ModelMap model,@PathVariable("category") String category) {

        return categoryPager(model, category,1);
    }

    @RequestMapping(value = "/category/{category}/{pageNo}.html")
    public String categoryPager(ModelMap model,@PathVariable("category") String category, @PathVariable("pageNo") Integer pageNo) {


        return "category";
    }

    @RequestMapping(value = "/tag/{tag}/")
    public String tag(ModelMap model,@PathVariable("tag") String tag) {

        return tagPager(model, tag,1);
    }

    @RequestMapping(value = "/tag/{tag}/{pageNo}.html")
    public String tagPager(ModelMap model,@PathVariable("tag") String tag, @PathVariable("pageNo") Integer pageNo) {


        return "category";
    }

}
