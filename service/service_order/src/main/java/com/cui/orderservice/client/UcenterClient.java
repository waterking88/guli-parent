package com.cui.orderservice.client;

import com.cui.commonutils.eduvo.UcenterMemberEdu;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author water
 * @date 2024/4/14
 * @Description
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    /**
     * //根据课程id查询课程信息
     *
     * @param id
     * @return
     */
    @PostMapping("/ucenterservice/ucenter-member/getInfoUc/{id}")
    UcenterMemberEdu getInfo(@PathVariable("id") String id);
}
