package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class IndexController {

    @RequestMapping(value = {"/", "/index"})
    public String index(ModelMap model) {

        return "index";
    }

}
