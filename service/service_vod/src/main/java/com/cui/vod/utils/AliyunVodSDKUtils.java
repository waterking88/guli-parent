package com.cui.vod.utils;

import com.aliyun.oss.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @author water
 * @date 2024/4/4
 * @Description
 */
public class AliyunVodSDKUtils {
    public static DefaultAcsClient initVodClient(String accessKeyId, String
            accessKeySecret) throws ClientException {
        // 点播服务接入区域
        String regionId = "cn-shanghai";
        DefaultProfile profile = DefaultProfile.getProfile(regionId, accessKeyId,
                accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
