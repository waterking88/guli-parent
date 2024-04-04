package com.cui.eduservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.eduservice.entity.EduChapter;
import com.cui.eduservice.entity.vo.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author cui
 * @since 2024-03-29
 */
public interface EduChapterService extends IService<EduChapter> {
    /**
     * 返回树状章节信息
     *
     * @param courseId
     * @return
     */
    List<ChapterVo> nestedList(String courseId);

    /**
     * 根据id值删除章节信息
     *
     * @param id
     * @return
     */
    boolean removeChapterById(String id);

    /**
     * 根据课程id删除章节
     *
     * @param courseId
     * @return
     */
    boolean removeByCourseId(String courseId);
}
