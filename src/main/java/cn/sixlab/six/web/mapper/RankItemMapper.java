package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.RankItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RankItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RankItem record);

    int insertSelective(RankItem record);

    RankItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RankItem record);

    int updateByPrimaryKey(RankItem record);

    @Delete(" delete from rank_item " +
            " where group_id = #{groupId,jdbcType=INTEGER} ")
    void deleteGroup(@Param("groupId") Integer groupId);

    @Select(" select * " +
            " from rank_item " +
            " where group_id = #{groupId,jdbcType=INTEGER} " +
            " order by item_rank ")
    List<RankItem> selectByGroup(@Param("groupId") Integer groupId);

    void insertGroup(@Param("groupId") Integer groupId, @Param("dataList")List<RankItem> dataList);
}