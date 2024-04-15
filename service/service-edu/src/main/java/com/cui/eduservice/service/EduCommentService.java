package com.cui.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.eduservice.entity.EduComment;

import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author cui
 * @since 2024-04-12
 */
public interface EduCommentService extends IService<EduComment> {
    /**
     * 分页查询评论
     *
     * @param pageParam
     * @param courseId
     * @return
     */
    Map<String, Object> pageQuery(Page<EduComment> pageParam, String courseId);
}
