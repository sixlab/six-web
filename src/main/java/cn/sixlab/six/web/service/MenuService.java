package cn.sixlab.six.web.service;

import cn.sixlab.six.web.mapper.SiteMenuMapper;
import cn.sixlab.six.web.models.SiteMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private SiteMenuMapper menuMapper;

    public List<SiteMenu> positionMenus(String position) {
        return menuMapper.selectPositionMenu(position);
    }

}
