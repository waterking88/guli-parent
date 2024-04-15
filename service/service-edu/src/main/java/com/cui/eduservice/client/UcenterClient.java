package com.cui.eduservice.client;


import com.cui.commonutils.eduvo.UcenterMemberEdu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author water
 * @date 2024/4/12
 * @Description
 */
@Component
@FeignClient(name = "service-ucenter", fallback = UcenterClientImpl.class)
public interface UcenterClient {
    /**
     * 根据用户id获取用户信息
     *
     * @param id
     * @return
     */
    @PostMapping("/ucenterservice/ucenter-member/getInfoUc/{id}")
    UcenterMemberEdu getInfo(@PathVariable("id") String id);
}
