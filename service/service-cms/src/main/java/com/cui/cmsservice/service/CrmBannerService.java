package com.cui.cmsservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.cmsservice.entity.CrmBanner;
import com.cui.cmsservice.entity.vo.CrmBannerQuery;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author cui
 * @since 2024-04-07
 */
public interface CrmBannerService extends IService<CrmBanner> {

    /**
     * 根据id删除
     *
     * @param id
     */
    void removeBannerById(String id);

    /**
     * 根据id修改
     *
     * @param banner
     */
    void updateBannerById(CrmBanner banner);

    /**
     * 保存
     *
     * @param banner
     */
    void saveBanner(CrmBanner banner);

    /**
     * 根据id查询
     *
     * @param id
     * @return
     */
    CrmBanner getBannerById(String id);


    /**
     * 分页查询
     *
     * @param pageParam
     * @param crmBannerQuery
     */
    void pageBanner(Page<CrmBanner> pageParam, CrmBannerQuery crmBannerQuery);

    /**
     * 获取首页banner列表
     *
     * @return
     */
    List<CrmBanner> selectIndexList();
}
