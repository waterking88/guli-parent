package com.cui.cmsservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author water
 * @date 2024/4/7
 * @Description
 */
@ApiModel(value = "CrmBanner查询对象", description = "幻灯片查询对象封装")
@Data
public class CrmBannerQuery {
    @ApiModelProperty(value = "标题模糊查询")
    private String title;

    @ApiModelProperty(value = "图片地址模糊查询")
    private String imageUrl;

    @ApiModelProperty(value = "链接地址模糊查询")
    private String linkUrl;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    /**
     * 注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
     */
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
