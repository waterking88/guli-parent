package com.cui.eduservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.frontvo.CourseWebVo;
import com.cui.eduservice.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author cui
 * @since 2024-03-29
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    /**
     * 获取课程最终发布信息
     *
     * @param id
     * @return
     */
    CoursePublishVo getCoursePublishVoById(String id);

    /**
     * 获取课程基本信息，用于课程详情页面展示
     *
     * @param courseId
     * @return
     */
    CourseWebVo getBaseCourseInfo(String courseId);
}
