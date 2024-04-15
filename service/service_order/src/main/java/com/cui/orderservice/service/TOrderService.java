package com.cui.orderservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.orderservice.entity.TOrder;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author cui
 * @since 2024-04-14
 */
public interface TOrderService extends IService<TOrder> {

    /**
     * 保存订单信息
     *
     * @param courseId
     * @param memberId
     * @return
     */
    String saveOrder(String courseId, String memberId);
}
