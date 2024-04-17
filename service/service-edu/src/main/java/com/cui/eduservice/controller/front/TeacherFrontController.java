package com.cui.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.commonutils.R;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.EduTeacher;
import com.cui.eduservice.service.EduCourseService;
import com.cui.eduservice.service.EduTeacherService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author water
 * @date 2024/4/11
 * @Description
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
//@CrossOrigin  网关已配置跨域
public class TeacherFrontController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("getTeacherFrontList/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<EduTeacher> pageParam = new Page<EduTeacher>(page, limit);
        Map<String, Object> map = eduTeacherService.pageListWeb(pageParam);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping(value = "{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        //查询讲师信息
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        //根据讲师id查询这个讲师的课程列表
        List<EduCourse> courseList = eduCourseService.selectByTeacherId(id);
        return R.ok().data("teacher", eduTeacher).data("courseList", courseList);
    }

}
