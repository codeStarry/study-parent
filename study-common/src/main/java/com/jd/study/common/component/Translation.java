package com.jd.study.common.component;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jd.study.common.component.param.BaseParam;
import com.jd.study.common.component.param.InParam;
import com.jd.study.common.component.param.LikeParam;
import com.jd.study.common.utils.StringUtils;
import org.springframework.util.CollectionUtils;

/**
 * 参数翻译类
 * @author lsy
 * @date 2020-07-18
 */
public class Translation {


    /**
     * 翻译baseParam
     * @param param
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> translationParam(BaseParam<T> param) {
        if (null == param) {
            return new QueryWrapper<>(param.getCondition());
        }
        QueryWrapper<T> wrapper = new QueryWrapper<>(param.getCondition())
                .ge(null != param.getStartTime(), StringUtils.humpToUnderscore(param.getTimeName()), param.getStartTime())
                .le(null != param.getEndTime(), StringUtils.humpToUnderscore(param.getTimeName()), param.getEndTime())
                .orderBy(StringUtils.isNotEmpty(param.getOrderBy()), param.getAsc() > 0, StringUtils.humpToUnderscore(param.getOrderBy()));

        return wrapper;
    }

    /**
     * 翻译LikeParam
     * @param param
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> translationLikeParam(LikeParam<T> param) {
        QueryWrapper<T> wrapper = translationParam(param);

        if (StringUtils.isEmpty(param.getLikeName())
                || StringUtils.isEmpty(param.getValue())) {

            return wrapper;
        }
        if (param.getRight().equals(LikeParam.LEFT)) {

            return wrapper.likeLeft(param.getLikeName(), param.getValue());
        }
        return wrapper.likeRight(param.getLikeName(), param.getValue());
    }

    /**
     * 翻译inParam
     * @param param
     * @param <V>
     * @param <T>
     * @return
     */
    public static <V, T> QueryWrapper<T> translationInParam(InParam<V, T> param) {
        QueryWrapper<T> wrapper = translationLikeParam(param);

        if (StringUtils.isEmpty(param.getInName())
                || CollectionUtils.isEmpty(param.getInValue())) {

            return wrapper;
        }
        return wrapper.in(param.getInName(), param.getInValue());
    }
}
