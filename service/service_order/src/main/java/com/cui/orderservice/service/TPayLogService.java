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

    Map createNative(String orderNo);

    void updateOrderStatus(Map<String, String> map);

    Map<String, String> queryPayStatus(String orderNo);
}
