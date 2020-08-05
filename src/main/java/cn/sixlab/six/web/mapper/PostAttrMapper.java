package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.PostAttr;

public interface PostAttrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostAttr record);

    int insertSelective(PostAttr record);

    PostAttr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostAttr record);

    int updateByPrimaryKey(PostAttr record);
}