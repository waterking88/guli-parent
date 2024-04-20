package com.cui.ucenterservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cui.commonutils.JwtUtils;
import com.cui.commonutils.MD5;
import com.cui.commonutils.R;
import com.cui.servicebase.exception.GuliException;
import com.cui.ucenterservice.entity.UcenterMember;
import com.cui.ucenterservice.entity.vo.LoginVo;
import com.cui.ucenterservice.entity.vo.RegisterVo;
import com.cui.ucenterservice.mapper.UcenterMemberMapper;
import com.cui.ucenterservice.service.UcenterMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author cui
 * @since 2024-04-08
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(mobile)) {
            throw new GuliException(20001, "error");
        }
        //获取会员
        UcenterMember ucenterMember = baseMapper.selectOne(new
                QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (null == ucenterMember) {
            throw new GuliException(20001, "error");
        }
        //校验密码
        if (!MD5.encrypt(password).equals(ucenterMember.getPassword())) {
            throw new GuliException(20001, "error");
        }
        //校验是否被禁用
        if (ucenterMember.getIsDisabled()) {
            throw new GuliException(20001, "error");
        }
        //使用JWT生成token字符串
        String token = JwtUtils.getJwtToken(ucenterMember.getId(), ucenterMember.getNickname());
        return token;
    }

    @Override
    public R register(RegisterVo registerVo) {
        //获取注册信息，进行校验
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();
        //校验参数
        if (StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(mobile) ||
                StringUtils.isEmpty(password) ||
                StringUtils.isEmpty(code)) {
            throw new GuliException(20001, "error");
        }
        //校验校验验证码
        //从redis获取发送的验证码
        String mobleCode = redisTemplate.opsForValue().get(mobile);
        if (!code.equals(mobleCode)) {
            throw new GuliException(20001, "验证码错误");
        }
        //查询数据库中是否存在相同的手机号码
        Integer count = baseMapper.selectCount(new
                QueryWrapper<UcenterMember>().eq("mobile", mobile));
        if (count.intValue() > 0) {
            throw new GuliException(20001, "手机号已注册过");
        }
        //添加注册信息到数据库
        UcenterMember ucenterMember = new UcenterMember();
        ucenterMember.setNickname(nickname);
        ucenterMember.setMobile(registerVo.getMobile());
        ucenterMember.setPassword(MD5.encrypt(password));
        ucenterMember.setIsDisabled(false);
        ucenterMember.setAvatar("http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132");
        this.save(ucenterMember);
        return R.ok();
    }

    /**
     * debug :该方法课件中返回值为LoginVo,缺少用户id，导致前端无法根据id判断用户是否已经登录.更改 LoginVO ===> UcenterMember
     *
     * @param memberId
     * @return
     */
    @Override
    public UcenterMember getLoginInfo(String memberId) {
        UcenterMember ucenterMember = baseMapper.selectById(memberId);
        return ucenterMember;
    }

    @Override
    public UcenterMember getByOpenid(String openid) {
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("openid", openid);
        UcenterMember ucenterMember = baseMapper.selectOne(queryWrapper);
        return ucenterMember;
    }

    @Override
    public Integer countRegisterByDay(String day) {
        return baseMapper.selectRegisterCount(day);
    }
}
