package com.cui.cmsservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.cmsservice.entity.CrmBanner;
import com.cui.cmsservice.entity.vo.CrmBannerQuery;
import com.cui.cmsservice.mapper.CrmBannerMapper;
import com.cui.cmsservice.service.CrmBannerService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author cui
 * @since 2024-04-07
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @CacheEvict(value = "banner", allEntries = true)
    @Override
    public void removeBannerById(String id) {
        baseMapper.deleteById(id);
    }

    @CacheEvict(value = "banner", allEntries = true)
    @Override
    public void updateBannerById(CrmBanner banner) {
        baseMapper.updateById(banner);
    }

    @CacheEvict(value = "banner", allEntries = true)
    @Override
    public void saveBanner(CrmBanner banner) {
        baseMapper.insert(banner);
    }

    @Override
    public CrmBanner getBannerById(String id) {
        CrmBanner crmBanner = baseMapper.selectById(id);
        return crmBanner;
    }

    @Override
    public void pageBanner(Page<CrmBanner> pageParam, CrmBannerQuery crmBannerQuery) {
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (crmBannerQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String title = crmBannerQuery.getTitle();
        String imageUrl = crmBannerQuery.getImageUrl();
        String linkUrl = crmBannerQuery.getLinkUrl();
        String begin = crmBannerQuery.getBegin();
        String end = crmBannerQuery.getEnd();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(imageUrl)) {
            queryWrapper.like("image_url", imageUrl);
        }
        if (!StringUtils.isEmpty(linkUrl)) {
            queryWrapper.like("link_url", linkUrl);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Cacheable(value = "banner", key = "'selectIndexList'")
    @Override
    public List<CrmBanner> selectIndexList() {
        List<CrmBanner> list = baseMapper.selectList(new QueryWrapper<CrmBanner>().orderByDesc("sort"));
        return list;
    }
}
