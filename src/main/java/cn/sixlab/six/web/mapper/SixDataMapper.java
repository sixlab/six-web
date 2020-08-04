package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.SixData;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface SixDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SixData record);

    int insertSelective(SixData record);

    SixData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SixData record);

    int updateByPrimaryKey(SixData record);

    @Select(" select * " +
            " from six_data " +
            " where code = #{code} " +
            " order by create_time desc limit 1")
    SixData selectFirstByCode(@Param("code") String code);
}