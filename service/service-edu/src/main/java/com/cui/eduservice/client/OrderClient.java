package com.cui.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author water
 * @date 2024/4/15
 * @Description
 */
@Component
@FeignClient(value = "service-order", fallback = OrderClientImpl.class)
public interface OrderClient {
    /**
     * 查询订单信息，用户是否已经购买该课程
     *
     * @param memberid
     * @param id
     * @return
     */
    @GetMapping("/orderservice/t-order/isBuyCourse/{memberid}/{id}")
    boolean isBuyCourse(@PathVariable("memberid") String memberid,
                        @PathVariable("id") String id);
}
