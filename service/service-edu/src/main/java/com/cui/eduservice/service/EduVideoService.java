package com.cui.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.eduservice.entity.EduVideo;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author cui
 * @since 2024-03-29
 */
public interface EduVideoService extends IService<EduVideo> {
    /**
     * 根据课程id返回章节数量
     *
     * @param chapterId
     * @return
     */
    boolean getCountByChapterId(String chapterId);

    /**
     * 根据课程id删除video
     *
     * @param courseId
     * @return
     */
    boolean removeByCourseId(String courseId);

    /**
     * 根据id值删除课时信息
     *
     * @param id
     * @return
     */
    boolean removeVideoById(String id);
}
