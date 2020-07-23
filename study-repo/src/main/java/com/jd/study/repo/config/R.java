package com.jd.study.repo.config;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 返回视图
 * @author lsy
 * @date 2020-07-18
 * @param <T>
 */
@Data
public class R<T> {

    /**
     * 状态码
     */
    private int code;

    /**
     * 时间戳
     */
    private LocalDateTime timestamp;

    /**
     * 信息
     */
    private String message;

    /**
     * 数据
     */
    private T data;

    /**
     * 成功返回视图
     * @param data
     * @param <T>
     * @return
     */
    public static <T> R<T> ok(T data) {
        return resultApi(Constants.SUCCESS, LocalDateTime.now(), null, data);
    }

    /**
     * 异常返回视图
     * @param message
     * @param <T>
     * @return
     */
    public static <T> R<T> failure(String message) {
        return resultApi(Constants.ERROR, LocalDateTime.now(), message, null);
    }

    /**
     * 异常返回视图
     * @param message
     * @param code
     * @param <T>
     * @return
     */
    public static <T> R<T> failure(int code, String message) {
        return resultApi(code, LocalDateTime.now(), message, null);
    }

    /**
     * 视图基类
     * @param code              状态码
     * @param timestamp         时间戳
     * @param message           消息
     * @param data              数据
     * @param <T>
     * @return
     */
    public static <T> R<T> resultApi(int code, LocalDateTime timestamp, String message, T data) {
        R<T> result = new R<T>();
        result.setCode(code);
        result.setTimestamp(timestamp);
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}
