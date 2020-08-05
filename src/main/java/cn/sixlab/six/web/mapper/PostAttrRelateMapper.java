package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.PostAttrRelate;

public interface PostAttrRelateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostAttrRelate record);

    int insertSelective(PostAttrRelate record);

    PostAttrRelate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostAttrRelate record);

    int updateByPrimaryKey(PostAttrRelate record);
}