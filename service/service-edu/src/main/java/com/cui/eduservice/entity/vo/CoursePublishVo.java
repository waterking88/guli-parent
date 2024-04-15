package com.cui.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author water
 * @date 2024/4/1
 * @Description
 */
@ApiModel(value = "课程发布信息")
@Data
public class CoursePublishVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    /**
     * 只用于显示 不用修改，所以类型不使用BigDecimal
     */
    private String price;
}
