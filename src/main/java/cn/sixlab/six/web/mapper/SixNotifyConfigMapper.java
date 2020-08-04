package cn.sixlab.six.web.mapper;

import cn.sixlab.six.web.models.SixNotifyConfig;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SixNotifyConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SixNotifyConfig record);

    int insertSelective(SixNotifyConfig record);

    SixNotifyConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SixNotifyConfig record);

    int updateByPrimaryKey(SixNotifyConfig record);

    @Select(" select * " +
            " from six_notify_config " +
            " where code = #{code} ")
    List<SixNotifyConfig> queryAllByCode(@Param("code") String code);
}