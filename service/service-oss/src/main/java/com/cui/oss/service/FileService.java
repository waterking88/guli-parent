package com.cui.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author water
 * @date 2024/3/27
 * @Description
 */
public interface FileService {
    /**
     * 文件上传至阿里云
     *
     * @param file
     * @return
     */
    String upload(MultipartFile file);
}