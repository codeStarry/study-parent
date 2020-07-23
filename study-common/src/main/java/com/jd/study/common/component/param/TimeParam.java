package com.jd.study.common.component.param;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 时间参数类
 * @author lsy
 * @date 2020-7-18
 * @param <T>
 */
@Getter
@Setter
public class TimeParam<T> {

    /**
     * 时间字段名
     */
    private String timeName;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 参数
     */
    private T condition;
}
