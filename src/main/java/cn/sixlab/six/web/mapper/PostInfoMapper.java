package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.PostInfo;

public interface PostInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKeyWithBLOBs(PostInfo record);

    int updateByPrimaryKey(PostInfo record);
}