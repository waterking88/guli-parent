package com.cui.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cui.commonutils.R;
import com.cui.eduservice.entity.EduTeacher;
import com.cui.eduservice.entity.vo.EduTeacherQuery;
import com.cui.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-03-19
 * description 过时了，变成 tags
 */
@RestController
@RequestMapping("/eduservice/edu-teacher")
//@CrossOrigin  网关已配置跨域
@Api(tags = "讲师管理")
public class EduTeacherController {


    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation(value = "列出所有讲师")
    @GetMapping("findAll")
    public R list() {
//        //模拟异常
//        try{
//            int a = 10/0;
//        }catch(Exception e){
//            throw new GuliException(20001,"出现自定义guli异常");
//        }
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("items", list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        boolean result = eduTeacherService.removeById(id);
        if (result) {
            return R.ok();
        } else {
            return R.error().message("删除失败");
        }
    }


    @ApiOperation(value = "分页讲师列表")
    @PostMapping("pageQuery/{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody(required = false) EduTeacherQuery teacherQuery) {    //required=false 表示该值可以没有
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        eduTeacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        boolean save = eduTeacherService.save(teacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据ID修改讲师")
    @PutMapping("{id}")
    public R updateById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher) {
        teacher.setId(id);
        eduTeacherService.updateById(teacher);
        return R.ok();
    }
}

