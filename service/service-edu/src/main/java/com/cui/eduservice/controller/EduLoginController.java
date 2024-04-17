package com.cui.eduservice.controller;

import com.cui.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author water
 * @date 2024/3/24
 * @Description 测试模拟前端接口
 * description 过时了，变成 tags
 */
@RestController
@RequestMapping("/eduservice/user")
@Api(tags = "登录")
//@CrossOrigin  网关已配置跨域
public class EduLoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://k.sinaimg.cn/n/sinakd20100/560/w1080h1080/20240219/079b-6645929cd79bc3defe82f90d1c77db01.jpg/w700d1q75cms.jpg");
    }
}
