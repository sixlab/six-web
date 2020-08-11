package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.RankGroup;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RankGroupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RankGroup record);

    int insertSelective(RankGroup record);

    RankGroup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankGroup record);

    int updateByPrimaryKey(RankGroup record);

    @Select(" select *" +
            " from rank_group " +
            " order by group_order ")
    List<RankGroup> selectAll();

    @Select(" select *" +
            " from rank_group " +
            " where group_type = 'item' " +
            " order by group_order ")
    List<RankGroup> selectItems();

}