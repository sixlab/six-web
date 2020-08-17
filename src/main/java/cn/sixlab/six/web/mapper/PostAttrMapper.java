package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.PostAttr;
import cn.sixlab.six.web.models.PostAttrRelate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostAttrMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostAttr record);

    int insertSelective(PostAttr record);

    PostAttr selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostAttr record);

    int updateByPrimaryKey(PostAttr record);

    List<PostAttr> selectPostAttr(@Param("relateList") List<PostAttrRelate> relateList);

    @Select(" select * " +
            " from post_attr " +
            " where attr_code = #{attrCode,jdbcType=VARCHAR}" +
            " or id = #{attrCode,jdbcType=VARCHAR} " +
            " limit 1 ")
    PostAttr selectByAttr(@Param("attrCode") String attrCode);

    @Select(" select * " +
            " from post_attr " +
            " where attr_type = 'tag' " +
            " and attr_name = #{attrName,jdbcType=VARCHAR} " +
            " limit 1 ")
    PostAttr selectTag(@Param("attrName") String attrName);

    @Select(" select * " +
            " from post_attr " +
            " where attr_type = #{attrType,jdbcType=VARCHAR} " +
            " and attr_name in ${tagNames,jdbcType=VARCHAR} ")
    List<PostAttr> selectAttrs(@Param("attrType") String attrType, @Param("tagNames") String tagNames);
}