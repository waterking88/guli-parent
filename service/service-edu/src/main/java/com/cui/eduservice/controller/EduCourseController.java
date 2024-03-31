package com.cui.eduservice.controller;


import com.cui.commonutils.R;
import com.cui.eduservice.entity.vo.CourseInfoVo;
import com.cui.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
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
}

