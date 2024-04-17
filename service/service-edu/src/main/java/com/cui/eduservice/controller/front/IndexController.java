package com.cui.eduservice.controller.front;

import com.cui.commonutils.R;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.EduTeacher;
import com.cui.eduservice.service.EduCourseService;
import com.cui.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author water
 * @date 2024/4/7
 * @Description
 */
@RestController
@RequestMapping("/eduservice/index")
//@CrossOrigin  网关已配置跨域
public class IndexController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduTeacherService eduTeacherService;

    /**
     * 查询前8条热门课程，查询前4条名师
     *
     * @return
     */
    @GetMapping("index")
    public R index() {
        //查询前8条热门课程
        List<EduCourse> popularCourses = eduCourseService.getPopularCourses();
        //查询前4条名师
        List<EduTeacher> popularTeachers = eduTeacherService.getPopularTeachers();
        return R.ok().data("courseList", popularCourses).data("teacherList", popularTeachers);
    }
}
