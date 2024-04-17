package com.cui.eduservice.controller;


import com.cui.commonutils.R;
import com.cui.eduservice.entity.EduVideo;
import com.cui.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-03-29
 */
@RestController
@RequestMapping("/eduservice/edu-video")
//@CrossOrigin  网关已配置跨域
@Api(tags = "课程视频管理")
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation(value = "新增课时")
    @PostMapping("saveVideoInfo")
    public R save(
            @ApiParam(name = "eduVideo", value = "课时对象", required = true)
            @RequestBody EduVideo eduVideo) {
        eduVideoService.save(eduVideo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询课时")
    @GetMapping("videoInfo/{id}")
    public R getVideInfoById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id) {
        EduVideo eduVideo = eduVideoService.getById(id);
        return R.ok().data("item", eduVideo);
    }

    @ApiOperation(value = "更新课时")
    @PutMapping("updateVideoInfo")
    public R updateCourseInfoById(
            @ApiParam(name = "EduVideo", value = "课时基本信息", required = true)
            @RequestBody EduVideo eduVideo) {
        eduVideoService.updateById(eduVideo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课时")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课时ID", required = true)
            @PathVariable String id) {
        boolean result = eduVideoService.removeVideoById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }
}

