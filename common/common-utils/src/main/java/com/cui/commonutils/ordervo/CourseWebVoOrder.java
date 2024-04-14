package com.cui.commonutils.ordervo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author water
 * @date 2024/4/14
 * @Description
 */
@ApiModel(value = "课程信息", description = "创建订单需要的课程信息vo")
@Data
public class CourseWebVoOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    @ApiModelProperty(value = "课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private BigDecimal price;
    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
}
