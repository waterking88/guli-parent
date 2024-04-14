package com.cui.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.commonutils.R;
import com.cui.commonutils.ordervo.CourseWebVoOrder;
import com.cui.eduservice.entity.EduCourse;
import com.cui.eduservice.entity.frontvo.CourseQueryVo;
import com.cui.eduservice.entity.frontvo.CourseWebVo;
import com.cui.eduservice.entity.vo.ChapterVo;
import com.cui.eduservice.service.EduChapterService;
import com.cui.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author water
 * @date 2024/4/11
 * @Description
 */
@RestController
@CrossOrigin
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "分页课程列表")
    @PostMapping(value = "{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseQuery", value = "查询对象", required = false)
            @RequestBody(required = false) CourseQueryVo courseQuery) {
        Page<EduCourse> pageParam = new Page<>(page, limit);
        Map<String, Object> map = eduCourseService.pageListWeb(pageParam, courseQuery);
        return R.ok().data(map);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVos = eduChapterService.nestedList(courseId);

        return R.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVos);
    }

    //根据课程id查询课程信息
    @GetMapping("getVo/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId) {
        EduCourse eduCourse = eduCourseService.getById(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(eduCourse, courseWebVoOrder);
        return courseWebVoOrder;
    }
}
