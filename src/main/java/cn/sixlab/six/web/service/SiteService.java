package cn.sixlab.six.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.minesoft.mine.site.core.mapper.MsMetaMapper;
import tech.minesoft.mine.site.core.models.MsMeta;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SiteService {

    @Autowired
    private MsMetaMapper metaMapper;

    public Map<String, String> siteInfo() {
        Map<String,String> siteMap = new HashMap<>();

        List<MsMeta> metaList = metaMapper.selectGroup("six-siteInfo");
        for (MsMeta meta : metaList) {
            siteMap.put(meta.getMetaKey(), meta.getMetaVal());
        }

        siteMap.put("copyrightYear", Calendar.getInstance().get(Calendar.YEAR)+"");

        return siteMap;
    }
    
}
