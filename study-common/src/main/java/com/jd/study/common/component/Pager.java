package com.jd.study.common.component;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.common.component.param.InParam;
import com.jd.study.common.component.param.LikeParam;
import lombok.Getter;
import lombok.Setter;

/**
 * mybatis-plus 自定义分页配置
 * @author lsy
 * @date 2020-07-18
 * @param <T>
 */
@Getter
@Setter
public class Pager<T> {

    /**
     * 页码
     */
    private Long page = 1L;

    /**
     * 页大小
     */
    private Long pageSize = 10L;

    /**
     * 操作者ID
     */
    private Long optId;

    /**
     * 参数
     */
    private T condition;

    /**
     * 装饰BaseParam分页
     * @param pager
     * @param <T>
     * @return
     */
    public static <T> Page<T> decorateParam(Pager<BaseParam<T>> pager) {
        return new Page<T>(pager.getPage(), pager.getPageSize());
    }

    public static <T> Page<T> decorateLikeParam(Pager<LikeParam<T>> pager) {
        return new Page<T>(pager.getPage(), pager.getPageSize());
    }

    /**
     * 装饰InParam分页
     * @param pager
     * @param <T>
     * @return
     */
    public static <V, T> Page<T> decorateInParam(Pager<InParam<V, T>> pager) {
        return new Page<T>(pager.getPage(), pager.getPageSize());
    }
}
