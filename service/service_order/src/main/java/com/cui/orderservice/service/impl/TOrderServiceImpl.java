package com.cui.orderservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.commonutils.eduvo.UcenterMemberEdu;
import com.cui.commonutils.ordervo.CourseWebVoOrder;
import com.cui.orderservice.client.EduClient;
import com.cui.orderservice.client.UcenterClient;
import com.cui.orderservice.entity.TOrder;
import com.cui.orderservice.mapper.TOrderMapper;
import com.cui.orderservice.service.TOrderService;
import com.cui.orderservice.utils.OrderNoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author cui
 * @since 2024-04-14
 */
@Service
public class TOrderServiceImpl extends ServiceImpl<TOrderMapper, TOrder> implements TOrderService {

    @Autowired
    private EduClient eduClient;
    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String saveOrder(String courseId, String memberId) {
        //远程调用获取用户信息
        CourseWebVoOrder courseInfoDto = eduClient.getCourseInfoDto(courseId);
        //远程调用获取课程信息
        UcenterMemberEdu ucenterMemberEdu = ucenterClient.getInfo(memberId);

        //创建订单
        TOrder tOrder = new TOrder();

        //创建订单
        TOrder order = new TOrder();
        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoDto.getTitle());
        order.setCourseCover(courseInfoDto.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoDto.getPrice());
        order.setMemberId(memberId);
        order.setMobile(ucenterMemberEdu.getMobile());
        order.setNickname(ucenterMemberEdu.getNickname());
        order.setStatus(0);
        order.setPayType(1);
        baseMapper.insert(order);
        return order.getOrderNo();
    }
}
