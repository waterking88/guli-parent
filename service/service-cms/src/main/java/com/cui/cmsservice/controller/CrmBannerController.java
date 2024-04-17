package com.cui.cmsservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.cmsservice.entity.CrmBanner;
import com.cui.cmsservice.entity.vo.CrmBannerQuery;
import com.cui.cmsservice.service.CrmBannerService;
import com.cui.commonutils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-04-07
 */
//@CrossOrigin  网关已配置跨域
@RestController
@RequestMapping("/cmsservice/crm-banner")
@Api(tags = "banner管理")
public class CrmBannerController {
    @Autowired
    private CrmBannerService bannerService;

    @ApiOperation(value = "获取Banner分页列表")
    @PostMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "crmBannerQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CrmBannerQuery crmBannerQuery) {
        System.out.println(crmBannerQuery);
        Page<CrmBanner> pageParam = new Page<>(page, limit);
        bannerService.pageBanner(pageParam, crmBannerQuery);
        return R.ok().data("rows", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    @ApiOperation(value = "获取Banner")
    @GetMapping("get/{id}")
    public R get(@PathVariable String id) {
        CrmBanner banner = bannerService.getBannerById(id);
        return R.ok().data("item", banner);
    }

    @ApiOperation(value = "新增Banner")
    @PostMapping("save")
    public R save(@RequestBody CrmBanner banner) {
        bannerService.saveBanner(banner);
        return R.ok();
    }

    @ApiOperation(value = "修改Banner")
    @PutMapping("update")
    public R updateById(@RequestBody CrmBanner banner) {
        bannerService.updateBannerById(banner);
        return R.ok();
    }

    @ApiOperation(value = "删除Banner")
    @DeleteMapping("remove/{id}")
    public R remove(@PathVariable String id) {
        bannerService.removeBannerById(id);
        return R.ok();
    }
}

