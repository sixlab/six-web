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

import java.util.Date;
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

    public FullPostInfo getPost(String postId) {
        PostInfo postInfo = postInfoMapper.selectAlias(postId);

        if (postInfo != null && Const.POST_STATUS_OPEN.equals(postInfo.getPostStatus())){
            return copyPost(postInfo);
        }

        return null;
    }

    public FullPostInfo getPostById(Integer postId) {
        PostInfo postInfo = postInfoMapper.selectByPrimaryKey(postId);

        if (postInfo != null){
            return copyPost(postInfo);
        }

        return null;
    }

    public FullPostInfo copyPost(PostInfo postInfo){
        FullPostInfo fullPostInfo = new FullPostInfo();
        BeanUtils.copyProperties(postInfo, fullPostInfo);

        postAttr(fullPostInfo);

        return fullPostInfo;
    }

    public PageInfo<PostInfo> selectPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        List<PostInfo> infoList = postInfoMapper.selectAll();

        return new PageInfo<>(infoList);
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

    public void delete(Integer id) {
        postInfoMapper.deleteByPrimaryKey(id);
    }

    public void publish(Integer id) {
        PostInfo postInfo = new PostInfo();
        postInfo.setId(id);
        postInfo.setPostStatus(Const.POST_STATUS_OPEN);
        postInfoMapper.updateByPrimaryKeySelective(postInfo);
    }

    public void add(FullPostInfo data) {
        data.setCommentCount(0);
        data.setCommentStatus(Const.COMMENT_STATUS_OPEN);
        data.setPostStatus(Const.POST_STATUS_EDIT);

        if(null==data.getPostDate()){
            data.setPostDate(new Date());
            data.setPostModified(new Date());
        }else{
            data.setPostModified(data.getPostDate());
        }
        data.setCreateTime(new Date());

        postInfoMapper.insert(data);

        if(StringUtils.isNotBlank(data.getAliasName())){
            data.setAliasName(data.getPostType()+data.getId());

            postInfoMapper.updateByPrimaryKey(data);
        }
    }

    public void modify(FullPostInfo data) {
        PostInfo old = postInfoMapper.selectByPrimaryKey(data.getId());

        old.setPostStatus(Const.POST_STATUS_EDIT);
        old.setPostTitle(data.getPostTitle());
        old.setAliasName(data.getAliasName());
        old.setAuthorName(data.getAuthorName());
        old.setPostType(data.getPostType());
        old.setPostSummary(data.getPostSummary());
        old.setPostContent(data.getPostContent());

        if(null==data.getPostDate()){
            old.setPostDate(new Date());
            old.setPostModified(new Date());
        }else{
            old.setPostDate(data.getPostDate());
            old.setPostModified(data.getPostDate());
        }

        postInfoMapper.updateByPrimaryKeyWithBLOBs(old);
    }
}
