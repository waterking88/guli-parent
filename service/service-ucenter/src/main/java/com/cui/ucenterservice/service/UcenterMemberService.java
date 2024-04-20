package com.cui.ucenterservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cui.commonutils.R;
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
    R register(RegisterVo registerVo);

    /**
     * 根据用户id获取用户信息
     *
     * @param memberId
     * @return
     */
    UcenterMember getLoginInfo(String memberId);

    /**
     * 根据微信openid获取用户信息
     *
     * @param openid
     * @return
     */
    UcenterMember getByOpenid(String openid);

    /**
     * 根据日期获取注册人数
     *
     * @param day
     * @return
     */
    Integer countRegisterByDay(String day);
}
