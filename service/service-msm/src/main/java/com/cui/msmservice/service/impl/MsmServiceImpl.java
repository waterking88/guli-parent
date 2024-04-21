package com.cui.msmservice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.cui.msmservice.service.MsmService;
import com.cui.msmservice.utils.ConstantPropertiesUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author water
 * @date 2024/4/8
 * @Description
 */
@Service
public class MsmServiceImpl implements MsmService {
    /**
     * 发送短信
     */
    @Override
    public boolean send(String PhoneNumbers, String templateCode,
                        Map<String, Object> param) {
        if (StringUtils.isEmpty(PhoneNumbers)) {
            return false;
        }
        DefaultProfile profile =
                DefaultProfile.getProfile("default", ConstantPropertiesUtil.ACCESS_KEY_ID,
                        ConstantPropertiesUtil.ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        //提交方式
        request.setMethod(MethodType.POST);
        //请求路径
        request.setDomain("dysmsapi.aliyuncs.com");
        //修改版本号，使用最近的日期会报非法版本
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", PhoneNumbers);
        request.putQueryParameter("SignName", "个人谷粒在线教育平台网站");
        request.putQueryParameter("TemplateCode", templateCode);
        //验证码数据，需要转成json数据
        request.putQueryParameter("TemplateParam",
                JSONObject.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response.getHttpResponse().isSuccess();
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return false;
    }
}
