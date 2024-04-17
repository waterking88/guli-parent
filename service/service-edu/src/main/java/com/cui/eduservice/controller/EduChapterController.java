package com.cui.eduservice.controller;


import com.cui.commonutils.R;
import com.cui.eduservice.entity.EduChapter;
import com.cui.eduservice.entity.vo.ChapterVo;
import com.cui.eduservice.service.EduChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/eduservice/edu-chapter")
//@CrossOrigin  网关已配置跨域
@Api(tags = "课程章节管理")
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;

    @ApiOperation(value = "嵌套章节数据列表")
    @GetMapping("nested-list/{courseId}")
    public R nestedListByCourseId(
            @ApiParam(name = "courseId", value = "课程ID", required = true)
            @PathVariable String courseId) {
        List<ChapterVo> chapterVoList = eduChapterService.nestedList(courseId);
        return R.ok().data("items", chapterVoList);
    }

    @ApiOperation(value = "新增章节")
    @PostMapping
    public R save(
            @ApiParam(name = "chapterVo", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter) {
        eduChapterService.save(eduChapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID查询章节")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id) {
        EduChapter eduChapter = eduChapterService.getById(id);
        return R.ok().data("item", eduChapter);
    }

    @ApiOperation(value = "根据ID修改章节")
    @PutMapping
    public R updateById(
            @ApiParam(name = "chapter", value = "章节对象", required = true)
            @RequestBody EduChapter eduChapter) {
        eduChapterService.updateById(eduChapter);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除章节")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "章节ID", required = true)
            @PathVariable String id) {
        boolean result = eduChapterService.removeChapterById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }
}

