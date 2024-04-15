package com.cui.orderservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.orderservice.entity.TPayLog;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author cui
 * @since 2024-04-14
 */
public interface TPayLogService extends IService<TPayLog> {

    /**
     * 获取微信支付二维码信息
     *
     * @param orderNo
     * @return
     */
    Map createNative(String orderNo);

    /**
     * 支付后更新订单状态
     *
     * @param map
     */
    void updateOrderStatus(Map<String, String> map);

    /**
     * 查询订单状态，是否已经支付完成
     *
     * @param orderNo
     * @return
     */
    Map<String, String> queryPayStatus(String orderNo);
}
