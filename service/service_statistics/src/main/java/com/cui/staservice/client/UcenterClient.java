package com.cui.staservice.client;

import com.cui.commonutils.R;
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
@FeignClient("service-ucenter")
public interface UcenterClient {
    /**
     * 根据日期获取注册人数
     *
     * @param day
     * @return
     */
    @GetMapping(value = "/ucenterservice/ucenter-member/countregister/{day}")
    public R registerCount(@PathVariable("day") String day);
}
