package com.jd.study.common.component.param;

import lombok.Getter;
import lombok.Setter;

/**
 * 排序条件
 * @author lsy
 * @date 2020-07-18
 * @param <T>
 */
@Getter
@Setter
public class BaseParam<T> extends TimeParam<T> {

    /**
     * 排序字段名
     */
    private String orderBy;

    /**
     * 倒序：0  or  正序：1
     */
    private Integer asc = 0;
}
