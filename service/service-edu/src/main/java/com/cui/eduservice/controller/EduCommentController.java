package com.cui.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.commonutils.JwtUtils;
import com.cui.commonutils.R;
import com.cui.commonutils.eduvo.UcenterMemberEdu;
import com.cui.eduservice.client.UcenterClient;
import com.cui.eduservice.entity.EduComment;
import com.cui.eduservice.service.EduCommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-04-12
 */
@Slf4j
@RestController
//@CrossOrigin  网关已配置跨域
@Api(tags = "课程评论")
@RequestMapping("/eduservice/edu-comment")
public class EduCommentController {
    @Autowired
    private EduCommentService eduCommentService;
    @Autowired
    private UcenterClient ucenterClient;

    /**
     * 根据课程id查询评论列表
     *
     * @param page
     * @param limit
     * @param courseId
     * @return
     */
    @ApiOperation(value = "评论分页列表")
    @GetMapping("{page}/{limit}")
    public R index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @RequestParam("courseId") String courseId) {
        Page<EduComment> pageParam = new Page<>(page, limit);
        Map<String, Object> map = eduCommentService.pageQuery(pageParam, courseId);
        return R.ok().data(map);
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("auth/save")
    public R save(@RequestBody EduComment eduComment, HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            return R.error().code(28004).message("请登录");
        }
        if (StringUtils.isEmpty(eduComment.getContent())) {
            return R.error().code(20001).message("请输入评论内容");
        }
        eduComment.setMemberId(memberId);
        UcenterMemberEdu ucenterMemberEdu = ucenterClient.getInfo(memberId);
        if (StringUtils.isEmpty(ucenterMemberEdu)) {
            return R.error().code(20001).message("评论失败！");
        }
        eduComment.setNickname(ucenterMemberEdu.getNickname());
        eduComment.setAvatar(ucenterMemberEdu.getAvatar());
        eduCommentService.save(eduComment);
        return R.ok();
    }
}

