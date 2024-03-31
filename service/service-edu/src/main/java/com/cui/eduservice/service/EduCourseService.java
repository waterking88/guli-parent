package com.cui.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cui
 * @since 2024-03-29
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveEduCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getEduCourseInfoFormById(String id);

    void updateEduCourseInfoById(CourseInfoVo courseInfoVo);
}
