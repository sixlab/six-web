package cn.sixlab.six.web.service;

import cn.sixlab.six.web.mapper.PostInfoMapper;
import cn.sixlab.six.web.models.PostInfo;
import cn.sixlab.six.web.utils.Const;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostInfoMapper postInfoMapper;

    public PostInfo getPost(String postId, String postType) {
        PostInfo postInfo = postInfoMapper.selectAlias(postId);
        if(null == postInfo && StringUtils.isNumeric(postId)){
            postInfo = postInfoMapper.selectByPrimaryKey(Integer.valueOf(postId));
        }

        if (postInfo != null && postType.equals(postInfo.getPostType()) && Const.POST_STATUS_OPEN.equals(postInfo.getPostStatus())){
            return postInfo;
        }

        return null;
    }

    public void page(Integer pageNo) {

    }
}
