package com.cui.aclservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cui.aclservice.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据用户id获取权限值
     *
     * @param id
     * @return
     */
    List<String> selectPermissionValueByUserId(String id);

    /**
     * 获取所有权限值
     *
     * @return
     */
    List<String> selectAllPermissionValue();

    /**
     * 根据用户id获取java对象格式权限列表
     *
     * @param userId
     * @return
     */
    List<Permission> selectPermissionByUserId(String userId);
}
