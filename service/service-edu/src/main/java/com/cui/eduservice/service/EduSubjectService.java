package com.cui.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.eduservice.entity.EduSubject;
import com.cui.eduservice.entity.vo.SubjectNestedVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author cui
 * @since 2024-03-28
 */
public interface EduSubjectService extends IService<EduSubject> {
    /**
     * 通过Excel文件批量保存课程分类信息
     *
     * @param file
     * @param subjectService
     */
    void saveSubject(MultipartFile file, EduSubjectService subjectService);

    /**
     * 树状模式列出所有课程分类信息
     *
     * @return
     */
    List<SubjectNestedVo> nestedList();
}
