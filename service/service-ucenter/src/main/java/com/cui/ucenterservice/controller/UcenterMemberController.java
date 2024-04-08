package com.cui.ucenterservice.controller;


import com.cui.commonutils.JwtUtils;
import com.cui.commonutils.R;
import com.cui.servicebase.exception.GuliException;
import com.cui.ucenterservice.entity.vo.LoginVo;
import com.cui.ucenterservice.entity.vo.RegisterVo;
import com.cui.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-04-08
 */
@RestController
@RequestMapping("/ucenterservice/ucenter-member")
@CrossOrigin
@Api(tags = "用户管理")
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo) {
        String token = ucenterMemberService.login(loginVo);
        return R.ok().data("token", token);
    }

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        ucenterMemberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("auth/getLoginInfo")
    public R getLoginInfo(HttpServletRequest request) {
        try {
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            LoginVo loginInfo = ucenterMemberService.getLoginInfo(memberId);
            return R.ok().data("item", loginInfo);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "error");
        }
    }
}

