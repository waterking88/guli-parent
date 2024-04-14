package com.cui.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.eduservice.client.OssClient;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.EduCourseDescription;
import com.cui.eduservice.entity.frontvo.CourseQueryVo;
import com.cui.eduservice.entity.frontvo.CourseWebVo;
import com.cui.eduservice.entity.vo.CourseInfoVo;
import com.cui.eduservice.entity.vo.CoursePublishVo;
import com.cui.eduservice.entity.vo.CourseQuery;
import com.cui.eduservice.mapper.EduCourseMapper;
import com.cui.eduservice.service.EduChapterService;
import com.cui.eduservice.service.EduCourseDescriptionService;
import com.cui.eduservice.service.EduCourseService;
import com.cui.eduservice.service.EduVideoService;
import com.cui.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private OssClient ossClient;

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private EduChapterService eduChapterService;

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

    @Override
    public CoursePublishVo getCoursePublishVoById(String id) {
        return baseMapper.getCoursePublishVoById(id);
    }

    @Override
    public boolean publishCourseById(String id) {
        EduCourse course = new EduCourse();
        course.setId(id);
        //设置课程发布状态
        course.setStatus(EduCourse.COURSE_NORMAL);
        Integer count = baseMapper.updateById(course);
        return null != count && count > 0;
    }

    @Override
    public void pageQuery(Page<EduCourse> pageParam, CourseQuery courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");
        if (courseQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String title = courseQuery.getTitle();
        String teacherId = courseQuery.getTeacherId();
        String subjectParentId = courseQuery.getSubjectParentId();
        String subjectId = courseQuery.getSubjectId();
        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("title", title);
        }
        if (!StringUtils.isEmpty(teacherId)) {
            queryWrapper.eq("teacher_id", teacherId);
        }
        if (!StringUtils.isEmpty(subjectParentId)) {
            queryWrapper.ge("subject_parent_id", subjectParentId);
        }
        if (!StringUtils.isEmpty(subjectId)) {
            queryWrapper.ge("subject_id", subjectId);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean removeCourseById(String id) {
        //根据id删除所有视频
        eduVideoService.removeByCourseId(id);
        //根据id删除所有章节
        eduChapterService.removeByCourseId(id);
        //根据id删除所有课程详情
        eduCourseDescriptionService.removeById(id);
        //删除封面     查出cover，远程调用oss删除
        EduCourse eduCourse = this.getById(id);
        String cover = eduCourse.getCover();
        ossClient.remove(cover);

        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }

    @Cacheable(value = "EduCourse", key = "'popularCourses'")
    @Override
    public List<EduCourse> getPopularCourses() {
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");
        wrapper.last("limit 8");
        return baseMapper.selectList(wrapper);
    }

    @Override
    public List<EduCourse> selectByTeacherId(String id) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<EduCourse>();
        queryWrapper.eq("teacher_id", id);
        //按照最后更新时间倒序排列
        queryWrapper.orderByDesc("gmt_modified");
        List<EduCourse> courses = baseMapper.selectList(queryWrapper);
        return courses;
    }

    @Override
    public Map<String, Object> pageListWeb(Page<EduCourse> pageParam, CourseQueryVo courseQuery) {
        QueryWrapper<EduCourse> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(courseQuery.getSubjectParentId())) {
            queryWrapper.eq("subject_parent_id",
                    courseQuery.getSubjectParentId());
        }
        if (!StringUtils.isEmpty(courseQuery.getSubjectId())) {
            queryWrapper.eq("subject_id", courseQuery.getSubjectId());
        }
        if (!StringUtils.isEmpty(courseQuery.getBuyCountSort())) {
            queryWrapper.orderByDesc("buy_count");
        }
        if (!StringUtils.isEmpty(courseQuery.getGmtCreateSort())) {
            queryWrapper.orderByDesc("gmt_create");
        }
        if (!StringUtils.isEmpty(courseQuery.getPriceSort())) {
            queryWrapper.orderByDesc("price");
        }
        baseMapper.selectPage(pageParam, queryWrapper);
        List<EduCourse> records = pageParam.getRecords();
        long current = pageParam.getCurrent();
        long pages = pageParam.getPages();
        long size = pageParam.getSize();
        long total = pageParam.getTotal();
        boolean hasNext = pageParam.hasNext();
        boolean hasPrevious = pageParam.hasPrevious();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("items", records);
        map.put("current", current);
        map.put("pages", pages);
        map.put("size", size);
        map.put("total", total);
        map.put("hasNext", hasNext);
        map.put("hasPrevious", hasPrevious);
        return map;
    }

    @Override
    public CourseWebVo getBaseCourseInfo(String courseId) {
        this.updatePageViewCount(courseId);
        return baseMapper.getBaseCourseInfo(courseId);
    }

    @Override
    public void updatePageViewCount(String id) {
        EduCourse eduCourse = baseMapper.selectById(id);
        eduCourse.setViewCount(eduCourse.getViewCount() + 1);
        baseMapper.updateById(eduCourse);
    }


}


