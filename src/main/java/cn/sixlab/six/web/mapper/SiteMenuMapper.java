package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.SiteMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SiteMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SiteMenu record);

    int insertSelective(SiteMenu record);

    SiteMenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SiteMenu record);

    int updateByPrimaryKey(SiteMenu record);

    @Select(" select menu_position " +
            " from site_menu" +
            " group by menu_position ")
    List<String> selectPositions();

    @Select(" select * " +
            " from site_menu " +
            " where menu_position = #{menuPosition,jdbcType=VARCHAR} " +
            " order by menu_order ")
    List<SiteMenu> selectPositionMenu(@Param("menuPosition")String menuPosition);

    @Delete(" delete from site_menu " +
            " where menu_position = #{menuPosition,jdbcType=VARCHAR} ")
    void deletePosition(@Param("menuPosition")String menuPosition);

    void insertPositionMenu(@Param("menuPosition")String menuPosition,
                            @Param("menuList")List<SiteMenu> menuList);
}