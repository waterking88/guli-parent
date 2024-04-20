package com.cui.staservice.schedule;

import com.cui.staservice.service.StatisticsDailyService;
import com.cui.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author water
 * @date 2024/4/15
 * @Description
 */
@Component
public class ScheduledTask {
    @Autowired
    private StatisticsDailyService dailyService;
    /**
     * * 每天凌晨1点执行定时
     * cron表达式要6位，不能7位会报错
     */

    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.createStatisticsByDay(day);
    }

}
