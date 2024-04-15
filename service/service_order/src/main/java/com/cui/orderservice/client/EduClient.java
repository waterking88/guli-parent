package com.cui.orderservice.client;

import com.cui.commonutils.ordervo.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author water
 * @date 2024/4/14
 * @Description
 */
@Component
@FeignClient("service-edu")
public interface EduClient {
    /**
     * 根据课程id查询课程信息,用于支付页面
     *
     * @param courseId
     * @return
     */
    @GetMapping("/eduservice/coursefront/getVo/{courseId}")
    CourseWebVoOrder getCourseInfoDto(@PathVariable("courseId") String courseId);
}
