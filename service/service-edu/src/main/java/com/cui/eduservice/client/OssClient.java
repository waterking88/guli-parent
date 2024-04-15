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
@FeignClient(value = "service-oss", fallback = OssClientImpl.class)
@Component
public interface OssClient {
    /**
     * 根据url删除云端文件
     *
     * @param url
     * @return
     */
    @DeleteMapping(value = "/eduoss/file/remove")
    R remove(@RequestParam("url") String url);
}
