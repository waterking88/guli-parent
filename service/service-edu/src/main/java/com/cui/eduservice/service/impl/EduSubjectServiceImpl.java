package com.cui.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.eduservice.entity.EduSubject;
import com.cui.eduservice.entity.excel.ExcelSubjectData;
import com.cui.eduservice.entity.vo.SubjectNestedVo;
import com.cui.eduservice.entity.vo.SubjectVo;
import com.cui.eduservice.listener.SubjectExcelListener;
import com.cui.eduservice.mapper.EduSubjectMapper;
import com.cui.eduservice.service.EduSubjectService;
import com.cui.servicebase.exception.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author cui
 * @since 2024-03-28
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //读取
            EasyExcel.read(in, ExcelSubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20002, "添加课程分类失败");
        }
    }

    @Override
    public List<SubjectNestedVo> nestedList() {
        //最终要的到的数据列表
        ArrayList<SubjectNestedVo> subjectNestedVoArrayList = new ArrayList<>();
        //获取一级分类数据记录
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", 0);
        queryWrapper.orderByAsc("sort", "id");
        List<EduSubject> firstSubjects = baseMapper.selectList(queryWrapper);
        //获取二级分类数据记录
        QueryWrapper<EduSubject> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.ne("parent_id", 0);
        queryWrapper2.orderByAsc("sort", "id");
        List<EduSubject> secondSubjects = baseMapper.selectList(queryWrapper2);
        //填充一级分类vo数据
        for (EduSubject firstSubject : firstSubjects) {
            //创建一级类别vo对象
            SubjectNestedVo subjectNestedVo = new SubjectNestedVo();
            BeanUtils.copyProperties(firstSubject, subjectNestedVo);
            subjectNestedVoArrayList.add(subjectNestedVo);
            //填充二级分类vo数据
            ArrayList<SubjectVo> subjectVoArrayList = new ArrayList<>();
            for (EduSubject secondSubject : secondSubjects) {
                if (firstSubject.getId().equals(secondSubject.getParentId())) {
                    //创建二级类别vo对象
                    SubjectVo subjectVo = new SubjectVo();
                    BeanUtils.copyProperties(secondSubject, subjectVo);
                    subjectVoArrayList.add(subjectVo);
                }
            }
            subjectNestedVo.setChildren(subjectVoArrayList);
        }
        return subjectNestedVoArrayList;
    }
}

