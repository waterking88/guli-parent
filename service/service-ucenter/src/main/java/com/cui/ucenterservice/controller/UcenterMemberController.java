package com.cui.ucenterservice.controller;


import com.cui.commonutils.JwtUtils;
import com.cui.commonutils.R;
import com.cui.commonutils.eduvo.UcenterMemberEdu;
import com.cui.servicebase.exception.GuliException;
import com.cui.ucenterservice.entity.UcenterMember;
import com.cui.ucenterservice.entity.vo.LoginVo;
import com.cui.ucenterservice.entity.vo.RegisterVo;
import com.cui.ucenterservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
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
//@CrossOrigin  网关已配置跨域
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

    @GetMapping("test")
    public R login() {
        return R.ok().data("test", "test");
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
            UcenterMember ucenterMember = ucenterMemberService.getLoginInfo(memberId);
            return R.ok().data("item", ucenterMember);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GuliException(20001, "error");
        }
    }

    /**
     * 根据token字符串获取用户信息实体
     *
     * @param id
     * @return
     */
    @PostMapping("getInfoUc/{id}")
    public UcenterMemberEdu getInfo(@PathVariable String id) {
        //根据用户id获取用户信息
        UcenterMember ucenterMember = ucenterMemberService.getById(id);
        UcenterMemberEdu ucenterMemberEdu = new UcenterMemberEdu();
        BeanUtils.copyProperties(ucenterMember, ucenterMemberEdu);
        return ucenterMemberEdu;
    }


    /**
     * 查询某一天的注册人数
     *
     * @param day
     * @return
     */
    @GetMapping(value = "countregister/{day}")
    public R registerCount(
            @PathVariable String day) {
        Integer count = ucenterMemberService.countRegisterByDay(day);
        return R.ok().data("countRegister", count);
    }

}

