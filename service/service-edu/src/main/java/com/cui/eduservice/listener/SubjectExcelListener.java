package com.cui.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cui.eduservice.entity.EduSubject;
import com.cui.eduservice.entity.excel.ExcelSubjectData;
import com.cui.eduservice.service.EduSubjectService;
import com.cui.servicebase.exception.GuliException;

import java.util.Map;

/**
 * @author water
 * @date 2024/3/28
 * @Description
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    /**
     * 创建有参数构造，传递subjectService用于操作数据库
     *
     * @param subjectService
     */
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 一行一行去读取excle内容
     *
     * @param subject
     * @param analysisContext
     */
    @Override
    public void invoke(ExcelSubjectData subject, AnalysisContext analysisContext) {
        if (subject == null) {
            throw new GuliException(20001, "添加失败");
        }
        //添加一级分类
        EduSubject existOneSubject =
                this.existOneSubject(subjectService, subject.getOneSubjectName());
        //没有相同的
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setTitle(subject.getOneSubjectName());
            existOneSubject.setParentId("0");
            subjectService.save(existOneSubject);
        }
        //获取一级分类id值
        String pid = existOneSubject.getId();
        //添加二级分类
        EduSubject existTwoSubject =
                this.existTwoSubject(subjectService, subject.getTwoSubjectName(), pid);
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setTitle(subject.getTwoSubjectName());
            existTwoSubject.setParentId(pid);
            subjectService.save(existTwoSubject);
        }
    }

    /**
     * 读取excel表头信息
     *
     * @param headMap
     * @param context
     */
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext
            context) {
        System.out.println("表头信息：" + headMap);
    }

    /**
     * 读取完成后执行
     *
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    /**
     * 判断二级分类是否重复
     *
     * @param subjectService
     * @param name
     * @param pid
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService, String
            name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        return subjectService.getOne(wrapper);
    }

    /**
     * 判断一级分类是否重复
     *
     * @param subjectService
     * @param name
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService subjectService, String
            name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        return subjectService.getOne(wrapper);
    }
}
