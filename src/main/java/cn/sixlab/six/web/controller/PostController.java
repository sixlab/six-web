package cn.sixlab.six.web.controller;

import cn.sixlab.six.web.service.PostService;
import cn.sixlab.six.web.utils.Const;
import cn.sixlab.six.web.vo.FullPostInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import tech.minesoft.mine.site.core.utils.Err;
import tech.minesoft.mine.site.core.vo.MineException;

@Controller
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/{postId}.html")
    public String index(ModelMap model, @PathVariable("postId") String postId) {

        FullPostInfo post = postService.getPost(postId,Const.POST_TYPE_ARCHIVE);

        if(null==post){
            throw MineException.error(Err.ERR_NOT_EXIST, "文章不存在");
        }

        model.put("post", post);

        return "post/post";
    }

}
