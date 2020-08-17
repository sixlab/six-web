package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.models.PostInfo;
import cn.sixlab.six.web.service.PostService;
import cn.sixlab.six.web.vo.FullPostInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tech.minesoft.mine.site.core.controller.BaseController;
import tech.minesoft.mine.site.core.vo.ResultJson;

@Controller
@RequestMapping("/six/post")
public class SixPostController extends BaseController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasAuthority('admin')")
    @RequestMapping(value = "/list")
    public String list(ModelMap map,
                       @RequestParam(defaultValue = "1") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize) {

        PageInfo<PostInfo> postList = postService.selectPage(pageNum, pageSize);

        map.put("pageInfo", postList);
        return "post/manageList";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/add")
    public String add(ModelMap map) {
        return "post/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @GetMapping(value = "/edit/{id}")
    public String edit(ModelMap map, @PathVariable("id") Integer id) {
        FullPostInfo data = postService.getPostById(id);
        map.put("data", data);
        return "post/edit";
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/delete/{id}")
    public ResultJson delete(@PathVariable("id") Integer id) {

        postService.delete(id);

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/submit")
    public ResultJson submit(FullPostInfo data) {

        if(null==data.getId()){
            postService.add(data);
        }else{
            postService.modify(data);
        }

        return ResultJson.success();
    }

    @PreAuthorize("hasAuthority('admin')")
    @ResponseBody
    @PostMapping(value = "/publish/{id}")
    public ResultJson publish(@PathVariable("id") Integer id) {

        postService.publish(id);

        return ResultJson.success();
    }
}
