package cn.sixlab.six.web.service;

import cn.sixlab.six.web.mapper.SixDataMapper;
import cn.sixlab.six.web.vo.DataVo;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class DataService {

    @Autowired
    private SixDataMapper dataMapper;


    public Map<String, List> goldData(int type, int time) {
        Date now = new Date();

        List<String> date = new ArrayList<>();
        List<BigDecimal> min = new ArrayList<>();
        List<BigDecimal> max = new ArrayList<>();

        // 将时间置为最开始，即减去time个单位时间
        now = addDate(now, type, -time);
        for (int i = 0; i < time; i++) {
            Date start = now;
            // 递加1单位时间
            now = addDate(now, type, 1);
            Date finish = now;

            DataVo dataVo = dataMapper.selectDuration("jdGold", start, finish);

            date.add(DateFormatUtils.format(start, "yyyy-MM-dd HH:mm:ss"));
            if(null!=dataVo){
                min.add(dataVo.getMin());
                max.add(dataVo.getMax());
            }else{
                min.add(null);
                max.add(null);
            }
        }

        Map<String, List> result = new HashMap<>();

        result.put("date", date);
        result.put("min", min);
        result.put("max", max);

        return result;
    }

    private Date addDate(Date date, int type, int time){
        switch (type){
            case Calendar.HOUR_OF_DAY:
                date = DateUtils.addHours(date, time);
                break;
            case Calendar.DAY_OF_MONTH:
                date = DateUtils.addDays(date, time);
                break;
            case Calendar.MONTH:
                date = DateUtils.addMonths(date, time);
                break;
        }

        return date;
    }
}
