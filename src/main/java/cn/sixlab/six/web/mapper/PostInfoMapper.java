package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.PostInfo;
import cn.sixlab.six.web.vo.FullPostInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PostInfo record);

    int insertSelective(PostInfo record);

    PostInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PostInfo record);

    int updateByPrimaryKeyWithBLOBs(PostInfo record);

    int updateByPrimaryKey(PostInfo record);

    @Select(" select * " +
            " from post_info " +
            " where alias_name = #{aliasName,jdbcType=VARCHAR} " +
            " or id = #{aliasName} " +
            " limit 1 ")
    PostInfo selectAlias(@Param("aliasName") String aliasName);

    List<FullPostInfo> selectByType(@Param("postType") String postType);

    List<FullPostInfo> selectByAttr(@Param("postType") String postType,
                                    @Param("attrIds") String attrIds);
}