package com.cui.vod.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author water
 * @date 2024/4/4
 * @Description
 */
public interface VideoService {
    /**
     * 上传视频
     *
     * @param file
     * @return
     */
    String uploadVideo(MultipartFile file);

    /**
     * 根据视频id删除视频
     *
     * @param videoId
     */
    void removeVideo(String videoId);
}
