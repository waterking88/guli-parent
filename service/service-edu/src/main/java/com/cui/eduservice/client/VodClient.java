package com.cui.eduservice.client;

import com.cui.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * feign client
 *
 * @author water
 * @date 2024/4/4
 * @Description
 */
@FeignClient(name = "service-vod", fallback = VodFileDegradeFeignClient.class)
@Component
public interface VodClient {

    /**
     * 根据视频id删除云端视频
     *
     * @param videoId
     * @return
     */
    @DeleteMapping(value = "/admin/vod/video/{videoId}")
    public R removeVideo(@PathVariable("videoId") String videoId);

    /**
     * 批量删除云端视频
     *
     * @param videoIdList
     * @return
     */
    @DeleteMapping(value = "/admin/vod/video/deleteBatch")
    public R removeVideoList(@RequestParam("videoIdList") List<String> videoIdList);
}
