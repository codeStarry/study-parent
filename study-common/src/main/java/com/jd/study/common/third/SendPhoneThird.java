package com.jd.study.common.third;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Component;

/**
 * 短信工具类
 * @author lsy
 */
@Component
public class SendPhoneThird {

    private static final String ACCESS_KEY_ID = "LTAI4G5tiiycVW78P6EhWS73";
    private static final String ACCESS_SECRET = "MkqJnbKbAfi49CTCYrd0FKzOgYBPr6";
    private static final String SIGN_NAME = "爱进步";
    private static final String TEMPLATE_CODE = "SMS_190794878";
    
    public static void sendMsg(String phoneNum, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", SIGN_NAME);
        request.putQueryParameter("TemplateCode", TEMPLATE_CODE);
        request.putQueryParameter("PhoneNumbers", phoneNum);
        request.putQueryParameter("TemplateParam", code);

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        }catch (ClientException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendMsg("13065749273", "{\"code\":\"12563\"}");
    }
}
