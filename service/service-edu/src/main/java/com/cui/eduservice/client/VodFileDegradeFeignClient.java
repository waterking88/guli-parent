package com.cui.eduservice.client;

import com.cui.commonutils.R;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 熔断器实现类
 *
 * @author water
 * @date 2024/4/5
 * @Description
 */
@Component
public class VodFileDegradeFeignClient implements VodClient {
    @Override
    public R removeVideo(String videoId) {
        return R.error().message("time out");
    }

    @Override
    public R removeVideoList(List videoIdList) {
        return R.error().message("time out");
    }
}
