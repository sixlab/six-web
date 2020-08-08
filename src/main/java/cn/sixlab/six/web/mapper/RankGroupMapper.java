package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.RankGroup;

public interface RankGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RankGroup record);

    int insertSelective(RankGroup record);

    RankGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankGroup record);

    int updateByPrimaryKey(RankGroup record);
}