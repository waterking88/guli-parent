package com.cui.aclservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.aclservice.entity.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author testjava
 * @since 2020-01-12
 */
public interface UserService extends IService<User> {

    /**
     * 根据用户名从数据库中取出用户信息
     *
     * @param username
     * @return
     */
    User selectByUsername(String username);
}
