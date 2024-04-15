package com.cui.eduservice.client;

import com.cui.commonutils.R;
import org.springframework.stereotype.Component;

/**
 * @author water
 * @date 2024/4/15
 * @Description
 */
@Component
public class OssClientImpl implements OssClient {

    @Override
    public R remove(String url) {
        return R.error().message("time out");
    }
}
