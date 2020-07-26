package com.jd.study.common.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 * @author lsy
 */
public class RequestUtils {

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) ra;
        return attributes.getRequest();
    }

    /**
     * 获取客户端IP
     * @return
     */
    public static String getIp() {
        HttpServletRequest request = getRequest();
        return request.getHeader("X_FORWARDED_FOR");
    }
}
