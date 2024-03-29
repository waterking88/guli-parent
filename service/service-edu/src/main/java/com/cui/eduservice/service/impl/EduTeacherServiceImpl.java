package com.cui.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.eduservice.entity.EduTeacher;
import com.cui.eduservice.entity.vo.EduTeacherQuery;
import com.cui.eduservice.mapper.EduTeacherMapper;
import com.cui.eduservice.service.EduTeacherService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author cui
 * @since 2024-03-19
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery eduTeacherQuery) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("sort");
        if (eduTeacherQuery == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String name = eduTeacherQuery.getName();
        Integer level = eduTeacherQuery.getLevel();
        String begin = eduTeacherQuery.getBegin();
        String end = eduTeacherQuery.getEnd();
        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }
        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }
        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }
        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }

    @Override
    public boolean removeById(Serializable id) {
        Integer result = baseMapper.deleteById(id);
        return null != result && result > 0;
    }
}
