package com.jd.study.common.component.param;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * in查询条件
 * @author lsy
 * @date 2020-07-18
 * @param <V>
 * @param <T>
 */
@Getter
@Setter
public class InParam<V, T> extends LikeParam<T> {

    /**
     * in 字段名称
     */
    private String inName;

    /**
     * in 查询value
     */
    private List<V> inValue = new ArrayList<>(5);

}
