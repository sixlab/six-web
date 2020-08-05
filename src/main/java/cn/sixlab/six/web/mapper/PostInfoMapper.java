package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.PostInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
            " limit 1 ")
    PostInfo selectAlias(@Param("aliasName") String aliasName);

}