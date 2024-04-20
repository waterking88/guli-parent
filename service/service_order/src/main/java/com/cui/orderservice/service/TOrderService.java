package com.cui.orderservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.orderservice.entity.TOrder;
import com.cui.orderservice.entity.TOrderQuery;

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

    /**
     * 分页查询订单
     *
     * @param pageParam
     * @param orderQuery
     */
    void pageQuery(Page<TOrder> pageParam, TOrderQuery orderQuery);
}
