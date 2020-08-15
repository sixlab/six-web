package cn.sixlab.six.web.service;

import cn.sixlab.six.web.mapper.SixNotifyConfigMapper;
import cn.sixlab.six.web.models.SixNotifyConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class NotifyService {

    @Autowired
    private SixNotifyConfigMapper configMapper;

    public List<SixNotifyConfig> loadAll() {
        return configMapper.selectAll();
    }

    public SixNotifyConfig select(Integer id) {
        return configMapper.selectByPrimaryKey(id);
    }

    public void delete(Integer id) {
        configMapper.deleteByPrimaryKey(id);
    }

    public void add(SixNotifyConfig data) {
        data.setCreateTime(new Date());
        configMapper.insert(data);
    }

    public void modify(SixNotifyConfig data) {
        SixNotifyConfig old = configMapper.selectByPrimaryKey(data.getId());

        old.setCode(data.getCode());
        old.setType(data.getType());
        old.setRise(data.getRise());
        old.setStatus(data.getStatus());

        configMapper.updateByPrimaryKey(old);
    }
}
