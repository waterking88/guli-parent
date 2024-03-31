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

    /**
     * 保存课程信息
     *
     * @param courseInfoVo
     * @return
     */
    String saveEduCourseInfo(CourseInfoVo courseInfoVo);

    /**
     * 根据id获取课程信息
     *
     * @param id
     * @return
     */
    CourseInfoVo getEduCourseInfoFormById(String id);

    /**
     * 更新课程信息
     *
     * @param courseInfoVo
     */
    void updateEduCourseInfoById(CourseInfoVo courseInfoVo);
}
