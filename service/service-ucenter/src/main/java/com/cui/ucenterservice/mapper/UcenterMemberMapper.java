package com.cui.ucenterservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cui.ucenterservice.entity.UcenterMember;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author cui
 * @since 2024-04-08
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {

    /**
     * 根据日期获取注册人数
     *
     * @param day
     * @return
     */
    Integer selectRegisterCount(String day);
}
