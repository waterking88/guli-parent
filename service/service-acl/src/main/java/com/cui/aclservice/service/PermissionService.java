package com.cui.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.aclservice.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 获取全部菜单
     *
     * @return
     */
    List<Permission> queryAllMenu();

    /**
     * 根据角色获取菜单
     */
    List<Permission> selectAllMenu(String roleId);

    /**
     * 给角色分配权限
     *
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRealtionShip(String roleId, String[] permissionId);

    /**
     * 递归删除菜单
     */
    void removeChildById(String id);

    /**
     * 根据用户id获取用户菜单
     */
    List<String> selectPermissionValueByUserId(String id);

    /**
     * 根据用户id获取json格式权限列表
     *
     * @param id
     * @return
     */
    List<JSONObject> selectPermissionByUserId(String id);

    /**
     * 获取全部菜单
     *
     * @return
     */
    List<Permission> queryAllMenuGuli();

    /**
     * 递归删除菜单
     */
    void removeChildByIdGuli(String id);

    /**
     * 给角色分配权限
     *
     * @param roleId
     * @param permissionId
     */
    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);
}
