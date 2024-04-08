package com.cui.ucenterservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.ucenterservice.entity.UcenterMember;
import com.cui.ucenterservice.entity.vo.LoginVo;
import com.cui.ucenterservice.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author cui
 * @since 2024-04-08
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    /**
     * 用户登录
     *
     * @param loginVo
     * @return
     */
    String login(LoginVo loginVo);

    /**
     * 用户注册
     *
     * @param registerVo
     */
    void register(RegisterVo registerVo);

    /**
     * 根据用户id获取用户信息
     *
     * @param memberId
     * @return
     */
    LoginVo getLoginInfo(String memberId);
}
