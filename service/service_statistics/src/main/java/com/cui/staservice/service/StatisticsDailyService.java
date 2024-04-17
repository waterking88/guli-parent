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


    /**
     * 根据日期创建数据
     *
     * @param day
     */
    void createStatisticsByDay(String day);

    /**
     * 获取图标数据
     *
     * @param begin
     * @param end
     * @param type
     * @return
     */
    Map<String, Object> getChartData(String begin, String end, String type);
}
