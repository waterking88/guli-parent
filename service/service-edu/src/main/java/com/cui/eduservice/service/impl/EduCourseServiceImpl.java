package com.cui.eduservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.EduCourseDescription;
import com.cui.eduservice.entity.vo.CourseInfoVo;
import com.cui.eduservice.mapper.EduCourseMapper;
import com.cui.eduservice.service.EduCourseDescriptionService;
import com.cui.eduservice.service.EduCourseService;
import com.cui.eduservice.service.EduSubjectService;
import com.cui.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author cui
 * @since 2024-03-29
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Autowired
    private EduSubjectService eduSubjectService;

    @Override
    public String saveEduCourseInfo(CourseInfoVo courseInfoVo) {
        //保存课程基本信息
        EduCourse eduCourse = new EduCourse();
        eduCourse.setStatus(EduCourse.COURSE_DRAFT);
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean resultCourseInfo = this.save(eduCourse);
        if (!resultCourseInfo) {
            throw new GuliException(20001, "课程信息保存失败");
        }
        //保存课程详情信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean resultDescription = eduCourseDescriptionService.save(eduCourseDescription);
        if (!resultDescription) {
            throw new GuliException(20001, "课程详情信息保存失败");
        }
        return eduCourse.getId();
    }

    @Override
    public CourseInfoVo getEduCourseInfoFormById(String id) {
        EduCourse course = this.getById(id);
        if (course == null) {
            throw new GuliException(20001, "数据不存在");
        }
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(course, courseInfoVo);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(id);
        if (course != null) {
            courseInfoVo.setDescription(eduCourseDescription.getDescription());
        }
        return courseInfoVo;
    }

    @Override
    public void updateEduCourseInfoById(CourseInfoVo courseInfoVo) {
        //保存课程基本信息
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean resultCourseInfo = this.updateById(eduCourse);
        if (!resultCourseInfo) {
            throw new GuliException(20001, "课程信息保存失败");
        }
        //保存课程详情信息
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(eduCourse.getId());
        boolean resultDescription = eduCourseDescriptionService.updateById(eduCourseDescription);
        if (!resultDescription) {
            throw new GuliException(20001, "课程详情信息保存失败");
        }
    }
}
