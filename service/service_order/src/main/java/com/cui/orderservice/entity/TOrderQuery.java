package com.cui.orderservice.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author water
 * @date 2024/4/20
 * @Description
 */
@ApiModel(value = "Order查询对象", description = "订单查询对象封装")
@Data
public class TOrderQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String teacherName;

    @ApiModelProperty(value = "课程名称")
    private String courseTitle;

    @ApiModelProperty(value = "会员昵称")
    private String nickname;

    @ApiModelProperty(value = "会员手机")
    private String mobile;

    @ApiModelProperty(value = "查询开始时间", example = "2019-01-01 10:10:10")
    /**
     * 注意，这里使用的是String类型，前端传过来的数据无需进行类型转换
     */
    private String begin;
    @ApiModelProperty(value = "查询结束时间", example = "2019-12-01 10:10:10")
    private String end;
}
