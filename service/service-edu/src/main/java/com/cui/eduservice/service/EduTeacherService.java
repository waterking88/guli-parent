package com.cui.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.eduservice.entity.EduTeacher;
import com.cui.eduservice.entity.vo.EduTeacherQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author cui
 * @since 2024-03-19
 */
public interface EduTeacherService extends IService<EduTeacher> {
    /**
     * 分页条件查询讲师
     *
     * @param pageParam
     * @param eduTeacherQuery
     */
    void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery eduTeacherQuery);

    /**
     * 根据id逻辑删除讲师
     *
     * @param id
     * @return
     */
    @Override
    boolean removeById(Serializable id);

    /***
     * 获取热门教师
     * @return
     */
    List<EduTeacher> getPopularTeachers();

    /**
     * 分页查询讲师，前端展示
     *
     * @param pageParam
     * @return
     */
    Map<String, Object> pageListWeb(Page<EduTeacher> pageParam);
}
