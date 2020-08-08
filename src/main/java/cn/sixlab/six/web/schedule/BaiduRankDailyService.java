//package cn.sixlab.six.web.schedule;
//
//import cn.sixlab.six.web.mapper.RankGroupMapper;
//import cn.sixlab.six.web.mapper.RankItemMapper;
//import org.apache.commons.collections4.MapUtils;
//import org.apache.commons.lang3.time.DateFormatUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import tech.minesoft.minesite.core.api.Job;
//import tech.minesoft.minesite.core.utils.HttpUtils;
//import tech.minesoft.minesite.core.utils.JsonUtils;
//import tech.minesoft.minesite.core.vo.ResultJson;
//import tech.minesoft.minesite.ext.service.DingTalkService;
//
//import java.math.BigDecimal;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Random;
//
//@Service
//@Transactional
//public class BaiduRankDailyService implements Job {
//    private static String CODE = "BaiduRankDaily";
//
//    @Autowired
//    private RankGroupMapper groupMapper ;
//
//    @Autowired
//    private RankItemMapper itemMapper;
//
//    @Override
//    public void run() {
//        String apiUrl = "https://ms.jr.jd.com/gw/generic/hj/h5/m/latestPrice";
//
//        Map<String, String> header = new HashMap<>();
//        header.put("host", "ms.jr.jd.com");
//        header.put("referer", "https://m.jdjygold.com/finance-gold/msjgold/homepage?orderSource=7");
//
//        ResultJson resultJson = HttpUtils.sendGet(apiUrl, null, header);
//        if (resultJson.isSuccess()) {
//            Map map = JsonUtils.toBean(resultJson.getMessage(), Map.class);
//
//            Map resultData = MapUtils.getMap(map, "resultData");
//            Map datas = MapUtils.getMap(resultData, "datas");
//
//            Date time = new Date(MapUtils.getLong(datas, "time"));
//            String timeStr = DateFormatUtils.format(time, "yyyy-MM-dd HH:mm:ss");
//            BigDecimal price = new BigDecimal(MapUtils.getString(datas, "price"));
//
//            StringBuilder builder = new StringBuilder();
//            builder.append("\n价格：");
//            builder.append(price.toPlainString());
//            builder.append("\n日期：");
//            builder.append(timeStr);
//            builder.append("\n访问：https://datastrend.com/api/gold/index\n");
//
//            dingTalkService.sendText(builder.toString());
//        }
//    }
//
//}
