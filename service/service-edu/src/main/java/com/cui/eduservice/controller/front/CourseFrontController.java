package com.cui.eduservice.controller.front;

import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.commonutils.JwtUtils;
import com.cui.commonutils.R;
import com.cui.commonutils.ordervo.CourseWebVoOrder;
import com.cui.eduservice.client.OrderClient;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author water
 * @date 2024/4/11
 * @Description
 */
@RestController
//@CrossOrigin  网关已配置跨域
@RequestMapping("/eduservice/coursefront")
public class CourseFrontController {
    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrderClient orderClient;


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

    @ApiOperation(value = "根据ID查询课程,用于展示课程详情")
    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId, HttpServletRequest request) {
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节
        List<ChapterVo> chapterVos = eduChapterService.nestedList(courseId);

        //远程调用，判断课程是否被购买
        boolean buyCourse = false;
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        //如果有登录再进行查询
        if (!StringUtils.isEmpty(memberIdByJwtToken)) {
            buyCourse = orderClient.isBuyCourse(memberIdByJwtToken, courseId);
        }
        return R.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVos).data("isbuy", buyCourse);
    }

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId
     * @return
     */
    @ApiOperation(value = "根据ID查询课程,用于支付界面展示")
    @GetMapping("getVo/{courseId}")
    public CourseWebVoOrder getCourseInfoDto(@PathVariable String courseId) {
        CourseWebVo baseCourseInfo = eduCourseService.getBaseCourseInfo(courseId);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(baseCourseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }
}
