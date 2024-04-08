package com.cui.msmservice.service;

import java.util.Map;

/**
 * @author water
 * @date 2024/4/8
 * @Description
 */
public interface MsmService {
    /**
     * 发短信
     *
     * @param PhoneNumbers
     * @param templateCode
     * @param param
     * @return
     */
    boolean send(String PhoneNumbers, String templateCode, Map<String, Object> param);
}
