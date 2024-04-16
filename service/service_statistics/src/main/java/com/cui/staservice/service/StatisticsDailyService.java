package com.cui.staservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.staservice.entity.StatisticsDaily;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author cui
 * @since 2024-04-15
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {


    void createStatisticsByDay(String day);

    Map<String, Object> getChartData(String begin, String end, String type);
}
