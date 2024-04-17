package com.cui.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.commonutils.R;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.vo.CourseInfoVo;
import com.cui.eduservice.entity.vo.CoursePublishVo;
import com.cui.eduservice.entity.vo.CourseQuery;
import com.cui.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-03-29
 */
@RestController
@RequestMapping("/eduservice/edu-course")
//@CrossOrigin  网关已配置跨域
@Api(tags = "课程管理")
public class EduCourseController {
    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "新增课程")
    @PostMapping("addCourseInfo")
    public R saveCourseInfo(
            @ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        String courseId = eduCourseService.saveEduCourseInfo(courseInfoVo);
        if (!StringUtils.isEmpty(courseId)) {
            return R.ok().data("courseId", courseId);
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("courseInfo/{id}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        CourseInfoVo courseInfoVo = eduCourseService.getEduCourseInfoFormById(id);
        return R.ok().data("item", courseInfoVo);
    }

    @ApiOperation(value = "更新课程")
    @PutMapping("updateCourseInfo")
    public R updateCourseInfoById(
            @ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        eduCourseService.updateEduCourseInfoById(courseInfoVo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("coursePublishInfo/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        CoursePublishVo coursePublishVo = eduCourseService.getCoursePublishVoById(id);
        return R.ok().data("item", coursePublishVo);
    }

    @ApiOperation(value = "根据id发布课程")
    @PutMapping("publishCourse/{id}")
    public R publishCourseById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        eduCourseService.publishCourseById(id);
        return R.ok();
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
                    CourseQuery courseQuery) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        eduCourseService.pageQuery(pageParam, courseQuery);
        List<EduCourse> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }


    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id) {
        boolean result = eduCourseService.removeCourseById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }
}

