package com.cui.eduservice.controller;


import com.cui.commonutils.R;
import com.cui.eduservice.entity.vo.SubjectNestedVo;
import com.cui.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-03-28
 * description 过时了，变成 tags
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
//@CrossOrigin  网关已配置跨域
@Api(tags = "课程管理")
public class EduSubjectController {
    @Autowired
    private EduSubjectService eduSubjectService;

    /**
     * 添加课程分类
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public R addSubject(MultipartFile file) {
        //1 获取上传的excel文件 MultipartFile
        //返回错误提示信息
        eduSubjectService.saveSubject(file, eduSubjectService);
        //判断返回集合是否为空
        return R.ok();
    }

    @ApiOperation(value = "嵌套数据列表")
    @GetMapping("getNestedTreeList")
    public R nestedList() {
        List<SubjectNestedVo> subjectNestedVoList = eduSubjectService.nestedList();
        return R.ok().data("items", subjectNestedVoList);
    }
}


