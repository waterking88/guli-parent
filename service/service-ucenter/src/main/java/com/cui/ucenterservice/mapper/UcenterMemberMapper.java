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

    Integer selectRegisterCount(String day);
}
