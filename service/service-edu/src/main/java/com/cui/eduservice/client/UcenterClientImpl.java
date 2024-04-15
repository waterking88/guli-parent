package com.cui.eduservice.client;

import com.cui.commonutils.eduvo.UcenterMemberEdu;
import com.cui.servicebase.exception.GuliException;
import org.springframework.stereotype.Component;

/**
 * @author water
 * @date 2024/4/12
 * @Description
 */
@Component
public class UcenterClientImpl implements UcenterClient {

    @Override
    public UcenterMemberEdu getInfo(String id) {
        throw new GuliException(20001, "time out");
    }
}
