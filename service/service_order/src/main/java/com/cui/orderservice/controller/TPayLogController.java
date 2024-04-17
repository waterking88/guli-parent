package com.cui.orderservice.controller;


import com.cui.commonutils.R;
import com.cui.orderservice.service.TPayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author cui
 * @since 2024-04-14
 */
@RestController
@RequestMapping("/orderservice/t-pay-log")
//@CrossOrigin  网关已配置跨域
public class TPayLogController {
    @Autowired
    private TPayLogService tPayLogService;

    /**
     * 生成二维码
     *
     * @return
     */
    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = tPayLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = tPayLogService.queryPayStatus(orderNo);
        //出错
        if (map == null) {
            return R.error().message("支付出错");
        }
        //如果成功
        if ("SUCCESS".equals(map.get("trade_state"))) {
            //更改订单状态
            tPayLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }
        return R.ok().code(25000).message("支付中");
    }
}

