package cn.sixlab.six.web.service;

import cn.sixlab.six.web.mapper.RankGroupMapper;
import cn.sixlab.six.web.mapper.RankItemMapper;
import cn.sixlab.six.web.models.RankGroup;
import cn.sixlab.six.web.models.RankItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankService {

    @Autowired
    private RankGroupMapper groupMapper;

    @Autowired
    private RankItemMapper itemMapper;

    public List<RankGroup> listAll() {

        List<RankGroup> rankGroups = groupMapper.selectAll();

        return rankGroups;
    }

    public RankGroup selectGroup(Integer groupId) {

        return groupMapper.selectByPrimaryKey(groupId);
    }

    public List<RankItem> selectGroupItems(Integer groupId) {

        return itemMapper.selectByGroup(groupId);
    }
}
