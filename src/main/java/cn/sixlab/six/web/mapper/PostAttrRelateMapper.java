package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.PostAttrRelate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostAttrRelateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostAttrRelate record);

    int insertSelective(PostAttrRelate record);

    PostAttrRelate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostAttrRelate record);

    int updateByPrimaryKey(PostAttrRelate record);

    List<PostAttrRelate> selectPostAttr(@Param("postId") Integer postId);

    @Select(" select *" +
            " from post_attr_relate" +
            " where post_id = #{postId,jdbcType=INTEGER} " +
            "   and attr_id = #{attrId,jdbcType=INTEGER} " +
            " limit 1 ")
    PostAttrRelate selectRelate(@Param("postId")Integer postId, @Param("attrId")Integer attrId);
}