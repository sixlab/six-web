package cn.sixlab.six.web.service;

import cn.sixlab.six.web.mapper.PostInfoMapper;
import cn.sixlab.six.web.models.PostInfo;
import cn.sixlab.six.web.utils.Const;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.minesoft.minesite.core.utils.Err;
import tech.minesoft.minesite.core.vo.MineException;

@Service
public class PostService {

    @Autowired
    private PostInfoMapper postInfoMapper;

    public PostInfo getPost(String postId) {
        PostInfo postInfo = postInfoMapper.selectAlias(postId);
        if(null == postInfo && StringUtils.isNumeric(postId)){
            postInfo = postInfoMapper.selectByPrimaryKey(Integer.valueOf(postId));
        }

        if (postInfo != null && Const.POST_STATUS_OPEN.equals(postInfo.getPostStatus())){
            return postInfo;
        }else{
            throw MineException.error(Err.ERR_NOT_EXIST, "文章不存在");
        }
    }

    public void page(Integer pageNo) {

    }
}
