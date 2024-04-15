package com.cui.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.frontvo.CourseQueryVo;
import com.cui.eduservice.entity.frontvo.CourseWebVo;
import com.cui.eduservice.entity.vo.CourseInfoVo;
import com.cui.eduservice.entity.vo.CoursePublishVo;
import com.cui.eduservice.entity.vo.CourseQuery;

import java.util.List;
import java.util.Map;

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

    /**
     * 获取课程发布的信息
     *
     * @param id
     * @return
     */
    CoursePublishVo getCoursePublishVoById(String id);

    /**
     * 根据id发布课程
     *
     * @param id
     * @return
     */
    boolean publishCourseById(String id);

    /**
     * 分页查询课程
     *
     * @param pageParam
     * @param courseQuery
     */
    void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery);


    /**
     * 根据id删除课程
     *
     * @param id
     * @return
     */
    boolean removeCourseById(String id);

    /**
     * 首页显示热门课程
     *
     * @return
     */
    List<EduCourse> getPopularCourses();

    /**
     * 根据讲师id查询这个讲师的课程列表
     *
     * @param id
     * @return
     */
    List<EduCourse> selectByTeacherId(String id);

    /**
     * 分页查询课程，前端展示
     *
     * @param pageParam
     * @param courseQuery
     * @return
     */
    Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery);

    /**
     * 获取课程详情页面的课程基本信息
     *
     * @param courseId
     * @return
     */
    CourseWebVo getBaseCourseInfo(String courseId);

    /**
     * 更新课程浏览数
     *
     * @param id
     */
    void updatePageViewCount(String id);
}
