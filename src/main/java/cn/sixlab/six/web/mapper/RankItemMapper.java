package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.RankItem;

public interface RankItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RankItem record);

    int insertSelective(RankItem record);

    RankItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankItem record);

    int updateByPrimaryKey(RankItem record);
}