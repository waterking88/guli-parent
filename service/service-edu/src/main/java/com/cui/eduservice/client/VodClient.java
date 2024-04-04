package com.cui.eduservice.client;

import com.cui.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author water
 * @date 2024/4/4
 * @Description
 */
@FeignClient("service-vod")
@Component
public interface VodClient {

    @DeleteMapping(value = "/admin/vod/video/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);

    @DeleteMapping(value = "/admin/vod/video/deleteBatch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
