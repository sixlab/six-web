package cn.sixlab.six.web.service;

import cn.sixlab.six.web.mapper.PostAttrMapper;
import cn.sixlab.six.web.mapper.PostAttrRelateMapper;
import cn.sixlab.six.web.mapper.PostInfoMapper;
import cn.sixlab.six.web.models.PostAttr;
import cn.sixlab.six.web.models.PostAttrRelate;
import cn.sixlab.six.web.models.PostInfo;
import cn.sixlab.six.web.utils.Const;
import cn.sixlab.six.web.vo.FullPostInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostAttrMapper attrMapper;

    @Autowired
    private PostInfoMapper postInfoMapper;

    @Autowired
    private PostAttrRelateMapper relateMapper;

    public PostAttr attr(String attrCode){
        return attrMapper.selectByAttr(attrCode);
    }

    public FullPostInfo getPost(String postId, String postType) {
        PostInfo postInfo = postInfoMapper.selectAlias(postId);

        if (postInfo != null && postType.equals(postInfo.getPostType()) && Const.POST_STATUS_OPEN.equals(postInfo.getPostStatus())){
            FullPostInfo fullPostInfo = new FullPostInfo();
            BeanUtils.copyProperties(postInfo, fullPostInfo);

            postAttr(fullPostInfo);

            return fullPostInfo;
        }

        return null;
    }

    public PageInfo<FullPostInfo> postList(String postType, String attrIds, Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<FullPostInfo> infoList;
        if(StringUtils.isBlank(attrIds)){
            infoList = postInfoMapper.selectByType(postType);
        }else{
            infoList = postInfoMapper.selectByAttr(postType, attrIds);
        }

        PageInfo<FullPostInfo> pageInfo = new PageInfo<>(infoList);
        for (FullPostInfo fullPostInfo : infoList) {
            postAttr(fullPostInfo);
        }

        return pageInfo;
    }

    private void postAttr(FullPostInfo fullPostInfo) {
        List<PostAttrRelate> relateList = relateMapper.selectPostAttr(fullPostInfo.getId());

        if (CollectionUtils.isNotEmpty(relateList)) {
            List<PostAttr> attrList = attrMapper.selectPostAttr(relateList);

            for (PostAttr postAttr : attrList) {
                if(Const.ATTR_TYPE_TAG.equals(postAttr.getAttrType())){
                    fullPostInfo.setTagCodes(postAttr.getAttrCode());
                    fullPostInfo.setTagNames(postAttr.getAttrName());
                }else if(Const.ATTR_TYPE_CATEGORY.equals(postAttr.getAttrType())){
                    fullPostInfo.setCategoryCodes(postAttr.getAttrCode());
                    fullPostInfo.setCategoryNames(postAttr.getAttrName());
                }
            }
        }
    }

}
