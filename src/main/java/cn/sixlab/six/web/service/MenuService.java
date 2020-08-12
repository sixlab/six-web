package cn.sixlab.six.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.mapper.MsMenuMapper;
import tech.minesoft.mine.site.core.models.MsMenu;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MsMenuMapper menuMapper;

    public List<MsMenu> positionMenus(String position) {
        return menuMapper.selectPositionMenu(position);
    }

}
