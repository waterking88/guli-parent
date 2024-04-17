package com.cui.orderservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cui.commonutils.JwtUtils;
import com.cui.commonutils.R;
import com.cui.orderservice.entity.TOrder;
import com.cui.orderservice.service.TOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/orderservice/t-order")
//@CrossOrigin  网关已配置跨域
public class TOrderController {
    @Autowired
    private TOrderService orderService;


    /**
     * 根据课程id和用户id创建订单，返回订单id
     *
     * @param courseId
     * @param request
     * @return
     */
    @PostMapping("createOrder/{courseId}")
    public R save(@PathVariable String courseId, HttpServletRequest request) {
        String orderId = orderService.saveOrder(courseId,
                JwtUtils.getMemberIdByJwtToken(request));
        return R.ok().data("orderId", orderId);
    }

    @GetMapping("getOrder/{orderId}")
    public R get(@PathVariable String orderId) {
        QueryWrapper<TOrder> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderId);
        TOrder order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }

    @GetMapping("isBuyCourse/{memberid}/{id}")
    public boolean isBuyCourse(@PathVariable String memberid,
                               @PathVariable String id) {
        //订单状态是1表示支付成功
        int count = orderService.count(new QueryWrapper<TOrder>().eq("member_id",
                memberid).eq("course_id", id).eq("status", 1));
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }
}

