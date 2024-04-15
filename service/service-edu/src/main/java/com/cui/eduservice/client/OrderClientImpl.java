package com.cui.eduservice.client;

import com.cui.servicebase.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @author water
 * @date 2024/4/15
 * @Description
 */
@Component
public class OrderClientImpl implements OrderClient {
    @Override
    public boolean isBuyCourse(String memberid, String id) {
        throw new GuliException(20001, "timeout");
    }
}
