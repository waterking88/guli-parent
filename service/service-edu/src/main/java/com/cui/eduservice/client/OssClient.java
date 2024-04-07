package com.cui.eduservice.client;

import com.cui.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * feign client
 *
 * @author water
 * @date 2024/4/4
 * @Description
 */
@FeignClient("service-oss")
@Component
public interface OssClient {
    @DeleteMapping(value = "/eduoss/file/remove")
    R remove(@RequestParam("url") String url);
}
