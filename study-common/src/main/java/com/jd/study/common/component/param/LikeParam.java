package com.jd.study.common.component.param;

import lombok.Getter;
import lombok.Setter;

/**
 * like模糊查
 * @author lsy
 * @date 2020-07-18
 * @param <T>
 */
@Getter
@Setter
public class LikeParam<T> extends BaseParam<T> {

    public final static Integer LEFT = 0;
    public final static Integer RIGHT = 1;

    /**
     * like字段名
     */
    private String likeName;

    /**
     * 值
     */
    private String value;

    /**
     * 左模糊查：0  or  右模糊查：1
     *
     * @remark 默认右模糊查
     */
    private Integer right = RIGHT;
}
