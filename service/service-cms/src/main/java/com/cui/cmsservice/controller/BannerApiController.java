package com.cui.cmsservice.controller;

import com.cui.cmsservice.entity.CrmBanner;
import com.cui.cmsservice.service.CrmBannerService;
import com.cui.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author water
 * @date 2024/4/7
 * @Description
 */
@RestController
@RequestMapping("/cmsservice/banner")
@Api(tags = "网站首页Banner列表")
//@CrossOrigin  网关已配置跨域
public class BannerApiController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取首页banner")
    @GetMapping("getAllBanner")
    public R index() {
        List<CrmBanner> list = bannerService.selectIndexList();
        return R.ok().data("bannerList", list);
    }
}
